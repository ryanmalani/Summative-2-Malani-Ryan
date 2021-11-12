package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

    // prepared statements

    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_BOOKS_BY_AUTHOR_SQL =
            "select * from book where author_id = ?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add a book to the database

    @Override
    public Book addBook(Book book) {

        // update jdbcTemplate using INSERT prepared statement

        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());

        // retrieve last inserted object id

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        book.setBookId(id);

        return book;
    }

    // return a book from database by id

    @Override
    public Book getBook(int id) {

        // try querying jdbcTemplate for book using prepared statement, rowMapper, and book id, return null if empty

        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // return a list of books by an author using author id

    @Override
    public List<Book> getBooksByAuthor(int authorId) {

        // query the jdbcTemplate for books using rowMapper and author id

        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR_SQL, this::mapRowToBook, authorId);
    }

    // return a list of all books

    @Override
    public List<Book> getAllBooks() {

        // query the jdbcTemplate for books using prepared statement and rowMapper

        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    // update a book

    @Override
    public void updateBook(Book book) {

        // use prepared statement to update properties of book in jdbcTemplate

        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getBookId());
    }

    // delete a book

    @Override
    public void deleteBook(int id) {

        // use prepared statement to remove a book from jdbcTemplate by id

        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    // rowMapper

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setAuthorId(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPrice(rs.getBigDecimal("price"));

        return book;
    }
}
