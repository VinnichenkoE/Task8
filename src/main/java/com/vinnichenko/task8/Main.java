package com.vinnichenko.task8;

import com.vinnichenko.task8.exception.DaoException;
import com.vinnichenko.task8.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task8.model.entity.CustomBook;

public class Main {
    public static void main(String[] args) throws DaoException {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        CustomBook customBook = new CustomBook("war and peace", "Lev Tolstoy", 581, "Tver");
        CustomBook customBook2 = new CustomBook("Evgeniy Onegin", "Alexander Pushkin", 380, "Moscow");
        bookListDao.remove(2);

}}
