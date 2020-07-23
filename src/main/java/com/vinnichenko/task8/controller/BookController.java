package com.vinnichenko.task8.controller;

import com.vinnichenko.task8.controller.command.Command;
import com.vinnichenko.task8.controller.command.CommandProvider;

import java.util.Map;

public class BookController {

    private static final BookController INSTANCE = new BookController();

    private BookController() {
    }

    public static BookController getInstance() {
        return INSTANCE;
    }

    public Map<String, String> executeTask(Map<String, String> parameters) {
        String commandName = parameters
                .get(RequestParameters.PARAMETER_COMMAND_NAME);
        CommandProvider provider = new CommandProvider();
        Command command = provider.getCommand(commandName);
        return command.execute(parameters);
    }
}
