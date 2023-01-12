package com.epam.testing.controller.command.admin;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuestionsCommand implements Command {
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "";
        return new DispatchInfo(false, page);
    }
}
