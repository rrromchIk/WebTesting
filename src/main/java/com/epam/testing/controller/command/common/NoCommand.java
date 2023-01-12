package com.epam.testing.controller.command.common;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.PAGE_ERROR_PAGE;
        String errorMessage = "No such command";
        req.setAttribute("errorMessage", errorMessage);
        return new DispatchInfo(false, page);
    }
}
