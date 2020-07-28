package test.vinnichenko.task8.controller.command;

import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.controller.command.impl.FindByIdCommand;
import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.vinnichenko.task8.dataprovider.TestData;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class FindByIdCommandTest {

    @BeforeMethod
    public void setUp() {
        TestData.fillIn();
    }

    @Test
    public void executeTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameter.ID, "1");
        CustomBook book = new CustomBook(1, "War and Peace",
                "Lev Tolstoy", 581, "Moscow");
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        expected.put(ResponseParameter.RESULT, book);
        Command command = new FindByIdCommand();
        Map<String, Object> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}