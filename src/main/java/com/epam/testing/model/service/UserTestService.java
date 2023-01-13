package com.epam.testing.model.service;

import com.epam.testing.model.dao.UserTestDAO;
import com.epam.testing.model.dao.impl.UserTestDAOImpl;
import com.epam.testing.model.entity.Test;
import com.epam.testing.model.entity.TestInfo;
import com.epam.testing.model.entity.TestStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserTestService {
    private final UserTestDAO userTestDao = new UserTestDAOImpl();
    private final TestsService testsService = new TestsService();

    public int getAmountOfUserPassedTests(long userId) {
        return userTestDao.getAmountOfUserPassedTests(userId);
    }

    public boolean addTestToUsersTests(long userId, long testId, Timestamp time) {
        return userTestDao.addUsersTest(userId, testId, time);
    }

    public List<TestInfo> getUserTestsInfo(long userId, int limit, int offset) {
        return userTestDao.getUserTestsInfo(userId, limit, offset);
    }

    public boolean addResultAndEndingTime(long userId, long testId, float result, Timestamp endingTime) {
        return userTestDao.addResultAndEndingTime(userId, testId, result, endingTime);
    }

    public TestStatus getUserTestStatus(long userId, long testId) {
        TestStatus status = userTestDao.getTestStatus(userId, testId);
        if(status == null) {
            status = TestStatus.NOT_STARTED;
        }
        return status;
    }

    public boolean updateUserTestStatus(long userId, long testId, TestStatus status) {
        return userTestDao.updateTestStatus(userId, testId, status);
    }

    public long getRemainingTime(long userId, long testId) {
        Test test = testsService.getTestById(testId);
        int testDuration = test.getDuration();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startingTime = userTestDao.getTestStartingTime(userId, testId).toLocalDateTime();
        LocalDateTime limitTime = startingTime.plusMinutes(testDuration);

        return currentTime.until(limitTime, ChronoUnit.MILLIS);
    }
}
