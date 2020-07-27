package com.vinnichenko.task8.validator;

public class DataValidator {

    private static final int MAX_PAGES = 1000;
    private static final int MAX_TITLE_LENGTH = 45;
    private static final int MAX_AUTHORS_LENGTH = 45;
    private static final int MAX_TYPOGRAPHY_LENGTH = 45;
    private static final String REGEX_DIGITS = "\\d+";
    private static final String REGEX_AUTHORS = "^(\\p{LC}+\\s\\p{LC}+\\s*)++$";

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
        boolean result = false;
        if (authors.matches(REGEX_AUTHORS)
                && authors.length() <= MAX_AUTHORS_LENGTH) {
            result = true;
        }
        return result;
    }

    public boolean isTitleValid(String title) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    public boolean isTypographyValid(String typography) {
        return typography.length() <= MAX_TYPOGRAPHY_LENGTH;
    }
}
