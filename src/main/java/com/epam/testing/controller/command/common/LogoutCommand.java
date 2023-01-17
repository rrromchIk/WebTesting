package com.epam.testing.controller.command.common;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public DispatchInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.setAttribute("userRole", UserRole.GUEST);
            session.setAttribute("login", "");
        }
        return new DispatchInfo(false, Path.PAGE_INDEX);
    }
}
