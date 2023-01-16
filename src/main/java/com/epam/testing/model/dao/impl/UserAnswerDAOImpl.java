package com.epam.testing.model.dao.impl;

import com.epam.testing.model.connection.DataSource;
import com.epam.testing.model.dao.UserAnswerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** UserAnswerDAOImpl class
 *
 * @author  rom4ik
 */

public class UserAnswerDAOImpl implements UserAnswerDAO {
    private final DataSource datasource = DataSource.getInstance();

    /**Getting User's answers
     *
     * @param userId for identification
     * @param questionId for identification
     * @return valid entity if it exists, else empty list.
     */
    @Override
    public List<String> getUserAnswers(long userId, long questionId) {
        List<String> userAnswers = new ArrayList<>();
        try(Connection connection = datasource.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement(UserAnswerQueries.GET.QUERY)) {
            statement.setLong(1, userId);
            statement.setLong(2, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userAnswers.add(resultSet.getString(UserAnswerFields.TEXT.FIELD));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAnswers;
    }

    @Override
    public boolean create(long userId, long questionId, String text) {
        boolean result = false;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserAnswerQueries.CREATE.QUERY)) {
            statement.setLong(1, userId);
            statement.setLong(2, questionId);
            statement.setString(3, text);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(long userId, long questionId) {
        boolean result = false;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserAnswerQueries.DELETE.QUERY)) {
            statement.setLong(1, userId);
            statement.setLong(2, questionId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum UserAnswerQueries {
        GET("SELECT * FROM user_answer WHERE user_id = ? AND question_id = ?"),
        CREATE("INSERT INTO user_answer(user_id, question_id, text) VALUES(?, ?, ?)"),
        DELETE("DELETE FROM user_answer WHERE user_id = ? AND question_id = ?");

        final String QUERY;
        UserAnswerQueries(String query) {
            this.QUERY = query;
        }
    }

    enum UserAnswerFields {
        USER_ID("user_id"),
        QUESTION_ID("question_id"),
        TEXT("text");

        final String FIELD;
        UserAnswerFields(String field) {
            this.FIELD = field;
        }
    }
}






