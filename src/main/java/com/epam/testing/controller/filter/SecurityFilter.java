package com.epam.testing.controller.filter;
import com.epam.testing.controller.Path;
import com.epam.testing.model.entity.UserRole;
import com.epam.testing.model.entity.UserStatus;
import com.epam.testing.model.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "SecurityFilter",
        urlPatterns = "/controller",
        initParams = {
                @WebInitParam(name = "guest", value = "logIn signUp logOut i18n"),
                @WebInitParam(name = "client", value = "userMain profile editProfile startTest passTest " +
                        "submitAnswers endTest"),
                @WebInitParam(name = "admin", value = "adminMain userInfo editUser deleteTest addTest " +
                        "changeUserStatus submitTestInfo addQuestions submitQuestionInfo testInfo editTest deleteQuestion")
        })
public class SecurityFilter implements Filter {
    private static final UserService userService = new UserService();
    private static final Map<UserRole, List<String>> accessMap = new HashMap<>();

    @Override
    public void init(FilterConfig config) {
        List<String> adminCommands = asList(config.getInitParameter("admin"));
        List<String> clientCommands = asList(config.getInitParameter("client"));
        List<String> guestCommands = asList(config.getInitParameter("guest"));
        clientCommands.addAll(guestCommands);
        adminCommands.addAll(guestCommands);
        accessMap.put(UserRole.CLIENT, clientCommands);
        accessMap.put(UserRole.ADMIN, adminCommands);
        accessMap.put(UserRole.GUEST, guestCommands);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String command = request.getParameter("action");

        HttpSession session = httpRequest.getSession(false);
        if(session == null) {
            request.setAttribute("errorMessage", "Invalid session");
            return false;
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        String userLogin = (String) session.getAttribute("login");
        if(userRole == null) {
            session.setAttribute("userRole", UserRole.GUEST);
            userRole = UserRole.GUEST;
        }

        request.setAttribute("errorMessage", "You don't have permission to access the requested resource");

        if(userLogin != null && userService.userIsBlocked(userLogin)) {
            return false;
        }
        return accessMap.get(userRole).contains(command);
    }

    @Override
    public void destroy() {

    }

    private List<String> asList(String param) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
