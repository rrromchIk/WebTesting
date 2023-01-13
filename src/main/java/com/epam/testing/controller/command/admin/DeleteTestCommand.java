package com.epam.testing.controller.command.admin;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.model.service.TestsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTestCommand implements Command {
    private final TestsService testsService = new TestsService();
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        int activePage = (int)req.getSession().getAttribute("activePage");
        long testId = Long.parseLong(req.getParameter("testId"));

        boolean redirect = true;
        String page = Path.COMMAND_ADMIN_MAIN + "&page=" + activePage;

        if(!testsService.deleteTest(testId)) {
            page = Path.PAGE_ERROR_PAGE;
            String errorMessage = "Unable to delete test";
            req.setAttribute("errorMessage", errorMessage);
            redirect = false;
        }

        return new DispatchInfo(redirect, page);
    }
}
