package com.vinnichenko.task8.model.dao;

import com.vinnichenko.task8.exception.DaoException;
import com.vinnichenko.task8.model.entity.CustomBook;

import java.util.List;
import java.util.Optional;

public interface BookListDao {

    boolean add(CustomBook customBook) throws DaoException;

    void remove(int id) throws DaoException;

    Optional<CustomBook> findById(int id) throws DaoException;

    List<CustomBook> findByTitle(String title) throws DaoException;
}
