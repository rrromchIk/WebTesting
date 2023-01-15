package com.epam.testing.model.dao.impl;

import com.epam.testing.model.connectionPool.DBManager;
import com.epam.testing.model.dao.QuestionAnswerVariantsDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerVariantsDAOImpl implements QuestionAnswerVariantsDAO {
    private final DBManager datasource = DBManager.getInstance();

    /**
     * Select all answers variants of question.
     *
     * @param id for identification
     * @return valid list of answers if they exist. If not return empty list.
     */

    public List<String> getAllByQuestionId(long id) {
        List<String> answers = new ArrayList<>();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AnswerQueries.GET_ALL_BY_ID.QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                answers.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    /**
     * Create Correct Answer in database.
     *
     * @param questionId for identification.
     * @param text for answer text
     * @return false if Correct Answer already exist. If creating success - true.
     */
    public boolean create(long questionId, String text) {
        boolean result = false;
        try(Connection connection = datasource.getConnection();
            PreparedStatement statement = connection.prepareStatement(AnswerQueries.INSERT.QUERY)) {
            statement.setLong( 1, questionId);
            statement.setString(2, text);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum AnswerQueries {
        INSERT("INSERT INTO question_answer_variants(question_id, text) VALUES(?, ?)"),
        GET_ALL_BY_ID("SELECT text FROM question_answer_variants WHERE question_id = ?");

        public final String QUERY;
        AnswerQueries(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
