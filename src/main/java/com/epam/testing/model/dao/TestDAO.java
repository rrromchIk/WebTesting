package com.epam.testing.model.dao;

import com.epam.testing.model.entity.Test;
import java.util.List;

/** Test DAO interface
 *
 * @author rom4ik
 */

public interface TestDAO extends DAO<Test> {
    Test getTestByName(String name);
    List<Test> getTestsSortedByNumberOfQuestions(int limit, int offset);
    List<Test> getTestsSortedByName(int limit, int offset);
    List<Test> getTestsSortedByDifficulty(int limit, int offset);
    List<Test> getTestsOnParticularSubject(String subject, int limit, int offset);
    List<String> getAllTestsSubjects();
    int getAmountOfTestsOnParticularSubject(String subject);
}
