package com.vinnichenko.task8.controller.command;



import com.vinnichenko.task8.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.ADD_BOOK, new AddBookCommand());
        repository.put(CommandName.REMOVE_BOOK, new RemoveBookCommand());
        repository.put(CommandName.FIND_BY_ID, new FindByIdCommand());
        repository.put(CommandName.FIND_BY_TITLE, new FindByTitleCommand());
        repository.put(CommandName.WRONG_COMMAND, new WrongCommand());
    }

    public Command getCommand(String commandName) {
        Command command;
        try {
            command = repository
                    .get(CommandName.valueOf(commandName.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_COMMAND);
        }
        return command;
    }
}