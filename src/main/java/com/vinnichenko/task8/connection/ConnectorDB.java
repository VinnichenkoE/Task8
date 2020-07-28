package com.vinnichenko.task8.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        String url = null;
        String user = null;
        String pass = null;
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            url = resource.getString("jdbc.url");
            user = resource.getString("jdbc.username");
            pass = resource.getString("jdbc.password");
        } catch (MissingResourceException ex) {
            System.err.println("can not find properties file");
        }
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
}