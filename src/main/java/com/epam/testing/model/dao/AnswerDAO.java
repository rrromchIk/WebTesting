package com.epam.testing.model.dao;

import com.epam.testing.model.connectionPool.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Answer DAO interface
 *
 * @author rom4ik
 */

public interface AnswerDAO {
    DBManager DB_MANAGER = DBManager.getInstance();

    static List<String> getAllByQuestionId(long questionId, String table) {
        List<String> answers = new ArrayList<>();
        String query = AnswerQueries.GET_BY_QUESTION_ID.QUERY.replace("tableName", table);
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                answers.add(resultSet.getString(AnswerFields.TEXT.FIELD));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    static boolean create(long questionId, String text, String table) {
        boolean result = false;
        String query = AnswerQueries.INSERT.QUERY.replace("tableName", table);
        try(Connection connection = DB_MANAGER.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong( 1, questionId);
            statement.setString(2, text);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    static boolean update(long questionId, String text, String table) {
        boolean result = false;
        String query = AnswerQueries.UPDATE.QUERY.replace("tableName", table);
        try(Connection connection  = DB_MANAGER.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, questionId);
            statement.setString( 2, text);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    static boolean delete(String text, String table) {
        boolean result = false;
        String query = AnswerQueries.DELETE.QUERY.replace("tableName", table);
        try (Connection connection = DB_MANAGER.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(2, text);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum AnswerQueries {
        INSERT("INSERT INTO tableName(question_id, text) VALUES(?, ?)"),
        UPDATE("UPDATE tableName SET text = ? WHERE text = ?"),
        DELETE("DELETE FROM tableName WHERE text = ?"),
        GET_BY_QUESTION_ID("SELECT text FROM tableName WHERE question_id = ?");

        public final String QUERY;
        AnswerQueries(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    enum AnswerFields{
        QUESTION_ID("question_id"),
        TEXT("text");

        public final String FIELD;
        AnswerFields(String field) {
            this.FIELD = field;
        }
    }
}
