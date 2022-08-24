package com.revature.urbooks.daos;

import com.revature.urbooks.dto.UserDto;
import com.revature.urbooks.models.User;
import com.revature.urbooks.utils.custom_exceptions.InvalidSQLException;
import com.revature.urbooks.utils.database.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User user) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role, email, phone, firstname, lastname) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getFirstName());
            ps.setString(8, user.getLastName());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ;
            }
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                String id = rs.getString("id");
                String username =  rs.getString("username");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setRole(role);
                user.setEmail(email);
                user.setPhone(phone);
                user.setFirstName(firstName);
                user.setLastName(lastName);

                users.add(user);
            }

            return users;
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

    public String getUsername(String username) {
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                throw new InvalidSQLException("An error occurred when tyring to save to the database.");


        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        } finally {
            if(con != null) {

            }
        }

        return null;
    }



    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                String id = rs.getString("id");
                String username =  rs.getString("username");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setRole(role);
                user.setEmail(email);
                user.setPhone(phone);
                user.setFirstName(firstName);
                user.setLastName(lastName);

                users.add(user);
            }

            return users;
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

    public List<User> searchUserFirstAndLast(String firstName, String lastName) {
        List<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getInstance().getConnection();
            //PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE firstName like ''%?%'' or lastName like '%?%'");
           // ps.setString(1, firstName);
            //ps.setString(2, lastName);
           // ResultSet rs = ps.executeQuery();


            String sql = "SELECT * FROM users WHERE lower(firstname) like '%" + firstName.toLowerCase() + "%' or lower(lastName) like '%" + lastName.toLowerCase() + "%';";
            Statement  stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                User user = new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
                users.add(user);
            }



        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return users;
    }
}
