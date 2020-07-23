package com.vinnichenko.task8.model.service.impl;

import com.vinnichenko.task8.exception.DaoException;
import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.dao.BookListDao;
import com.vinnichenko.task8.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task8.model.entity.CustomBook;
import com.vinnichenko.task8.model.service.BookService;
import com.vinnichenko.task8.validator.DataValidator;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private static final BookServiceImpl INSTANCE = new BookServiceImpl();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(String title, String authors,
                       String numberPages, String typography)
            throws ServiceException {
        if (title == null || authors == null || typography == null
                || numberPages == null) {
            throw new ServiceException("incorrect parameters");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isNumberPagesValid(numberPages)
                || !dataValidator.isAuthorValid(authors)) {
            throw new ServiceException("incorrect parameters");
        }
        int intNumberPages = Integer.parseInt(numberPages);

        CustomBook customBook = new CustomBook(title, authors,
                intNumberPages, typography);
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        boolean result;
        try {
            result = bookListDao.add(customBook);
        } catch (DaoException e) {
            throw new ServiceException("can not add book", e);
        }
        return result;
    }

    @Override
    public CustomBook findById(int id) throws ServiceException {
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        try {
            Optional<CustomBook> optionalBook = bookListDao.findById(id);
            if (!optionalBook.isPresent()) {
                throw new ServiceException("no such book in warehouse");
            }
            CustomBook result = optionalBook.get();
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void removeBook(int id) throws ServiceException {
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        CustomBook customBook = findById(id);
        try {
            bookListDao.remove(id);
        } catch (DaoException e) {
            throw new ServiceException("can not remove book", e);
        }
    }

    @Override
    public List<CustomBook> findByTitle(String title) throws ServiceException {
        if (title == null) {
            throw new ServiceException("title is null");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        try {
            List<CustomBook> result = bookListDao.findByTitle(title);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }
}