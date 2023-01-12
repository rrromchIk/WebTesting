<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Profile"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=userMain"
                       class="float-right btn btn-outline-primary">&#8592 <fmt:message key="button.back"/></a>
                </div>
                <div id="lang-dropdown" class="dropdown">
                    <button id="dropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="lang"/>
                    </button>
                    <div class="dropdown-menu" id="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <p onclick="window.location.href='/controller?action=i18n&en&command=/controller?action=profile'" id="engDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/en-flag.webp" alt="ENG"></p>
                        <p onclick="window.location.href='/controller?action=i18n&ua&command=/controller?action=profile'" id="uaDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/ua-flag.png" alt="UA"></p>
                    </div>
                </div>
            </div>
        </nav>

        <h1><fmt:message key="profile.label"/></h1>
        <h3><fmt:message key="registration-form.login.label"/>: ${requestScope.fullUser.login}</h3>

        <div id="profileForm">
            <form method="post" action="${pageContext.request.contextPath}/controller?action=editProfile">
                <input type="hidden" name="login" value="${requestScope.fullUser.login}">
                <div class="form-group">
                    <label><fmt:message key="registration-form.name.label"/></label>
                    <input name="name" type="text" value="${requestScope.fullUser.name}" readonly="true" class="form-control"
                        placeholder="<fmt:message key="registration-form.name.placeholder"/>" required maxlength="25">
                </div>
                <div class="form-group">
                    <label><fmt:message key="registration-form.surname.label"/></label>
                    <input name="surname" type="text" value="${requestScope.fullUser.surname}" readonly="true" class="form-control"
                        placeholder="<fmt:message key="registration-form.surname.placeholder"/>" required maxlength="25">
                </div>
                <div class="form-group">
                    <label><fmt:message key="registration-form.email.label"/></label>
                    <input name="email" type="email" value="${requestScope.fullUser.email}" readonly="true" class="form-control"
                        placeholder="<fmt:message key="registration-form.email.placeholder"/>" required maxlength="25">
                </div>
                <button id="edit-profile-btn" type="button" class="btn btn-primary"><fmt:message key="edit-profile.button"/></button>
                <button id="submit-changes-btn" disabled="true" type="submit" class="btn btn-primary"><fmt:message key="button.submitChanges"/></button>
            </form>
        </div>

        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>