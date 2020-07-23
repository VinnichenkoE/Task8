package com.vinnichenko.task8.model.service;


import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.entity.CustomBook;

import java.util.List;

public interface BookService {

    boolean add(String title, String author,
                String numberPages, String typography) throws ServiceException;

    CustomBook findById(int id) throws ServiceException;

    void removeBook(int id) throws ServiceException;

    List<CustomBook> findByTitle(String title) throws ServiceException;
}
