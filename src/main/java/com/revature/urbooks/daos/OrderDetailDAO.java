package com.revature.urbooks.daos;

import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Order;
import com.revature.urbooks.utils.custom_exceptions.InvalidSQLException;
import com.revature.urbooks.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDetailDAO implements CrudDAO{

    @Override
    public void save(Object obj) throws IOException {

    }

    public void saveToOrderDetail(Order order, Map<String, Book> map) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {

            for(Map.Entry<String, Book> m : map.entrySet()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO order_details (order_id, book_id, quantity, status ) VALUES (?, ?, ?, ?)");
                ps.setString(1, order.getId());
                ps.setString(2, m.getValue().getId());
                ps.setInt(3, m.getValue().getQuantity());
                ps.setBoolean(4, false);
                ps.executeUpdate();
            }

            for(Map.Entry<String, Book> m : map.entrySet()) {
                PreparedStatement ps = con.prepareStatement("update books set quantity = ? where id = ?");
                PreparedStatement ps2 = con.prepareStatement("select quantity from books where id = ?");

                ps2.setString(1, m.getValue().getId());
                int inventoryQuantity = 0;
                ResultSet rs = ps2.executeQuery();
                if(rs.next()) {
                    inventoryQuantity = rs.getInt("quantity");
                }


                ps.setInt(1, inventoryQuantity - m.getValue().getQuantity());
                ps.setString(2, m.getValue().getId());

                ps.executeUpdate();

            }

        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            e.printStackTrace();;
        }
    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Object getById(String id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
