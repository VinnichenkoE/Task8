package com.vinnichenko.task8.model.dao.impl;

import com.vinnichenko.task8.exception.DaoException;
import com.vinnichenko.task8.model.dao.BookListDao;
import com.vinnichenko.task8.model.entity.CustomBook;
import com.vinnichenko.task8.util.ConnectorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookListDaoImpl implements BookListDao {

    private static final BookListDaoImpl INSTANCE = new BookListDaoImpl();

    private final static String SQL_INSERT = "INSERT INTO warehouse " +
            "(id, title, authors, number_pages, typography) " +
            "VALUES (? , ?, ? ,?, ?)";
    private final static String SQL_DELETE = "DELETE FROM warehouse WHERE id = ?";
    private final static String SQL_SELECT_BY_ID = "SELECT " +
            "title, authors, number_pages, typography " +
            "FROM warehouse WHERE id = ?";
    private final static String SQL_SELECT_BY_TITLE = "SELECT " +
            "id, authors, number_pages, typography " +
            "FROM warehouse WHERE title = ?";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_TITLE = "title";
    private final static String COLUMN_AUTHORS = "authors";
    private final static String COLUMN_NUMBER_PAGES = "number_pages";
    private final static String COLUMN_TYPOGRAPHY = "typography";

    private BookListDaoImpl() {
    }

    public static BookListDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(CustomBook customBook) throws DaoException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.getConnection()) {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, customBook.getId());
            preparedStatement.setString(2, customBook.getTitle());
            preparedStatement.setString(3, customBook.getAuthors());
            preparedStatement.setInt(4, customBook.getNumberPages());
            preparedStatement.setString(5, customBook.getTypography());
            return preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.getConnection()) {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            closeStatement(preparedStatement);
        }
    }

    @Override
    public Optional<CustomBook> findById(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectorDB.getConnection()) {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString(COLUMN_TITLE);
                String authors = resultSet.getString(COLUMN_AUTHORS);
                int numberPages = resultSet.getInt(COLUMN_NUMBER_PAGES);
                String typography = resultSet.getString(COLUMN_TYPOGRAPHY);
                CustomBook customBook = new CustomBook(id, title,
                        authors, numberPages, typography);
                return Optional.of(customBook);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
    }

    @Override
    public List<CustomBook> findByTitle(String title) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CustomBook> books = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection()) {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_TITLE);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String authors = resultSet.getString(COLUMN_AUTHORS);
                int numberPages = resultSet.getInt(COLUMN_NUMBER_PAGES);
                String typography = resultSet.getString(COLUMN_TYPOGRAPHY);
                CustomBook customBook = new CustomBook(id, title,
                        authors, numberPages, typography);
                books.add(customBook);
            }
            return books;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
    }

    public void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("can't close prepareStatement" + e.getMessage());
            }
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("can't close resultSet" + e.getMessage());
            }
        }
    }
}
