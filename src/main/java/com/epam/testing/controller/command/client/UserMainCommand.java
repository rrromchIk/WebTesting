package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.entity.Test;
import com.epam.testing.model.entity.TestInfo;
import com.epam.testing.model.entity.TestStatus;
import com.epam.testing.model.service.TestsService;
import com.epam.testing.model.service.UserTestService;
import com.epam.testing.util.PaginationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserMainCommand implements Command {
    private final TestsService testsService = new TestsService();
    private final UserTestService userTestService = new UserTestService();
    private static final Integer RECORDS_ON_PAGE_LIMIT = 3;

    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = Path.PAGE_USER_MAIN;

        String tab = req.getParameter("tab");
        long userId = (Long)req.getSession().getAttribute("userId");

        if(tab == null) {
            tab = "tests";
        }

        if(tab.equals("tests")) {
            processTestsTab(req, userId);
        } else if(tab.equals("passedTests")) {
            processPassedTestsTab(req, userId);
        } else {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "";
            req.setAttribute("errorMessage", errorMessage);
        }

        return new DispatchInfo(false, page);
    }

    private void processTestsTab(HttpServletRequest req, long userId) {
        List<String> subjects = testsService.getAllSubjects();
        String subject = req.getParameter("groupBy");
        String sortingMethod = req.getParameter("sortMethod");

        if(sortingMethod == null && subject == null) {
            sortingMethod = "default";
        }

        int totalNumOfTests;
        if(subject != null) {
            totalNumOfTests = testsService.getAmountOfTestsOnParticularSubject(subject);
        } else {
            totalNumOfTests = testsService.getAmountOfTests();
        }

        int amountOfPages = PaginationService.getNumberOfPages(RECORDS_ON_PAGE_LIMIT, totalNumOfTests);
        String page = req.getParameter("page");
        int pageNumber = PaginationService.getValidPageNumber(page, totalNumOfTests, RECORDS_ON_PAGE_LIMIT);
        int offset = PaginationService.getOffsetOnCertainPage(RECORDS_ON_PAGE_LIMIT, totalNumOfTests, pageNumber);

        List<Test> tests;
        if(subject == null) {
            tests = getTestsBySortedMethod(sortingMethod, offset);
        } else {
            tests = testsService.getTestsOnParticularSubject(subject, RECORDS_ON_PAGE_LIMIT, offset);
        }

        setTestsStatuses(tests, userId);

        req.setAttribute("selectedSubject", subject);
        req.setAttribute("subjects", subjects);
        req.setAttribute("tests", tests);
        req.setAttribute("activeTab", "tests");
        req.setAttribute("amountOfPages", amountOfPages);
        req.setAttribute("activePage", pageNumber);
        req.setAttribute("sortMethod", sortingMethod);
    }

    private void processPassedTestsTab(HttpServletRequest req, long userId) {
        int totalNumOfPassedTests = userTestService.getAmountOfUserPassedTests(userId);
        int amountOfPages = PaginationService.getNumberOfPages(RECORDS_ON_PAGE_LIMIT, totalNumOfPassedTests);
        String page = req.getParameter("page");
        int pageNumber = PaginationService.getValidPageNumber(page, totalNumOfPassedTests, RECORDS_ON_PAGE_LIMIT);
        int offset = PaginationService.getOffsetOnCertainPage(RECORDS_ON_PAGE_LIMIT, totalNumOfPassedTests, pageNumber);

        List<TestInfo> userTests = userTestService.getUserTestsInfo(userId, RECORDS_ON_PAGE_LIMIT, offset);
        req.setAttribute("passedTests", userTests);
        req.setAttribute("activePage", pageNumber);
        req.setAttribute("activeTab", "passedTests");
        req.setAttribute("amountOfPages", amountOfPages);
    }

    private List<Test> getTestsBySortedMethod(String sortingMethod, int offset) {
        List<Test> tests = null;

        switch (sortingMethod) {
            case "default":
                tests = testsService.getAllTests(RECORDS_ON_PAGE_LIMIT, offset);
                break;
            case "name":
                tests = testsService.getTestsSortedByName(RECORDS_ON_PAGE_LIMIT, offset);
                break;
            case "difficulty":
                tests = testsService.getTestsSortedByDifficulty(RECORDS_ON_PAGE_LIMIT, offset);
                break;
            case "numOfQuest":
                tests = testsService.getTestsSortedByNumberOfQuestions(RECORDS_ON_PAGE_LIMIT, offset);
                break;
        }
        return tests;
    }

    private void setTestsStatuses(List<Test> tests, long userId) {
        tests.forEach(test -> {
            TestStatus status = userTestService.getUserTestStatus(userId, test.getId());
            test.setStatus(status);
        });
    }
}
