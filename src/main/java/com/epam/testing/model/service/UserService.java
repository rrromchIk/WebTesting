package com.epam.testing.model.service;

import com.epam.testing.model.dao.impl.UserDAOImpl;
import com.epam.testing.model.entity.User;

import java.util.List;

public class UserService {
    private static final UserDAOImpl dao = new UserDAOImpl();

    public int getAmountOfUsers() {
        return dao.getAmountOfRecords();
    }

    public List<User> getAllUsers(int limit, int offset) {
        return dao.getAll(limit, offset);
    }

    public User getById(long id) {
        return dao.getById(id);
    }

    public User getByLogin(String login) {
        return dao.getByLogin(login);
    }

    public boolean isUserExist(String login, String password) {
        return dao.getByLoginAndPassword(login, password) != null;
    }

    public boolean addUser(User user) {
        return dao.create(user) != -1;
    }

    public boolean updateUser(User user) {
        return dao.update(user);
    }

    public boolean deleteUser(User user) {
        return dao.delete(user);
    }
}
