package com.epam.testing.model.dao;

import com.epam.testing.model.entity.TestInfo;
import com.epam.testing.model.entity.TestStatus;

import java.sql.Timestamp;
import java.util.List;

/** UserTest DAO interface
 *
 * @author rom4ik
 */

public interface UserTestDAO {
    int getAmountOfUserPassedTests(long userId);
    Timestamp getTestStartingTime(long userId, long testId);
    boolean addUsersTest(long userId, long testId, Timestamp startingTime);
    boolean addResultAndEndingTime(long userId, long testId, Float testResult, Timestamp endingTime);
    List<TestInfo> getUserTestsInfo(long userId, int limit, int offset);
    TestStatus getTestStatus(long userId, long testId);
    boolean updateTestStatus(long userId, long testId, TestStatus status);
}
