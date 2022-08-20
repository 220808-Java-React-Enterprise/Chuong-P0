package com.revature.urbooks.utils.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Singleton design pattern */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private final Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        Connection conn = DriverManager.getConnection(url, username, password);
        if (conn == null) throw new RuntimeException("Could not establish connection with the database!");

        return conn;
    }
}
