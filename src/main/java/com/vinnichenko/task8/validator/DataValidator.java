package com.vinnichenko.task8.validator;

public class DataValidator {

    private static final int MAX_PAGES = 1000;
    private static final String REGEX_DIGITS = "\\d+";
    private static final String REGEX_AUTHOR = "^(\\p{LC}+\\s\\p{LC}+\\s*)++$";

    public boolean isNumberPagesValid(String numberPages) {
        boolean result = false;
        if (isDigit(numberPages)) {
            int intNumberPages = Integer.parseInt(numberPages);
            result = intNumberPages > 0 && intNumberPages < MAX_PAGES;
        }
        return result;
    }

    public boolean isDigit(String number) {
        return number.matches(REGEX_DIGITS);
    }

    public boolean isAuthorValid(String authors) {
        return authors.matches(REGEX_AUTHOR);
    }
}
