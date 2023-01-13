package com.epam.testing.model.service;

import com.epam.testing.model.dao.TestDAO;
import com.epam.testing.model.dao.impl.TestDAOImpl;
import com.epam.testing.model.entity.Test;

import java.sql.Timestamp;
import java.util.List;

public class TestsService {
    private final TestDAO dao = new TestDAOImpl();

    public Test getTestById(long testId) {
        return dao.getById(testId);
    }

    public Test getTestByName(String name) {
        return dao.getTestByName(name);
    }

    public int getAmountOfTests() {
        return dao.getAmountOfRecords();
    }

    public int getAmountOfTestsOnParticularSubject(String subject) {
        return dao.getAmountOfTestsOnParticularSubject(subject);
    }

    public List<Test> getAllTests(int limit, int offset) {
        return dao.getAll(limit, offset);
    }

    public List<Test> getTestsSortedByNumberOfQuestions(int limit, int offset) {
        return dao.getTestsSortedByNumberOfQuestions(limit, offset);
    }

    public List<Test> getTestsSortedByName(int limit, int offset) {
        return dao.getTestsSortedByName(limit, offset);
    }

    public List<Test> getTestsSortedByDifficulty(int limit, int offset) {
        return dao.getTestsSortedByDifficulty(limit, offset);
    }

    public List<Test> getTestsOnParticularSubject(String subject, int limit, int offset) {
        return dao.getTestsOnParticularSubject(subject, limit, offset);
    }

    public List<String> getAllSubjects() {
        return dao.getAllTestsSubjects();
    }

    public boolean deleteTest(long testId) {
        Test test = dao.getById(testId);
        return dao.delete(test);
    }

    public boolean createTest(Test test) {
        long id = dao.create(test);
        test.setId(id);
        return id != -1;
    }
}
