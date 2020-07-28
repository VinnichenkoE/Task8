package test.vinnichenko.task8.controller.command;

import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.controller.command.impl.WrongCommand;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class WrongCommandTest {

    @Test
    public void executeTest() {
        Map<String, String> parameters = new HashMap<>();
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.FAIL_STATUS);
        expected.put(ResponseParameter.MESSAGE,
                ResponseParameter.FAIL_MESSAGE);
        Command command = new WrongCommand();
        Map<String, Object> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}