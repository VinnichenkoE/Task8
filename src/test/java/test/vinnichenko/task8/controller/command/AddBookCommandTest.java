package test.vinnichenko.task8.controller.command;

import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.controller.command.impl.AddBookCommand;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class AddBookCommandTest {

    @Test
    public void executeTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameter.TITLE, "War and Peace");
        parameters.put(RequestParameter.AUTHORS, "Lev Tolstoy");
        parameters.put(RequestParameter.NUMBER_PAGES, "581");
        parameters.put(RequestParameter.TYPOGRAPHY, "Moscow");
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS, ResponseParameter.SUCCESS_STATUS);
        Command command = new AddBookCommand();
        Map<String, Object> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}