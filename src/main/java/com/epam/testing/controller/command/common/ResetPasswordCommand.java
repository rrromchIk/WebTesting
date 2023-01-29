package com.epam.testing.controller.command.common;

import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import com.epam.testing.util.EmailSenderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResetPasswordCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ResetPasswordCommand.class);

    @Override
    public DispatchInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("ResetPasswordCommand execution starts");
        String email = req.getParameter("email");
        LOGGER.info("Recipient email: {}", email);
        HttpSession session = req.getSession();

        if(EmailSenderUtil.sendEmail(email, "Hello from Java")) {
            String successMsg = "Email sent successfully";
            session.setAttribute("success", true);
            LOGGER.info(successMsg);
        } else {
            String errorMessage = "Failed to send email";
            session.setAttribute("invalid", true);
            LOGGER.warn(errorMessage);
        }

        LOGGER.debug("ResetPasswordCommand execution finished");
        return new DispatchInfo(true, Path.PAGE_FORGOT_PASSWORD);
    }
}
