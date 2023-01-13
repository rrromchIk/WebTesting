package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.*;
import com.epam.testing.model.service.TestQuestionService;
import com.epam.testing.model.service.TestsService;
import com.epam.testing.model.service.UserTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class PassTestCommand implements Command {
    private final TestsService testsService = new TestsService();
    private final TestQuestionService testQuestionService = new TestQuestionService();
    private final UserTestService userTestService = new UserTestService();

    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.PAGE_USER_TEST_PASSING;
        HttpSession httpSession = req.getSession();

        String questionToRender = req.getParameter("renderQuestion");
        long userId = (long)httpSession.getAttribute("userId");
        long testId = Long.parseLong(req.getParameter("testId"));

        Test test = testsService.getTestById(testId);
        List<Question> questions = testQuestionService.getQuestionsByTestId(testId);
        for(int i = 0; i < questions.size(); i++) {
            questions.get(i).setNumber(i + 1);
        }

        if(!questions.isEmpty()) {
            Question currentQuestion = questions.get(0);
            if(isQuestionToRenderValid(questionToRender)) {
                Optional<Question> opt = questions.stream()
                        .filter(question -> question.getId() == Integer.parseInt(questionToRender))
                        .findFirst();
                if(opt.isPresent())
                    currentQuestion = opt.get();
            }

            List<CheckedAnswer> answerVariants = testQuestionService
                    .getAnswerVariantsByQuestionIdWithCheckedStatus(userId, currentQuestion.getId());

            String questionType = getQuestionType(currentQuestion);
            long remainingTime = userTestService.getRemainingTime(userId, testId);

            req.setAttribute("timer", remainingTime);
            req.setAttribute("questionType", questionType);
            req.setAttribute("answers", answerVariants);
            req.setAttribute("test", test);
            req.setAttribute("questions", questions);
            req.setAttribute("questionAmount", questions.size());
            req.setAttribute("currentQuestion", currentQuestion);
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "No questions";
            req.setAttribute("errorMessage", errorMessage);
        }


        return new DispatchInfo(false, page);
    }

    private boolean isQuestionToRenderValid(String questionToRender) {
        try {
            Integer.parseInt(questionToRender);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String getQuestionType(Question question) {
        String result;
        if(question.getType().equals(QuestionType.MULTIPLE_CORRECT_ANSWERS)) {
            result = "checkbox";
        } else {
            result = "radio";
        }
        return result;
    }
}
