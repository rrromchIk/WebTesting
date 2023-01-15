package com.epam.testing.controller.command.admin.users;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoCommand implements Command {
    private final UserService userService = new UserService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page;

        long userId = Long.parseLong(req.getParameter("userId"));
        User user = userService.getUserById(userId);

        if(user != null) {
            req.setAttribute("fullUser", user);
            page = Path.PAGE_USER_INFO;
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "User not found";
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("commandToGoBack", Path.COMMAND_ADMIN_MAIN);
        }

        return new DispatchInfo(false, page);
    }
}
