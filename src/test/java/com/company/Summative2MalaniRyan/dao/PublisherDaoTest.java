package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.model.Author;
import com.company.Summative2MalaniRyan.model.Book;
import com.company.Summative2MalaniRyan.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {

        // empty out the test database

        List<Author> authors = authorDao.getAllAuthors();
        authors.stream()
                .forEach(a ->
                {
                    authorDao.deleteAuthor(a.getAuthorId());
                });

        List<Book> books = bookDao.getAllBooks();
        books.stream()
                .forEach(b ->
                {
                    bookDao.deleteBook(b.getBookId());
                });

        List<Publisher> publishers = publisherDao.getAllPublishers();
        publishers.stream()
                .forEach(p ->
                {
                    publisherDao.deletePublisher(p.getPublisherId());
                });
    }

    @Test
    public void shouldAddGetDeletePublisher() {

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisherDao.addPublisher(publisher);

        Publisher publisher2 = publisherDao.getPublisher(publisher.getPublisherId());

        assertEquals(publisher2, publisher);

        publisherDao.deletePublisher(publisher.getPublisherId());

        publisher2 = publisherDao.getPublisher(publisher.getPublisherId());

        assertNull(publisher2);
    }

    @Test
    public void shouldGetAllPublishers() {

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("Pelican Publishing Company");
        publisher.setStreet("1000 Burmaster Street");
        publisher.setCity("Gretna");
        publisher.setState("LA");
        publisher.setPostalCode("70053");
        publisher.setPhone("5046848976");
        publisher.setEmail("editorial@pelicanpub.com");
        publisherDao.addPublisher(publisher);

        List<Publisher> pList = publisherDao.getAllPublishers();

        assertEquals(pList.size(), 2);

    }

    @Test
    public void shouldUpdatePublisher() {

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisherDao.addPublisher(publisher);

        publisher.setName("NEW NAME");
        publisher.setStreet("NEW STREET");
        publisher.setCity("NEW CITY");
        publisher.setState("NA");
        publisher.setPostalCode("NEW POSTAL CODE");
        publisher.setPhone("NEW PHONE");
        publisher.setEmail("NEW EMAIL");
        publisherDao.updatePublisher(publisher);

        Publisher publisher2 = publisherDao.getPublisher(publisher.getPublisherId());

        assertEquals(publisher2, publisher);
    }
}