package com.epam.testing.controller.command.admin.users;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.UserStatus;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserStatusCommand implements Command {
    private final UserService userService = new UserService();
    private static final UserStatus ACTIVE_STATUS = UserStatus.ACTIVE;
    private static final UserStatus BLOCKED_STATUS = UserStatus.BLOCKED;

    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        int activePage = (int)req.getSession().getAttribute("activePage");
        long userId = Long.parseLong(req.getParameter("userId"));

        String page = Path.COMMAND_ADMIN_MAIN + "&tab=users&page=" + activePage;
        boolean redirect = true;

        User user = userService.getUserById(userId);
        if(user != null) {
            UserStatus userStatus = user.getStatus();
            user.setStatus(userStatus == UserStatus.ACTIVE ? BLOCKED_STATUS : ACTIVE_STATUS);
            if(!userService.updateUser(user)) {
                page = Path.PAGE_ERROR_PAGE;
                String errorMessage = "Update user's status failed";
                req.setAttribute("commandToGoBack", Path.COMMAND_ADMIN_MAIN);
                req.setAttribute("errorMessage", errorMessage);
                redirect = false;
            }
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "User with such id not found";
            req.setAttribute("commandToGoBack", Path.COMMAND_ADMIN_MAIN);
            req.setAttribute("errorMessage", errorMessage);
            redirect = false;
        }
        return new DispatchInfo(redirect, page);
    }
}
