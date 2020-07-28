package test.vinnichenko.task8.controller.command;

import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.controller.command.impl.FindAllOrderByCommand;
import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.vinnichenko.task8.dataprovider.SortDataProvider;
import test.vinnichenko.task8.dataprovider.TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindAllOrderByCommandTest {

    @BeforeMethod
    public void setUp() {
        TestData.fillIn();
    }

    @Test(dataProvider = "sortByNumberPages",
            dataProviderClass = SortDataProvider.class)
    public void executeTest(List<CustomBook> sortedList) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameter.COLUMN_NAME, "number_pages");
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseParameter.STATUS,
                ResponseParameter.SUCCESS_STATUS);
        expected.put(ResponseParameter.RESULT, sortedList);
        Command command = new FindAllOrderByCommand();
        Map<String, Object> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}