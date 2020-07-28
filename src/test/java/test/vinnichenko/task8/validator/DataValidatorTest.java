package test.vinnichenko.task8.validator;

import com.vinnichenko.task8.validator.DataValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataValidatorTest {

    DataValidator dataValidator;

    @BeforeMethod
    public void setUp() {
        dataValidator = new DataValidator();
    }

    @Test
    public void isNumberPagesValidTestPositive() {
        boolean condition = dataValidator.isNumberPagesValid("125");
        assertTrue(condition);
    }

    @DataProvider(name = "numberPagesData")
    public Object[][] createNumberPagesData() {
        return new Object[][]{
                {"-125"},
                {"2501"},
                {"dd25"}
        };
    }

    @Test(dataProvider = "numberPagesData")
    public void isNumberPagesValidTestNegative(String stringNumberPages) {
        boolean condition = dataValidator.isNumberPagesValid(stringNumberPages);
        assertFalse(condition);
    }

    @Test
    public void isDigitTestPositive() {
        boolean condition = dataValidator.isDigit("125");
        assertTrue(condition);
    }

    @Test
    public void isDigitTestNegative() {
        boolean condition = dataValidator.isDigit("12ew");
        assertFalse(condition);
    }

    @DataProvider(name = "isAuthorValidData")
    public Object[][] createDataIsAuthorValid() {
        return new Object[][]{
                {"Lev Tolstoy Alexander Pushkin", true},
                {"Lev Tolstoy Alexander", false},
                {"L2d Tolstoy", false},
                {"Lev Tolstoy Alexander Pushkin Petr Petrov Nikolay " +
                        "Velichkovskiy", false}
        };
    }

    @Test(dataProvider = "isAuthorValidData")
    public void isAuthorValidTest(String authors, boolean expected) {
        boolean actual = dataValidator.isAuthorValid(authors);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isTitleValidData")
    public Object[][] createDataIsTitleValid() {
        return new Object[][]{
                {"One Day in the Life of Ivan Denisovich", true},
                {"The legend of Thiel Ulenspiegel and Lamm Gudzak, their " +
                        "adventures - funny, brave and glorious in Flanders " +
                        "and other countries", false}
        };
    }

    @Test(dataProvider = "isTitleValidData")
    public void isTitleValidTest(String title, boolean expected) {
        boolean actual = dataValidator.isTitleValid(title);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isTypographyValidData")
    public Object[][] createDataIsTypographyValid() {
        return new Object[][]{
                {"Konica Minolta", true},
                {"very long and complex name of some typography more than " +
                        "45 characters", false}
        };
    }

    @Test(dataProvider = "isTypographyValidData")
    public void isTypographyValidTest(String typography, boolean expected) {
        boolean actual = dataValidator.isTypographyValid(typography);
        assertEquals(actual, expected);
    }
}