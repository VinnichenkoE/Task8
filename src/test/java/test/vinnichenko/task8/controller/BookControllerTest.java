package test.vinnichenko.task8.controller;

import com.vinnichenko.task8.controller.BookController;
import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static org.testng.Assert.*;

public class BookControllerTest {

    BookController bookController;
    static Connection connection;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("test_database");
        String url = resource.getString("jdbc.url");
        String user = resource.getString("jdbc.username");
        String pass = resource.getString("jdbc.password");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void init() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO warehouse(title, authors, number_pages, typography) " +
                    "VALUES ('War and Peace', 'Lev Tolstoy', 581,  'Moscow')," +
                    "('Evgeniy Onegin', 'Alexander Pushkin', 250, 'Tver')," +
                    "('Master and Margarita', 'Mihail Bylgakov', 350, 'Contemporary')," +
                    "('Hunters Notes', 'Ivan Tyrgenev', 150, 'Konica Minolta');");
        }
    }

    @BeforeMethod
    public void setUp() {
        bookController = BookController.getInstance();
    }

    @AfterClass
    public void tearDown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from warehouse;");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecuteFindByTitle() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameter.TITLE, "War and Peace");
        request.put(RequestParameter.COMMAND_NAME, "find_by_title");
        Map<String, Object> actual = bookController.executeTask(request);
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        List<CustomBook> books = new ArrayList<>();
        CustomBook book =
                new CustomBook(1, "War and Peace",
                        "Lev Tolstoy", 581, "Tver");
        books.add(book);
        expected.put(ResponseParameter.RESULT, books);
        assertEquals(actual, expected);
    }
}