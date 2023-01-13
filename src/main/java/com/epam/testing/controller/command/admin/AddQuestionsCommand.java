package com.epam.testing.controller.command.admin;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.Test;
import com.epam.testing.model.service.TestQuestionService;
import com.epam.testing.model.service.TestsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuestionsCommand implements Command {
    private final TestsService testsService = new TestsService();
    private final TestQuestionService testQuestionService = new TestQuestionService();

    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.PAGE_ADD_QUESTIONS;

        long testId = Long.parseLong(req.getParameter("testId"));
        Test test = testsService.getTestById(testId);

        int amountOfAddedQuestions = testQuestionService.getAmountOfAddedQuestions(test.getId());
        int amountOfQuestionsInTheTest = test.getNumberOfQuestions();

        if(amountOfAddedQuestions >= amountOfQuestionsInTheTest) {
            page = Path.COMMAND_ADMIN_MAIN;
        } else {
            req.setAttribute("questionNumber", amountOfAddedQuestions + 1);
            req.setAttribute("fullTest", test);
            req.setAttribute("testId", testId);
        }

        return new DispatchInfo(false, page);
    }
}
