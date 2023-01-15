package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements Command {
    private final UserService userService = new UserService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.PAGE_USER_PROFILE;

        HttpSession httpSession = req.getSession();
        String userLogin = (String)httpSession.getAttribute("login");

        User user = userService.getUserByLogin(userLogin);
        req.setAttribute("fullUser", user);

        return new DispatchInfo(false, page);
    }
}
