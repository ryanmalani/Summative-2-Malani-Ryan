package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaoJdbcTemplateImpl implements PublisherDao {

    // prepared statements

    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher (name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUBLISHERS_SQL =
            "select * from publisher";

    private static final String UPDATE_PUBLISHER_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where publisher_id = ?";

    private static final String DELETE_PUBLISHER_SQL =
            "delete from publisher where publisher_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add publisher

    @Override
    public Publisher addPublisher(Publisher publisher) {

        // update jdbcTemplate using INSERT prepared statement

        jdbcTemplate.update(INSERT_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail());

        // retrieve last inserted object id

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        publisher.setPublisherId(id);

        return publisher;
    }

    // return publisher by id

    @Override
    public Publisher getPublisher(int id) {

        // try querying jdbcTemplate for publisher using prepared statement, rowMapper, and publisher id, return null if empty

        try {
            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // return a list of all publishers

    @Override
    public List<Publisher> getAllPublishers() {

        // query the jdbcTemplate for publishers using prepared statement and rowMapper

        return jdbcTemplate.query(SELECT_ALL_PUBLISHERS_SQL, this::mapRowToPublisher);
    }

    // update publisher

    @Override
    public void updatePublisher(Publisher publisher) {

        // use prepared statement to update properties of publisher in jdbcTemplate

        jdbcTemplate.update(UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getPublisherId());
    }

    // delete publisher

    @Override
    public void deletePublisher(int id) {

        // use prepared statement to remove a publisher from jdbcTemplate by id

        jdbcTemplate.update(DELETE_PUBLISHER_SQL, id);
    }

    // rowMapper

    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {

        Publisher publisher = new Publisher();
        publisher.setPublisherId(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostalCode(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));

        return publisher;
    }
}
