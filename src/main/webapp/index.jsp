<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Testing Patform"/>
    </jsp:include>
    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand">TestsPortal</a>

            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="logIn.jsp" class="float-right btn btn-outline-primary"><fmt:message key="button.signIn"/> </a>
                </div>
                <div class="item-wrapper">
                    <a href="signUp.jsp" class="float-right btn btn-outline-primary"><fmt:message key="button.signUp"/> </a>
                </div>
                <div id="lang-dropdown" class="dropdown">
                    <button id="dropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="lang"/>
                    </button>
                    <div class="dropdown-menu" id="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <p onclick="window.location.href='/controller?action=i18n&en'" id="engDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/en-flag.webp" alt="ENG"></p>
                        <p onclick="window.location.href='/controller?action=i18n&ua'" id="uaDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/ua-flag.png" alt="UA"></p>
                    </div>
                </div>
            </div>
        </nav>

        <div id="main-content">
            <h1><fmt:message key="main-page.content"/> </h1>
        </div>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>