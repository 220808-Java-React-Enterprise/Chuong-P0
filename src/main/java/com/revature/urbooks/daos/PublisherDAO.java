package com.revature.urbooks.daos;

import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Publisher;
import com.revature.urbooks.models.User;
import com.revature.urbooks.utils.custom_exceptions.InvalidSQLException;
import com.revature.urbooks.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO implements CrudDAO<User>{
    @Override
    public void save(User obj) throws IOException {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from publishers");
            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publisher.setAddress(rs.getString("address"));
                publisher.setCity(rs.getString("city"));
                publisher.setState(rs.getString("state"));
                publisher.setZipcode(rs.getString("zipcode"));
                publisher.setPhone(rs.getString("phone"));
                publishers.add(publisher);
            }
            return publishers;
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
