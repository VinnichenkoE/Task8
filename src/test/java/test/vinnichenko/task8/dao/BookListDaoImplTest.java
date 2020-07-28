package test.vinnichenko.task8.dao;

import com.vinnichenko.task8.exception.DaoException;
import com.vinnichenko.task8.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task8.dataprovider.SortDataProvider;
import test.vinnichenko.task8.dataprovider.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookListDaoImplTest {

    BookListDaoImpl bookListDao;

    @BeforeMethod
    public void setUp() {
        TestData.fillIn();
        bookListDao = BookListDaoImpl.getInstance();
    }

    @Test
    public void addTest() throws DaoException {
        CustomBook book = new CustomBook("Her Final Words",
                "Brianna Labuskes", 343,
                "Thomas&Mercer");
        boolean condition = bookListDao.add(book);
        assertTrue(condition);
    }

    @DataProvider(name = "book")
    public Object[][] createDataBook() {
        CustomBook book = new CustomBook(2, "Evgeniy Onegin",
                "Alexander Pushkin", 250, "Tver");
        return new Object[][]{{book}};
    }

    @Test(dataProvider = "book")
    public void findByIdTest(CustomBook expected) throws DaoException {
        CustomBook actual = bookListDao.findById(2).get();
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestNegative() throws DaoException {
        Optional<CustomBook> actual = bookListDao.findById(25);
        Optional<CustomBook> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void findByTitleTest(CustomBook book) throws DaoException {
        List<CustomBook> actual = bookListDao.findByTitle("Evgeniy Onegin");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTitleTestNegative() throws DaoException {
        List<CustomBook> actual = bookListDao.findByTitle("Mermaid");
        List<CustomBook> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortById", dataProviderClass = SortDataProvider.class)
    public void findAllOrderByIdTest(List<CustomBook> expected)
            throws DaoException {
        List<CustomBook> actual = bookListDao.findAllOrderBy("id");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByTitleTest(List<CustomBook> expected)
            throws DaoException {
        List<CustomBook> actual = bookListDao
                .findAllOrderBy("title");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByAuthorTest(List<CustomBook> expected)
            throws DaoException {
        List<CustomBook> actual = bookListDao
                .findAllOrderBy("authors");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByNumberPages",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByPagesTest(List<CustomBook> expected)
            throws DaoException {
        List<CustomBook> actual = bookListDao
                .findAllOrderBy("number_pages");
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTypography",
            dataProviderClass = SortDataProvider.class)
    public void findAllOrderByTypographyTest(List<CustomBook> expected)
            throws DaoException {
        List<CustomBook> actual = bookListDao
                .findAllOrderBy("typography");
        assertEquals(actual, expected);
    }
}