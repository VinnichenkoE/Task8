package com.vinnichenko.task8.controller.command.impl;

import com.vinnichenko.task8.controller.RequestParameter;
import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.entity.CustomBook;
import com.vinnichenko.task8.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllOrderByCommand implements Command {
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String columnName = parameters.get(RequestParameter.COLUMN_NAME);
        Map<String, Object> response = new HashMap<>();
        try {
            List<CustomBook> books = service.findAllOrderBy(columnName);
            response.put(ResponseParameter.STATUS,
                    ResponseParameter.SUCCESS_STATUS);
            response.put(ResponseParameter.RESULT, books);
        } catch (ServiceException e) {
            response.put(ResponseParameter.STATUS,
                    ResponseParameter.FAIL_STATUS);
            response.put(ResponseParameter.MESSAGE, e.getMessage());
        }
        return response;
    }
}
