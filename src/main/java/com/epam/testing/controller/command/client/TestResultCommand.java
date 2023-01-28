package com.epam.testing.controller.command.client;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.util.PdfBuilderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestResultCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(TestResultCommand.class);
    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("TestResultCommand execution started");
        long testId = Long.parseLong(req.getParameter("testId"));
        long userId = (long)req.getSession().getAttribute("userId");

        PdfBuilderUtil.createResultPdf(resp, userId, testId);

        LOGGER.debug("TestResultCommand execution started");
        return new DispatchInfo(false, Path.COMMAND_USER_MAIN + "?tab=passedTests");
    }
}
