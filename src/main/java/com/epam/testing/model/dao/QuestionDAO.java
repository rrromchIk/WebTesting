package com.epam.testing.model.dao;

import com.epam.testing.model.entity.Question;
import java.util.List;

/** Question DAO interface
 *
 * @author rom4ik
 */

public interface QuestionDAO {
    List<Question> getQuestionsByTestId(long id);
    long create(long testId, Question question);
    boolean update(Question question);
    boolean delete(Question question);
}
