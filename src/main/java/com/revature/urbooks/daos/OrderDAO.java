package com.revature.urbooks.daos;

import com.revature.urbooks.models.Order;
import com.revature.urbooks.models.User;
import com.revature.urbooks.utils.custom_exceptions.InvalidSQLException;
import com.revature.urbooks.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    @Override
    public void save(Order order) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, subTotal, tax, grand_total, status, user_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getId());
            ps.setDouble(2, order.getSubTotal());
            ps.setDouble(3, order.getTax());
            ps.setDouble(4, order.getGrandTotal());
            ps.setBoolean(5, false);
            ps.setString(6, order.getUser().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save an Order to the database. " );
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
