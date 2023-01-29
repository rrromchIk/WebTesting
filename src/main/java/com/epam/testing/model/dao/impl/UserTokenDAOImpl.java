package com.epam.testing.model.dao.impl;

import com.epam.testing.model.connection.DataSource;
import com.epam.testing.model.dao.UserTokenDAO;
import com.epam.testing.model.entity.user.UserToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserTokenDAOImpl implements UserTokenDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserTokenDAOImpl.class);
    private final DataSource datasource;

    public UserTokenDAOImpl() {
        datasource = DataSource.getInstance();
    }
    public UserTokenDAOImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public boolean create(UserToken userToken) {
        boolean result = false;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     UserTokenQueries.CREATE.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userToken.getUserId());
            statement.setString(2, userToken.getToken());
            statement.setTimestamp(3, userToken.getExpirationDate());

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UserToken read(String token) {
        UserToken userToken = null;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserTokenQueries.READ.QUERY)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userToken = new UserToken.UserTokenBuilder()
                        .userId(resultSet.getLong(UserTokenFields.USER_ID.FIELD))
                        .token(resultSet.getString(UserTokenFields.TOKEN.FIELD))
                        .expirationDate(resultSet.getTimestamp(UserTokenFields.EXPIRATION_DATE.FIELD))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return userToken;
    }

    enum UserTokenQueries {
        CREATE("INSERT INTO user_token " +
                "VALUES(?, ?, ?)"),
        READ("SELECT * FROM user_token WHERE token = ?");

        final String QUERY;
        UserTokenQueries(String query) {
            this.QUERY = query;
        }
    }

    enum UserTokenFields {
        USER_ID("user_id"),
        TOKEN("token"),
        EXPIRATION_DATE("expiration_date");

        final String FIELD;
        UserTokenFields(String field) {
            this.FIELD = field;
        }
    }
}
