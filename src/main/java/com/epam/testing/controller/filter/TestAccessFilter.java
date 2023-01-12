package com.epam.testing.controller.filter;

import com.epam.testing.controller.Path;
import com.epam.testing.model.entity.RemainingTime;
import com.epam.testing.model.entity.TestStatus;
import com.epam.testing.model.service.UserTestService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        System.out.println("test access filter");
        String action = request.getParameter("action");
        if(!action.equals("passTest")) {
            chain.doFilter(request, response);
        } else if(accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the test";
            request.setAttribute("errorMessage", errorMessages);
            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        if(session == null) {
            System.out.println("session is null");
            return false;
        }

        long userId = (long)session.getAttribute("userId");
        long testId = Long.parseLong(httpRequest.getParameter("testId"));
        TestStatus testStatus = userTestService.getUserTestStatus(userId, testId);
        boolean result;
        if(testStatus.equals(TestStatus.NOT_STARTED)) {
            System.out.println("test not started");
            result = true;
        } else if(testStatus.equals(TestStatus.STARTED)) {
            long remainingTime = userTestService.getRemainingTime(userId, testId);
            result = remainingTime > 0;
            System.out.println("test started " + result);
            if(!result) {
                userTestService.updateUserTestStatus(userId, testId, TestStatus.PASSED);
            }
        } else {
            System.out.println("test passed");
            result = false;
        }
        return result;
    }
}
