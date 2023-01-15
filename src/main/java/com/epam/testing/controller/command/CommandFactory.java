package com.epam.testing.controller.command;

import com.epam.testing.controller.command.admin.*;
import com.epam.testing.controller.command.admin.tests.*;
import com.epam.testing.controller.command.admin.users.ChangeUserStatusCommand;
import com.epam.testing.controller.command.admin.users.EditUserCommand;
import com.epam.testing.controller.command.admin.users.UserInfoCommand;
import com.epam.testing.controller.command.client.*;
import com.epam.testing.controller.command.common.*;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Main Command factory for Controller.
 *
 * @author rom4ik
 */
public class CommandFactory {
  private static CommandFactory factory = new CommandFactory();
  private static Map<String, Command> commands = new HashMap<>();

  private CommandFactory() {}

  /**
     * Singleton.
     */
  public static synchronized CommandFactory commandFactory() {
    if (factory == null) {
      factory = new CommandFactory();
    }
    return factory;
  }

  static {
    // common commands
    commands.put("login", new LoginCommand());
    commands.put("logout", new LogoutCommand());
    commands.put("signup", new SignUpCommand());
    commands.put("i18n", new I18NCommand());

    //admin commands
    commands.put("adminmain", new AdminMainCommand());
    commands.put("userinfo", new UserInfoCommand());
    commands.put("edituser", new EditUserCommand());
    commands.put("deletetest", new DeleteTestCommand());
    commands.put("changeuserstatus", new ChangeUserStatusCommand());
    commands.put("addtest", new AddTestCommand());
    commands.put("submittestinfo", new SubmitTestInfoCommand());
    commands.put("addquestions", new AddQuestionsCommand());
    commands.put("submitquestioninfo", new SubmitQuestionInfoCommand());
    commands.put("testinfo", new TestInfoCommand());
    commands.put("edittest", new EditTestCommand());
    commands.put("deletequestion", new DeleteQuestionCommand());

    //client commands
    commands.put("editprofile", new EditProfileCommand());
    commands.put("profile", new ProfileCommand());
    commands.put("usermain", new UserMainCommand());
    commands.put("starttest", new StartTestCommand());
    commands.put("passtest", new PassTestCommand());
    commands.put("endtest", new EndTestCommand());
    commands.put("submitanswers", new SubmitAnswersCommand());
  }

  public Command getCommand(HttpServletRequest request) {
    String action = request.getParameter("action");
    Command command;
    if(!commands.containsKey(action.toLowerCase())) {
      command = new NoCommand();
    } else {
      command = commands.get(action.toLowerCase());
    }
    return command;
  }
}
