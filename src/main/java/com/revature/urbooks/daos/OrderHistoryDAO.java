package com.revature.urbooks.daos;

import com.revature.urbooks.dto.UserDto;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Order;
import com.revature.urbooks.utils.custom_exceptions.InvalidSQLException;
import com.revature.urbooks.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderHistoryDAO implements CrudDAO<Order>{
    @Override
    public void save(Order obj) throws IOException {

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

    public List<Order> getAllOrders(String userId) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            String sqlStr = "select * from orders where user_id = '" + userId + "'";
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getFloat("subTotal"),
                        rs.getFloat("tax"),
                        rs.getFloat("grand_total"),
                        rs.getDate("orderedDate"),
                        rs.getBoolean("status"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return orders;
    }


    @Override
    public List<Order> getAll() {
        return null;
    }

    public List<Order> getAllOrdersByLatestDate(String userId) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            String sqlStr = "select * from orders where user_id = '" + userId + "' order by orderedDate desc";
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getFloat("subTotal"),
                        rs.getFloat("tax"),
                        rs.getFloat("grand_total"),
                        rs.getDate("orderedDate"),
                        rs.getBoolean("status"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return orders;
    }

    public List<Order> getAllOrdersByOldestDate(String userId) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            String sqlStr = "select * from orders where user_id = '" + userId + "' order by orderedDate asc";
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getFloat("subTotal"),
                        rs.getFloat("tax"),
                        rs.getFloat("grand_total"),
                        rs.getDate("orderedDate"),
                        rs.getBoolean("status"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return orders;
    }

    public List<Order> getAllOrdersByMostExpensive(String userId) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            String sqlStr = "select * from orders where user_id = '" + userId + "' order by orderedDate asc";
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getFloat("subTotal"),
                        rs.getFloat("tax"),
                        rs.getFloat("grand_total"),
                        rs.getDate("orderedDate"),
                        rs.getBoolean("status"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return orders;
    }

    public List<Order> getAllOrdersByLeastExpensive(String userId) {
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            String sqlStr = "select * from orders where user_id = '" + userId + "' order by orderedDate asc";
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getFloat("subTotal"),
                        rs.getFloat("tax"),
                        rs.getFloat("grand_total"),
                        rs.getDate("orderedDate"),
                        rs.getBoolean("status"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            //throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return orders;
    }
}
