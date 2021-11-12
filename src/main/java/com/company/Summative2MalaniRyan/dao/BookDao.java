package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.model.Book;

import java.util.List;

public interface BookDao {

    Book addBook(Book book);

    Book getBook(int id);

    List<Book> getBooksByAuthor(int authorId);

    List<Book> getAllBooks();

    void updateBook(Book book);

    void deleteBook(int id);
}
