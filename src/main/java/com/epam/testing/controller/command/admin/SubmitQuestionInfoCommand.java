package com.epam.testing.controller.command.admin;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.Question;
import com.epam.testing.model.entity.QuestionType;
import com.epam.testing.model.service.TestQuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SubmitQuestionInfoCommand implements Command {
    private final TestQuestionService testQuestionService = new TestQuestionService();
    private static final int MAX_AMOUNT_OF_ANSWER_VARIANTS = 10;
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String command = Path.COMMAND_ADD_QUESTIONS;

        long testId = Long.parseLong(req.getParameter("testId"));
        String questionText = req.getParameter("questionText");
        int maxScore = 1;
        List<String> answers = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        QuestionType questionType = setQuestionInfo(req, answers, correctAnswers);

        Question question = new Question.QuestionBuilder()
                .text(questionText)
                .maxScore(maxScore)
                .type(questionType)
                .build();

        testQuestionService.addQuestionToTheTest(testId, question);
        answers.forEach(answer -> testQuestionService.addQuestionAnswer(question.getId(), answer));
        correctAnswers.forEach(correctAnswer -> testQuestionService.addCorrectAnswer(question.getId(), correctAnswer));

        command += "&testId=" + testId;
        return new DispatchInfo(true, command);
    }

    private QuestionType setQuestionInfo(HttpServletRequest request,
                                         List<String> answers,
                                         List<String> correctAnswers) {
        int i = 0;
        while(i != MAX_AMOUNT_OF_ANSWER_VARIANTS) {
            String text = request.getParameter("answer" + i);
            String checkBox = request.getParameter("answer" + i + "correct");
            i++;

            if(text != null) {
                answers.add(text);
            }
            if(text != null && checkBox != null) {

                correctAnswers.add(text);
            }
        }

        return  correctAnswers.size() > 1
                ? QuestionType.MULTIPLE_CORRECT_ANSWERS
                : QuestionType.SINGLE_CORRECT_ANSWER;
    }
}
