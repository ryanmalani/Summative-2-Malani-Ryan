package com.company.Summative2MalaniRyan.dao;

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
public class BookDaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        removeBooksAuthorsPublishers();
    }

    @After
    public void tearDown() {
        removeBooksAuthorsPublishers();
    }

    private void removeBooksAuthorsPublishers() {

        // empty out test database
        List<Book> books = bookDao.getAllBooks();
        books.stream()
                .forEach(b ->
                {
                    bookDao.deleteBook(b.getBookId());
                });

        List<Author> authors = authorDao.getAllAuthors();
        authors.stream()
                .forEach(a ->
                {
                    authorDao.deleteAuthor(a.getAuthorId());
                });

        List<Publisher> publishers = publisherDao.getAllPublishers();
        publishers.stream()
                .forEach(p ->
                {
                    publisherDao.deletePublisher(p.getPublisherId());
                });
    }

    @Test
    public void shouldAddGetDeleteBook() {

        // ARRANGE

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

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("9781585424337");
        book.setPublishDate(LocalDate.of(2005, 8, 18));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("Think And Grow Rich");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("7.49"));
        book = bookDao.addBook(book);

        // ACT

        Book book2 = bookDao.getBook(book.getBookId());

        // ASSERT

        assertEquals(book2, book);

        // ACT

        bookDao.deleteBook(book.getBookId());

        book2 = bookDao.getBook(book.getBookId());

        // ASSERT

        assertNull(book2);
    }

    @Test
    public void shouldGetBookByAuthorId() {

        // ARRANGE

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

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("9781585424337");
        book.setPublishDate(LocalDate.of(2005, 8, 18));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("Think And Grow Rich");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("7.49"));
        book = bookDao.addBook(book);

        // ACT

        List<Book> booksByAuthor = bookDao.getBooksByAuthor(author.getAuthorId());

        for(Book testBook : booksByAuthor) {

            // ASSERT

            assertEquals(author.getAuthorId(), testBook.getAuthorId());
        }
    }

    @Test
    public void shouldGetAllBooks() {

        // ARRANGE

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisher = publisherDao.addPublisher(publisher);

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

        Book book = new Book();
        book.setIsbn("9781585424337");
        book.setPublishDate(LocalDate.of(2005, 8, 18));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("Think And Grow Rich");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("7.49"));

        book = bookDao.addBook(book);

        book = new Book();
        book.setIsbn("9781565547063");
        book.setPublishDate(LocalDate.of(2000, 6, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("See You At The Top");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("8.00"));

        book = bookDao.addBook(book);

        // ACT

        List<Book> bList = bookDao.getAllBooks();

        // ASSERT

        assertEquals(bList.size(), 2);
    }

    @Test
    public void shouldUpdateBook() {

        // ARRANGE

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

        Publisher publisher = new Publisher();
        publisher.setName("TarcherPerigee");
        publisher.setStreet("1745 Broadway");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("10019");
        publisher.setPhone("2127829000");
        publisher.setEmail("penguinrandomhouse@penguinrandomhouse.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("978-1585424337");
        book.setPublishDate(LocalDate.of(2005, 8, 18));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("Think And Grow Rich");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("7.49"));

        book = bookDao.addBook(book);

        // ACT

        String newIsbn = "NEW ISBN";

        book.setIsbn(newIsbn);

        bookDao.updateBook(book);

        Book book2 = bookDao.getBook(book.getBookId());

        // ASSERT

        assertEquals(newIsbn, book2.getIsbn());
    }

}