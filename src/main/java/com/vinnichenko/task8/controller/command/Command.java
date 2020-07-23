package com.vinnichenko.task8.controller.command;

import java.util.Map;

public interface Command {
    Map<String, String> execute(Map<String, String> parameters);
}
