package com.revature.urbooks.services;

import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.dto.UserDto;
import com.revature.urbooks.models.Book;

import java.util.List;

public class BookService {

    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    public void update(Book selectedBook) {
        bookDAO.update(selectedBook);
    }
}
