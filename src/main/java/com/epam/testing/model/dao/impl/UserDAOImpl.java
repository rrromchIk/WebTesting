package com.epam.testing.model.dao.impl;

import com.epam.testing.model.connection.DataSource;
import com.epam.testing.model.dao.UserDAO;
import com.epam.testing.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** UserDAOImpl class
 *
 * @author rom4ik
 */

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private final DataSource datasource = DataSource.getInstance();
    /**
     * Select all Users.
     *
     * @return valid list of users if they exist. If not return empty list.
     */
    @Override
    public List<User> getAll(int limit, int offset) {
        List<User> users = new ArrayList<>();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.GET_ALL.QUERY)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int getAmountOfRecords() {
        int amount = 0;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     UserQueries.GET_AMOUNT_OF_RECORDS.QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return amount;
    }

    /**
     * Select User by id.
     *
     * @param id for select.
     * @return valid entity if it exists. If entity does not exist return null.
     */
    @Override
    public User getById(long id) {
        User user = null;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.GET_BY_ID.QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Create User in database.
     *
     * @param user for create.
     * @return -1 if User already exist. If creating success - User's id.
     */
    @Override
    public long create(User user) {
        long result = -1;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     UserQueries.INSERT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            if(statement.executeUpdate() <= 0) {
                throw new SQLException();
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    /** Map User from ResultSet
     *
     * @param resultSet for getting info
     * @return entity User from database
     * @throws SQLException if something went wrong
     */
    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User.UserBuilder()
                .login(resultSet.getString(UserFields.LOGIN.FIELD))
                .password(resultSet.getString(UserFields.PASSWORD.FIELD))
                .name(resultSet.getString(UserFields.NAME.FIELD))
                .surname(resultSet.getString(UserFields.SURNAME.FIELD))
                .email(resultSet.getString(UserFields.EMAIL.FIELD))
                .role(UserRole.getRole(resultSet.getString(UserFields.ROLE.FIELD)))
                .status(UserStatus.getStatus(resultSet.getString(UserFields.STATUS.FIELD)))
                        .build();
        user.setId(resultSet.getInt(UserFields.ID.FIELD));
        return user;
    }

    /**
     * Update User's info by login.
     *
     * @param user new user's state.
     * @return True if success. False if fails.
     */
    @Override
    public boolean update(User user) {
        boolean result = false;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.UPDATE.QUERY)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getStatus().getName());
            statement.setString(5, user.getLogin());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Select User by login and password.
     *
     * @param login and
     * @param password for select.
     * @return valid entity if it exists. If entity does not exist return null.
     */
    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.GET_BY_LOGIN_AND_PASSWORD.QUERY)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Select User by login.
     *
     * @param login and for select.
     * @return valid entity if it exists. If entity does not exist return null.
     */
    @Override
    public User getByLogin(String login) {
        User user = null;
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.GET_BY_LOGIN.QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    enum UserQueries {
        GET_ALL("SELECT * FROM user LIMIT ? OFFSET ?"),
        GET_BY_ID("SELECT * FROM user WHERE id = ?"),
        GET_BY_LOGIN_AND_PASSWORD("SELECT * FROM user WHERE login = ? AND password = sha1(?)"),
        GET_BY_LOGIN("SELECT * FROM user WHERE login = ?"),
        INSERT("INSERT INTO user(login, password, name, surname, email) " +
                "VALUES(?, sha1(?), ?, ?, ?)"),
        UPDATE("UPDATE user SET name = ?, surname = ?, email = ?, status = ? WHERE login = ?"),
        DELETE("DELETE FROM user WHERE login = ? AND password = ?"),

        GET_AMOUNT_OF_RECORDS("SELECT COUNT(login) FROM user");

        final String QUERY;
        UserQueries(String query) {
            this.QUERY = query;
        }
    }

    enum UserFields {
        ID("id"),
        LOGIN("login"),
        PASSWORD("password"),
        NAME("name"),
        SURNAME("surname"),
        EMAIL ("email"),
        ROLE("role"),
        STATUS("status");

        final String FIELD;
        UserFields(String field) {
            this.FIELD = field;
        }
    }
}

