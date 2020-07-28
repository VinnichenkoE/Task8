package test.vinnichenko.task8.dataprovider;

import com.vinnichenko.task8.model.entity.CustomBook;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class SortDataProvider {
    private static CustomBook book;
    private static CustomBook book2;
    private static CustomBook book3;
    private static CustomBook book4;

    static {
        book = new CustomBook(1, "War and Peace",
                "Lev Tolstoy", 581, "Moscow");
        book2 = new CustomBook(2, "Evgeniy Onegin",
                "Alexander Pushkin", 250, "Tver");
        book3 = new CustomBook(4, "Hunters Notes",
                "Ivan Tyrgenev", 125,
                "Konica Minolta");
        book4 = new CustomBook(3, "Master and Margarita",
                "Mihail Bylgakov", 350,
                "Contemporary");
    }

    @DataProvider(name = "sortById")
    public static Object[][] createDataSortById() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book4);
        books.add(book3);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByTitle")
    public static Object[][] createDataSortByTitle() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByAuthor")
    public static Object[][] createDataSortByAuthor() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book2);
        books.add(book3);
        books.add(book);
        books.add(book4);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByNumberPages")
    public static Object[][] createDataSortByNumberPages() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book3);
        books.add(book2);
        books.add(book4);
        books.add(book);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByTypography")
    public static Object[][] createDataSortByTypography() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book4);
        books.add(book3);
        books.add(book);
        books.add(book2);
        return new Object[][]{{books}};
    }
}
