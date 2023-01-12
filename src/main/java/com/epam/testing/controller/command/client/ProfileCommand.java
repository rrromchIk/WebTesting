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
        String page;
        HttpSession httpSession = req.getSession();

        String userLogin = (String)httpSession.getAttribute("login");
        User user = userService.getByLogin(userLogin);
        req.setAttribute("fullUser", user);
        page = Path.PAGE_USER_PROFILE;

        return new DispatchInfo(false, page);
    }
}
