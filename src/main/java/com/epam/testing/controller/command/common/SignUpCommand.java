package com.epam.testing.controller.command.common;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements Command {
    private final UserService userService = new UserService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page;

        User user = new User.UserBuilder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .build();

        if(userService.addUser(user)) {
            page = Path.PAGE_LOGIN + "?signUpSuccess=true";
        } else {
            page = Path.PAGE_SIGNUP + "?invalid=true";
        }

        return new DispatchInfo(true, page);
    }
}
