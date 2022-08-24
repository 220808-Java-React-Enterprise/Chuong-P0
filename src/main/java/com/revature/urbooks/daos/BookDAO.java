package com.revature.urbooks.daos;

import com.revature.urbooks.dto.UserDto;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.utils.custom_exceptions.InvalidSQLException;
import com.revature.urbooks.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements CrudDAO<Book>{
    @Override
    public void save(Book obj) throws IOException {

    }

    @Override
    public void update(Book obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Book getById(String id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books");
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setPublisher_id(rs.getString("publisher_id"));
                book.setPrice(rs.getFloat("price"));
                book.setQuantity(rs.getInt("quantity"));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
