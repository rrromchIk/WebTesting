package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.service.UserAnswerService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitAnswersCommand implements Command {
    private final UserAnswerService userAnswerService = new UserAnswerService();
    private static final int MAX_AMOUNT_OF_ANSWER_VARIANTS = 10;
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.COMMAND_USER_PASS_TEST;

        long questionId = Long.parseLong(req.getParameter("questionId"));
        long userId = (long) req.getSession().getAttribute("userId");
        long testId = Long.parseLong(req.getParameter("testId"));

        int i = 0;
        userAnswerService.deleteUserAnswers(userId, questionId);
        while(i != MAX_AMOUNT_OF_ANSWER_VARIANTS) {
            String text = req.getParameter("answer" + i);
            i++;

            if(text != null) {
                userAnswerService.addUserAnswer(userId, questionId, text);
            }
        }

        page += "&testId=" + testId;
        page += "&renderQuestion=" + questionId;
        return new DispatchInfo(true, page);
    }
}
