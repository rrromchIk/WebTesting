package com.epam.testing.model.service;

import com.epam.testing.model.dao.impl.UserAnswerDAOImpl;

import java.util.List;

public class UserAnswerService {
    private final UserAnswerDAOImpl dao = new UserAnswerDAOImpl();

    public List<String> getUsersAnswers(long userId, long questionId) {
        return dao.getUserAnswers(userId, questionId);
    }

    public boolean addUserAnswer(long userId, long questionId, String text) {
        return dao.addUserAnswer(userId, questionId, text);
    }

    public boolean deleteUserAnswers(long userId, long questionId) {
        return dao.deleteUserAnswer(userId, questionId);
    }
}
