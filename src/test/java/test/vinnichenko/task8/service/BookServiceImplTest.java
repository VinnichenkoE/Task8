package test.vinnichenko.task8.service;

import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.entity.CustomBook;
import com.vinnichenko.task8.model.service.impl.BookServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task8.dataprovider.SortDataProvider;
import test.vinnichenko.task8.dataprovider.TestData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookServiceImplTest {

    BookServiceImpl bookService;

    @BeforeMethod
    public void setUp() {
        TestData.fillIn();
        bookService = BookServiceImpl.getInstance();
    }

    @Test
    public void addTest() throws ServiceException {
        boolean condition = bookService
                .add("War and Peace", "Lev Tolstoy",
                        "581", "Konica Minolta");
        assertTrue(condition);
    }

    @DataProvider(name = "addTestNegative")
    public Object[][] createDataAddNegative() {
        return new Object[][]{
                {null, "Lev Tolstoy", "581", "Konica Minolta"},
                {"War and Peace", null, "581", "Konica Minolta"},
                {"War and Peace", "Lev Tolstoy", null, "Konica Minolta"},
                {"War and Peace", "Lev Tolstoy", "581", null},
                {"War and Peace", "Lev", "581", "Konica Minolta"},
                {"War and Peace", "Lev Tolstoy", "-15", "Konica Minolta"}
        };
    }

    @Test(dataProvider = "addTestNegative",
            expectedExceptions = ServiceException.class)
    public void addTestNegative(String title, String authors,
                                String numberPages, String typography)
            throws ServiceException {
        bookService.add(title, authors, numberPages, typography);
    }

    @DataProvider(name = "book")
    public Object[][] createDataBook() {
        CustomBook book = new CustomBook(2, "Evgeniy Onegin",
                "Alexander Pushkin", 250,
                "Tver");
        return new Object[][]{{book}};
    }

    @Test(dataProvider = "book")
    public void findByIdTest(CustomBook expected) throws ServiceException {
        CustomBook actual = bookService.findById(2);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void findByTitleTest(CustomBook book) throws ServiceException {
        List<CustomBook> actual = bookService.findByTitle("Evgeniy Onegin");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByTitleTestNegative() throws ServiceException {
        String title = null;
        bookService.findByTitle(title);
    }

    @Test(dataProvider = "sortById",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByIdTest(List<CustomBook> expected)
            throws ServiceException {
        List<CustomBook> actual = bookService.findAllOrderBy("id");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByTitleTest(List<CustomBook> expected)
            throws ServiceException {
        List<CustomBook> actual = bookService
                .findAllOrderBy("title");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByAuthorTest(List<CustomBook> expected)
            throws ServiceException {
        List<CustomBook> actual = bookService
                .findAllOrderBy("authors");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByNumberPages",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByPagesTest(List<CustomBook> expected)
            throws ServiceException {
        List<CustomBook> actual = bookService
                .findAllOrderBy("number_pages");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTypography",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByTypographyTest(List<CustomBook> expected)
            throws ServiceException {
        List<CustomBook> actual = bookService
                .findAllOrderBy("typography");
        assertEquals(actual, expected);
    }
}