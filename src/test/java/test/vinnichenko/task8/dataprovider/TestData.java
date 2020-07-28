package test.vinnichenko.task8.dataprovider;

import com.vinnichenko.task8.connection.ConnectorDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestData {
    private static final String SQL_DELETE_ALL = "DELETE FROM warehouse;";
    private static final String SQL_RESET_ID = "ALTER TABLE warehouse " +
            "AUTO_INCREMENT = 1;";
    private static final String SQL_INSERT = "INSERT INTO warehouse" +
            "(title, authors, number_pages, typography) " +
            "VALUES ('War and Peace', 'Lev Tolstoy', 581,  'Moscow'), " +
            "('Evgeniy Onegin', 'Alexander Pushkin', 250, 'Tver'), " +
            "('Master and Margarita', 'Mihail Bylgakov', 350, 'Contemporary'), " +
            "('Hunters Notes', 'Ivan Tyrgenev', 125, 'Konica Minolta');";

    public static void fillIn() {
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(SQL_DELETE_ALL);
            statement.execute(SQL_RESET_ID);
            statement.execute(SQL_INSERT);
        } catch (SQLException ex) {
            System.err.println("can not execute statement");
        }
    }
}
