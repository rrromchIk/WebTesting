package com.epam.testing.controller.filter;

import com.epam.testing.controller.Path;
import com.epam.testing.model.entity.TestStatus;
import com.epam.testing.model.service.UserTestService;
import com.epam.testing.util.CalculateTestResultService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebFilter(filterName = "TestAccessFilter",
        urlPatterns = "/controller")
public class TestAccessFilter implements Filter {
    private final UserTestService userTestService = new UserTestService();

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(!action.equals("passTest")) {
            chain.doFilter(request, response);
        } else if(accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the test";
            request.setAttribute("commandToGoBack", Path.COMMAND_USER_MAIN);
            request.setAttribute("errorMessage", errorMessages);
            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        if(session == null) {
            return false;
        }

        long userId = (long)session.getAttribute("userId");
        long testId = Long.parseLong(httpRequest.getParameter("testId"));

        TestStatus testStatus = userTestService.getUserTestStatus(userId, testId);
        boolean result;
        if(testStatus.equals(TestStatus.NOT_STARTED)) {
            result = false;
        } else if(testStatus.equals(TestStatus.STARTED)) {
            long remainingTime = userTestService.getRemainingTime(userId, testId);
            result = remainingTime > 0;
            if(!result) {
                float testResult = CalculateTestResultService.getTestResult(testId, userId);
                userTestService.addResultAndEndingTime(userId, testId, testResult, Timestamp.valueOf(LocalDateTime.now()));
                userTestService.updateUserTestStatus(userId, testId, TestStatus.PASSED);
            }
        } else {
            result = false;
        }
        return result;
    }
}
