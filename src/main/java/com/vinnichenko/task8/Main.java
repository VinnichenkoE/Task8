package com.vinnichenko.task8;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConnectorDB.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("select * from warehouse");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                int numberPages = resultSet.getInt("numberPages");
                String author = resultSet.getString("authors");
                String typography = resultSet.getString("typography");
                System.out.println(id);
                System.out.println(title);
                System.out.println(numberPages);
                System.out.println(author);
                System.out.println(typography);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
