package com.epam.testing.controller.command.common;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final UserService userService = new UserService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean redirect;
        String page;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(userService.isUserExist(login, password)) {
            User user = userService.getByLogin(login);
            if(login.equals("admin") && password.equals("admin")) {
                page = Path.COMMAND_ADMIN_MAIN;
            } else {
                page = Path.COMMAND_USER_MAIN;
            }
            redirect = true;

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userId", user.getId());
            httpSession.setAttribute("login", user.getLogin());
            httpSession.setAttribute("userRole", user.getRole());
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "No such user";
            req.setAttribute("errorMessage", errorMessage);
            redirect = false;
        }

        return new DispatchInfo(redirect, page);
    }
}
