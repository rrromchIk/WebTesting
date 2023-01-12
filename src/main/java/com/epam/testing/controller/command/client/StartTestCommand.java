package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.TestStatus;
import com.epam.testing.model.service.UserTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StartTestCommand implements Command {
    private final UserTestService userTestService = new UserTestService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.COMMAND_USER_PASS_TEST;
        HttpSession httpSession = req.getSession();

        long userId = (long)httpSession.getAttribute("userId");
        long testId = Long.parseLong(req.getParameter("testId"));

        page += "&testId=" + testId;

        if(userTestService.addTestToUsersTests(userId, testId, Timestamp.valueOf(LocalDateTime.now()))) {
            userTestService.updateUserTestStatus(userId, testId, TestStatus.STARTED);
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "Failed to add test to user collection";
            req.setAttribute("errorMessage", errorMessage);
        }

        return new DispatchInfo(true, page);
    }
}
