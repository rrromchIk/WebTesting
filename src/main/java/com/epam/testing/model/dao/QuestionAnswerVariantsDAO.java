package com.epam.testing.model.dao;

import java.util.List;

/** Question answer variants DAO interface
 *
 * @author rom4ik
 */

public interface QuestionAnswerVariantsDAO {
    List<String> getAllByQuestionId(long id);
    boolean create(long id, String text);
}
