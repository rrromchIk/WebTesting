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
    int getAmountOfRecords(long userId);
    Timestamp getStartingTime(long userId, long testId);
    boolean create(long userId, long testId, Timestamp startingTime);
    boolean addResultAndEndingTime(long userId, long testId, Float testResult, Timestamp endingTime);
    List<TestInfo> getTestsInfo(long userId, int limit, int offset);
    TestStatus getStatus(long userId, long testId);
    boolean updateStatus(long userId, long testId, TestStatus status);
}
