package com.vinnichenko.task8.model.dao;

import com.vinnichenko.task8.exception.DaoException;
import com.vinnichenko.task8.model.entity.CustomBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookListDao {

    boolean add(CustomBook customBook) throws DaoException;

    void remove(int id) throws DaoException;

    Optional<CustomBook> findById(int id) throws DaoException;

    List<CustomBook> findByTitle(String title) throws DaoException;

    default void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("can't close prepareStatement" + e.getMessage());
            }
        }
    }

    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("can't close resultSet" + e.getMessage());
            }
        }
    }
}
