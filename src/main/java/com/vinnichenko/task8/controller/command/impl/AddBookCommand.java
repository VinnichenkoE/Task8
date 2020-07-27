package com.vinnichenko.task8.controller.command.impl;

import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class AddBookCommand implements Command {

    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String title = parameters.get(RequestParameter.TITLE);
        String stringAuthors = parameters
                .get(RequestParameter.AUTHORS);
        String stringNumberPages = parameters
                .get(RequestParameter.NUMBER_PAGES);
        String typography = parameters
                .get(RequestParameter.TYPOGRAPHY);
        Map<String, Object> response = new HashMap<>();
        try {
            service.add(title, stringAuthors, stringNumberPages, typography);
            response.put(ResponseParameter.STATUS,
                    ResponseParameter.SUCCESS_STATUS);
        } catch (ServiceException e) {
            response.put(ResponseParameter.STATUS,
                    ResponseParameter.FAIL_STATUS);
            response.put(ResponseParameter.MESSAGE, e.getMessage());
        }
        return response;
    }
}
