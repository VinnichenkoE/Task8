package com.vinnichenko.task8.controller.command.impl;

import com.vinnichenko.task8.controller.ResponseParameter;
import com.vinnichenko.task8.controller.command.Command;

import java.util.HashMap;
import java.util.Map;

public class WrongCommand implements Command {
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        Map<String, Object> response = new HashMap<>();
        response.put(ResponseParameter.STATUS, ResponseParameter.FAIL_STATUS);
        response.put(ResponseParameter.MESSAGE,
                ResponseParameter.FAIL_MESSAGE);
        return response;
    }
}
