package com.vinnichenko.task8.controller.command.impl;

import com.vinnichenko.task8.controller.RequestParameters;
import com.vinnichenko.task8.controller.ResponseParameters;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class AddBookCommand implements Command {

    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String title = parameters.get(RequestParameters.PARAMETER_TITLE);
        String stringAuthors = parameters
                .get(RequestParameters.PARAMETER_AUTHORS);
        String stringNumberPages = parameters
                .get(RequestParameters.PARAMETER_NUMBER_PAGES);
        String typography = parameters
                .get(RequestParameters.PARAMETER_TYPOGRAPHY);
        Map<String, String> response = new HashMap<>();
        try {
            service.add(title, stringAuthors, stringNumberPages, typography);
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.SUCCESS_STATUS);
        } catch (ServiceException e) {
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.FAIL_STATUS);
            response.put(ResponseParameters.MESSAGE, e.getMessage());
        }
        return response;
    }
}
