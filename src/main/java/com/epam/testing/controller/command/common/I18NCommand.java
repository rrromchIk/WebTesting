package com.epam.testing.controller.command.common;
import com.epam.testing.controller.DispatchInfo;
import com.epam.testing.controller.Path;
import com.epam.testing.controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Internationalization controller command.
 *
 * @author rom4ik
 */

public class I18NCommand implements Command {
  @Override
  public DispatchInfo execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();

    String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
    String defaultLocale = "defaultLocale";

    if (request.getParameter("ua") != null) {
      Config.set(session, fmtLocale, Path.LOCALE_NAME_UA);
      session.setAttribute(defaultLocale, Path.LOCALE_NAME_UA);
    } else {
      Config.set(session, fmtLocale, Path.LOCALE_NAME_EN);
      session.setAttribute(defaultLocale, Path.LOCALE_NAME_EN);
    }

    String command = request.getContextPath() + request.getParameter("command");
    command = checkOtherParameters(request, command);

    return new DispatchInfo(true, command);
  }

  private String checkOtherParameters(HttpServletRequest request, String command) {
    if(command == null) {
      command = Path.PAGE_INDEX;
    }

    String tab = request.getParameter("tab");
    if(tab != null && !tab.isEmpty()) {
      command += "&tab=" + tab;
    }
    String page = request.getParameter("page");
    if(page != null && !page.isEmpty()) {
      command += "&page=" + page;
    }
    String sortMethod = request.getParameter("sortMethod");
    if(sortMethod != null && !sortMethod.isEmpty()) {
      command += "&sortMethod=" + sortMethod;
    }
    String groupBy = request.getParameter("groupBy");
    if(groupBy != null && !groupBy.isEmpty()) {
      command += "&groupBy=" + groupBy;
    }
    String userId = request.getParameter("userId");
    if(userId != null && !userId.isEmpty()) {
      command += "&userId=" + userId;
    }
    String testId = request.getParameter("testId");
    if(testId != null && !testId.isEmpty()) {
      command += "&testId=" + testId;
    }
    String testName = request.getParameter("testName");
    if(testName != null && !testName.isEmpty()) {
      command += "&testName=" + testName;
    }
    return command;
  }
}