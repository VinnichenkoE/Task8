package test.vinnichenko.task8.controller.command;

import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.controller.command.impl.FindByTitleCommand;
import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.vinnichenko.task8.dataprovider.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByTitleCommandTest {

    @BeforeMethod
    public void setUp() {
        TestData.fillIn();
    }

    @Test
    public void executeTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameter.TITLE, "War and Peace");
        CustomBook book = new CustomBook(1, "War and Peace",
                "Lev Tolstoy", 581, "Moscow");
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        expected.put(ResponseParameter.RESULT, books);
        Command command = new FindByTitleCommand();
        Map<String, Object> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}