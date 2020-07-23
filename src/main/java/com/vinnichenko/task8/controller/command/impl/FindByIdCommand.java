package com.vinnichenko.task8.controller.command.impl;



import com.vinnichenko.task8.controller.RequestParameters;
import com.vinnichenko.task8.controller.ResponseParameters;
import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.exception.ServiceException;
import com.vinnichenko.task8.model.entity.CustomBook;
import com.vinnichenko.task8.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class FindByIdCommand implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String stringId = parameters.get(RequestParameters.ID);
        int id = Integer.parseInt(stringId);
        Map<String, String> response = new HashMap<>();
        try {
            CustomBook customBook = service.findById(id);
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.SUCCESS_STATUS);
            response.put(ResponseParameters.RESULT, customBook.toString());
        } catch (ServiceException e) {
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.FAIL_STATUS);
            response.put(ResponseParameters.MESSAGE, e.getMessage());
        }
        return response;
    }
}
