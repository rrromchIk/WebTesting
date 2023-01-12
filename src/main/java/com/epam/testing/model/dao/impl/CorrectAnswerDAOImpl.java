package com.epam.testing.model.dao.impl;

import com.epam.testing.model.dao.AnswerDAO;
import java.util.List;

public class CorrectAnswerDAOImpl implements AnswerDAO {
    private static final String TABLE_NAME = "question_correct_answer";

    /**
     * Select all correct answers of question.
     *
     * @param questionId identification
     * @return valid list of correct answers if they exist. If not return empty list.
     */
    public List<String> getCorrectAnswersByQuestionId(long questionId) {
        return AnswerDAO.getAllByQuestionId(questionId, TABLE_NAME);
    }

    /**
     * Create Correct Answer in database.
     *
     * @param questionId for identification.
     * @param text for answer text
     * @return false if Correct Answer already exist. If creating success - true.
     */
    public boolean create(long questionId, String text){
        return AnswerDAO.create(questionId, text, TABLE_NAME);
    }

    /**
     * Update Correct Answer's text by previous text.
     *
     * @param questionId for identification.
     * @param text new text.
     * @return True if success. False if fails.
     */
    public boolean update(long questionId, String text){
       return AnswerDAO.update(questionId, text, TABLE_NAME);
    }


    public boolean delete(String text){
        return AnswerDAO.delete(text, TABLE_NAME);
    }
}
