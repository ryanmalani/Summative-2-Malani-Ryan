package com.company.Summative2MalaniRyan.dao;

import com.company.Summative2MalaniRyan.model.Author;

import java.util.List;

public interface AuthorDao {

    Author addAuthor(Author author);

    Author getAuthor(int id);

    List<Author> getAllAuthors();

    void updateAuthor(Author author);

    void deleteAuthor(int id);
}
