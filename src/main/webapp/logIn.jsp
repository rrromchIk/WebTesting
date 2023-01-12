<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Sign in"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="signUp.jsp" class="float-right btn btn-outline-primary"><fmt:message key="button.signUp"/></a>
                </div>
                <div id="lang-dropdown" class="dropdown">
                    <button id="dropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="lang"/>
                    </button>
                    <div class="dropdown-menu" id="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <p onclick="window.location.href='/controller?action=i18n&en&command=logIn.jsp'" id="engDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/en-flag.webp" alt="ENG"></p>
                        <p onclick="window.location.href='/controller?action=i18n&ua&command=logIn.jsp'" id="uaDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/ua-flag.png" alt="UA"></p>
                    </div>
                </div>
            </div>
        </nav>

        <h1 id="signInText"><fmt:message key="registration-form.signIn.label"/></h1>

        <div id="signInForm">
            <form method="post" action="${pageContext.request.contextPath}/controller?action=logIn">
                <div class="form-group">
                    <label><fmt:message key="registration-form.login.label"/></label>
                    <input type="text" name="login" class="form-control"
                           placeholder="<fmt:message key="registration-form.login.placeholder"/>" required
                           maxlength="25">
                </div>

                <div class="form-group">
                    <label><fmt:message key="registration-form.password.label"/></label>
                    <input type="password" name="password" class="form-control"
                           placeholder="<fmt:message key="registration-form.password.placeholder"/>" required
                           maxlength="25">
                </div>

                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="registration-form.logIn.button"/></button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>