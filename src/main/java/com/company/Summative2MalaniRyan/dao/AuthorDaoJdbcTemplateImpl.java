package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJdbcTemplateImpl implements AuthorDao {

    // prepared statements

    private static final String INSERT_AUTHOR_SQL =
            "insert into author (first_name, last_name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_AUTHOR_SQL =
            "select * from author where author_id = ?";

    private static final String SELECT_ALL_AUTHORS_SQL =
            "select * from author";

    private static final String UPDATE_AUTHOR_SQL =
            "update author set first_name = ?, last_name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where author_id = ?";

    private static final String DELETE_AUTHOR_SQL =
            "delete from author where author_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add author

    @Override
    public Author addAuthor(Author author) {

        // update jdbcTemplate using INSERT prepared statement

        jdbcTemplate.update(INSERT_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail());

        // retrieve last inserted object id

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        author.setAuthorId(id);

        return author;

    }

    // return author by id

    @Override
    public Author getAuthor(int id) {

        // try querying jdbcTemplate for author using prepared statement, rowMapper, and author id, return null if empty

        try {
            return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowToAuthor, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // return a list of all authors

    @Override
    public List<Author> getAllAuthors() {

        // query the jdbcTemplate for authors using prepared statement and rowMapper

        return jdbcTemplate.query(SELECT_ALL_AUTHORS_SQL, this::mapRowToAuthor);
    }

    // update author

    @Override
    public void updateAuthor(Author author) {

        // use prepared statement to update properties of author in jdbcTemplate

        jdbcTemplate.update(UPDATE_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail(),
                author.getAuthorId());

    }

    // delete an author

    @Override
    public void deleteAuthor(int id) {

        // use prepared statement to remove an author from jdbcTemplate by id

        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);
    }

    // rowMapper

    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setAuthorId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostalCode(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));

        return author;
    }
}
