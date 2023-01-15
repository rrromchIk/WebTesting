package com.epam.testing.model.service;

import com.epam.testing.model.dao.impl.UserDAOImpl;
import com.epam.testing.model.entity.User;
import com.epam.testing.model.entity.UserStatus;

import java.util.List;

public class UserService {
    private static final UserDAOImpl dao = new UserDAOImpl();

    public boolean userIsBlocked(String login) {
        return dao.getByLogin(login).getStatus().equals(UserStatus.BLOCKED);
    }

    public int getAmountOfUsers() {
        return dao.getAmountOfRecords();
    }

    public List<User> getAllUsers(int limit, int offset) {
        return dao.getAll(limit, offset);
    }

    public User getUserById(long id) {
        return dao.getById(id);
    }

    public User getUserByLogin(String login) {
        return dao.getByLogin(login);
    }

    public boolean userExists(String login, String password) {
        return dao.getByLoginAndPassword(login, password) != null;
    }

    public boolean addUser(User user) {
        return dao.create(user) != -1;
    }

    public boolean updateUser(User user) {
        return dao.update(user);
    }
}
