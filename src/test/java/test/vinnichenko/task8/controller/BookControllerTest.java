package test.vinnichenko.task8.controller;

import com.vinnichenko.task8.controller.BookController;
import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.vinnichenko.task8.dataprovider.SortDataProvider;
import test.vinnichenko.task8.dataprovider.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class BookControllerTest {

    BookController bookController;

    @BeforeMethod
    public void setUp() {
        bookController = BookController.getInstance();
        TestData.fillIn();
    }

    @Test
    public void testExecuteFindByTitle() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameter.TITLE, "war and peace");
        request.put(RequestParameter.COMMAND_NAME, "find_by_title");
        Map<String, Object> actual = bookController.executeTask(request);
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        List<CustomBook> books = new ArrayList<>();
        CustomBook book = new CustomBook(1, "War and Peace",
                "Lev Tolstoy", 581, "Moscow");
        books.add(book);
        expected.put(ResponseParameter.RESULT, books);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteFindById() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameter.ID, "1");
        request.put(RequestParameter.COMMAND_NAME, "find_by_id");
        Map<String, Object> actual = bookController.executeTask(request);
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        CustomBook book = new CustomBook(1, "War and Peace",
                "Lev Tolstoy", 581, "Moscow");
        expected.put(ResponseParameter.RESULT, book);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTaskTestWrongCommand() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameter.COMMAND_NAME,
                "something_wrong");
        Map<String, Object> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS, ResponseParameter.FAIL_STATUS);
        expected.put(ResponseParameter.MESSAGE,
                ResponseParameter.FAIL_MESSAGE);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTaskAdd() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameter.TITLE, "Hunter's Notes");
        request.put(RequestParameter.AUTHORS, "Ivan Tyrgenev");
        request.put(RequestParameter.NUMBER_PAGES, "125");
        request.put(RequestParameter.TYPOGRAPHY, "Contemporary");
        request.put(RequestParameter.COMMAND_NAME, "add_book");
        Map<String, Object> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void testExecuteFindAllOrderByTitle(List<CustomBook> books) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameter.COMMAND_NAME, "find_all_order_by");
        request.put(RequestParameter.COLUMN_NAME, "title");
        Map<String, Object> actual = bookController.executeTask(request);
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        expected.put(ResponseParameter.RESULT, books);
        assertEquals(actual, expected);
    }
}