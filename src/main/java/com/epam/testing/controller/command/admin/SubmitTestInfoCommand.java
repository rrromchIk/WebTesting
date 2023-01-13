package com.epam.testing.controller.command.admin;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.Test;
import com.epam.testing.model.entity.TestDifficulty;
import com.epam.testing.model.service.TestsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitTestInfoCommand implements Command {
    private final TestsService testsService = new TestsService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.COMMAND_ADD_QUESTIONS;
        boolean redirect = true;

        Test test = new Test.TestBuilder()
                .name(req.getParameter("name"))
                .subject(req.getParameter("subject"))
                .difficulty(TestDifficulty.getEnum(Integer.parseInt(req.getParameter("difficulty"))))
                .duration(Integer.parseInt(req.getParameter("duration")))
                .numberOfQuestions(Integer.parseInt(req.getParameter("numOfQuestions")))
                .build();
        System.out.println(test);


        if(testsService.createTest(test)) {
            page += "&testId=" + test.getId();
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "Unable to create test";
            req.setAttribute("errorMessage", errorMessage);
            redirect = false;
        }

        return new DispatchInfo(redirect, page);
    }
}
