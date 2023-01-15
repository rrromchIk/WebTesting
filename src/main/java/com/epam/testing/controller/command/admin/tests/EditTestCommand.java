package com.epam.testing.controller.command.admin.tests;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.Test;
import com.epam.testing.model.entity.TestDifficulty;
import com.epam.testing.model.service.TestsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTestCommand implements Command {
    private final TestsService testsService = new TestsService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.COMMAND_TEST_INFO;
        boolean redirect = true;

        long testId = Long.parseLong(req.getParameter("testId"));

        String subject = req.getParameter("subject");
        TestDifficulty difficulty = TestDifficulty.getEnum(Integer.parseInt(req.getParameter("difficulty")));
        int duration = Integer.parseInt(req.getParameter("duration"));
        int numOfQuestions = Integer.parseInt(req.getParameter("numOfQuestions"));

        Test test = testsService.getTestById(testId);
        test.setSubject(subject);
        test.setDifficulty(difficulty);
        test.setDuration(duration);
        test.setNumberOfQuestions(numOfQuestions);

        if(testsService.updateTest(test)) {
            page += "&testId=" + test.getId();
        } else {
            String command = page;
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "Unable to update test";
            req.setAttribute("commandToGoBack", command);
            req.setAttribute("errorMessage", errorMessage);
            redirect = false;
        }

        return new DispatchInfo(redirect, page);
    }
}
