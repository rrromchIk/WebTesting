package com.epam.testing.model.dao;

import java.util.List;

/** UserAnswer DAO interface
 *
 * @author rom4ik
 */
public interface UserAnswerDAO {
    List<String> getUserAnswers(long userId, long questionId);
    boolean addUserAnswer(long userId, long questionId, String text);
    boolean deleteUserAnswer(long userId, long questionId);
}
