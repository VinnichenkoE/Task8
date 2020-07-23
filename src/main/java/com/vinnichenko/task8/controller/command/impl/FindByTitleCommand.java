package com.vinnichenko.task8.controller.command.impl;

import com.vinnichenko.task8.controller.RequestParameters;
import com.vinnichenko.task8.controller.ResponseParameters;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.entity.CustomBook;
import com.vinnichenko.task8.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByTitleCommand implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String title = parameters.get(RequestParameters.PARAMETER_TITLE);
        Map<String, String> response = new HashMap<>();
        try {
            List<CustomBook> books = service.findByTitle(title);
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.SUCCESS_STATUS);
            response.put(ResponseParameters.RESULT, books.toString());
        } catch (ServiceException e) {
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.FAIL_STATUS);
            response.put(ResponseParameters.MESSAGE, e.getMessage());
        }
        return response;
    }
}
