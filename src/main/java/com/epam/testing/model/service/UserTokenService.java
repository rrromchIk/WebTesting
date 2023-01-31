package com.epam.testing.model.service;

import com.epam.testing.model.dao.UserTokenDAO;
import com.epam.testing.model.dao.impl.UserTokenDAOImpl;
import com.epam.testing.model.entity.user.UserToken;

public class UserTokenService {
    private final UserTokenDAO dao = new UserTokenDAOImpl();

    public boolean addUserToken(UserToken userToken) {
       return dao.create(userToken);
    }

    public UserToken getUserToken(String token) {
        return dao.read(token);
    }
}
