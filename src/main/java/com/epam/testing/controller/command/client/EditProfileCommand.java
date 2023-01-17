package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfileCommand implements Command {
    private final UserService userService = new UserService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getContextPath() + Path.COMMAND_USER_PROFILE;
        boolean redirect = true;

        String login = req.getParameter("login");
        String newName = req.getParameter("name");
        String newSurname= req.getParameter("surname");
        String newEmail = req.getParameter("email");

        User user = userService.getUserByLogin(login);
        user.setName(newName);
        user.setSurname(newSurname);
        user.setEmail(newEmail);

        if(!userService.updateUser(user)) {
            String errorMessage = "Can not update";
            req.setAttribute("commandToGoBack", Path.COMMAND_USER_PROFILE);
            req.setAttribute("errorMessage", errorMessage);
            page = Path.PAGE_ERROR_PAGE;
            redirect = false;
        }

        return new DispatchInfo(redirect, page);
    }
}
