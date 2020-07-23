package com.vinnichenko.task8.controller.command.impl;

import com.vinnichenko.task8.controller.ResponseParameters;
import com.vinnichenko.task8.controller.command.Command;

import java.util.HashMap;
import java.util.Map;

public class WrongCommand implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        Map<String, String> response = new HashMap<>();
        response.put(ResponseParameters.STATUS, ResponseParameters.FAIL_STATUS);
        response.put(ResponseParameters.MESSAGE,
                ResponseParameters.FAIL_MESSAGE);
        return response;
    }
}
