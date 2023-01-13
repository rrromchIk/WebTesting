package com.epam.testing.model.service;

import com.epam.testing.model.dao.QuestionDAO;
import com.epam.testing.model.dao.impl.CorrectAnswerDAOImpl;
import com.epam.testing.model.dao.impl.QuestionDAOImpl;
import com.epam.testing.model.dao.impl.AnswerVariantDAOImpl;
import com.epam.testing.model.entity.CheckedAnswer;
import com.epam.testing.model.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public class TestQuestionService {
    private final QuestionDAO questionDAO = new QuestionDAOImpl();
    private final AnswerVariantDAOImpl answerVariantDAO = new AnswerVariantDAOImpl();
    private final CorrectAnswerDAOImpl correctAnswerDAO = new CorrectAnswerDAOImpl();
    private final UserAnswerService userAnswerService = new UserAnswerService();

    public int getAmountOfAddedQuestions(long testId) {
        return questionDAO.getAmountOfQuestionsByTestId(testId);
    }

    public List<Question> getQuestionsByTestId(long testId) {
        return questionDAO.getQuestionsByTestId(testId);
    }

    public List<String> getAnswerVariants(long questionId) {
        return answerVariantDAO.getAnswerVariantsByQuestionId(questionId);
    }

    public List<String> getCorrectAnswers(long questionId) {
        return correctAnswerDAO.getCorrectAnswersByQuestionId(questionId);
    }

    public boolean addQuestionAnswer(long questionId, String text) {
        return answerVariantDAO.create(questionId, text);
    }

    public boolean addCorrectAnswer(long questionId, String text) {
        return correctAnswerDAO.create(questionId, text);
    }

    public List<CheckedAnswer> getAnswerVariantsByQuestionIdWithCheckedStatus(long userId, long questionId) {
        List<String> allAnswersToQuestion = answerVariantDAO.getAnswerVariantsByQuestionId(questionId);
        List<String> userAnswers = userAnswerService.getUsersAnswers(userId, questionId);

        return allAnswersToQuestion.stream().map(answerStr -> {
            boolean checked = userAnswers.contains(answerStr);
            return new CheckedAnswer(answerStr, checked);
        }).collect(Collectors.toList());
    }

    public boolean addQuestionToTheTest(long testId, Question question) {
        long id = questionDAO.create(testId, question);
        question.setId(id);
        return id != -1;
    }
}
