package com.vinnichenko.task8.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("jdbc.url");
        String user = resource.getString("jdbc.username");
        String pass = resource.getString("jdbc.password");
        return DriverManager.getConnection(url, user, pass);
    }
}
