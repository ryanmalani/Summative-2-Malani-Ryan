package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.Summative2MalaniRyanApplication;
import com.company.Summative2MalaniRyan.model.Author;
import com.company.Summative2MalaniRyan.model.Book;
import com.company.Summative2MalaniRyan.model.Publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        removeAuthors();
    }

    @After
    public void tearDown() {
        removeAuthors();
    }

    private void removeAuthors() {
        // empty out the test database

        List<Author> authors = authorDao.getAllAuthors();
        authors.stream()
                .forEach(a ->
                {
                    authorDao.deleteAuthor(a.getAuthorId());
                });
    }

    @Test
    public void shouldAddGetDeleteAuthor() {

        Author author = new Author();
        author.setFirstName("Napolean");
        author.setLastName("Hill");
        author.setStreet("1 College Avenue");
        author.setCity("Wise");
        author.setState("VA");
        author.setPostalCode("24293");
        author.setPhone("2763286700");
        author.setEmail("info@naphill.org");

        author = authorDao.addAuthor(author);

        Author author2 = authorDao.getAuthor(author.getAuthorId());

        assertEquals(author2, author);

        authorDao.deleteAuthor(author.getAuthorId());

        author2 = authorDao.getAuthor(author.getAuthorId());

        assertNull(author2);
    }

    @Test
    public void shouldGetAllAuthors() {

        Author author = new Author();
        author.setFirstName("Napolean");
        author.setLastName("Hill");
        author.setStreet("1 College Avenue");
        author.setCity("Wise");
        author.setState("VA");
        author.setPostalCode("24293");
        author.setPhone("2763286700");
        author.setEmail("info@naphill.org");

        author = authorDao.addAuthor(author);

        author = new Author();
        author.setFirstName("Zig");
        author.setLastName("Ziglar");
        author.setStreet("15400 Knoll Trail Drive Suite 103");
        author.setCity("Dallas");
        author.setState("TX");
        author.setPostalCode("75248");
        author.setPhone("8005270306");
        author.setEmail("customercare@ziglar.com");

        author = authorDao.addAuthor(author);

        List<Author> aList = authorDao.getAllAuthors();
        assertEquals(aList.size(), 2);

    }

    @Test
    public void shouldUpdateAuthor() {

        Author author = new Author();
        author.setFirstName("Napolean");
        author.setLastName("Hill");
        author.setStreet("1 College Avenue");
        author.setCity("Wise");
        author.setState("VA");
        author.setPostalCode("24293");
        author.setPhone("2763286700");
        author.setEmail("info@naphill.org");

        author = authorDao.addAuthor(author);

        author.setFirstName("Zig");
        author.setLastName("Ziglar");
        author.setStreet("15400 Knoll Trail Drive Suite 103");
        author.setCity("Dallas");
        author.setState("TX");
        author.setPostalCode("75248");
        author.setPhone("8005270306");
        author.setEmail("customercare@ziglar.com");

        authorDao.updateAuthor(author);

        Author author2 = authorDao.getAuthor(author.getAuthorId());

        assertEquals(author2, author);
    }
}