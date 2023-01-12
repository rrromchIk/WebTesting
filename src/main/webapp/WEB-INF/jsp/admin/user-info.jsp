<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="User info"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=adminMain&tab=users&page=${sessionScope.activePage}" class="float-right btn btn-outline-primary">🠔 Back</a>
                </div>
                <div id="lang-dropdown" class="dropdown">
                    <button id="dropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ENG
                    </button>
                    <div class="dropdown-menu" id="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <p id="engDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/en-flag.webp" alt="ENG"></p>
                        <p id="uaDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/ua-flag.png" alt="UA"></p>
                    </div>
                </div>
            </div>
        </nav>

        <h1>Login: ${requestScope.fullUser.login}</h1>

        <div id="profileForm">
            <form method="post" action="${pageContext.request.contextPath}/controller?action=editUser&userId=${requestScope.fullUser.id}">
                <input type="hidden" name="login" value="${requestScope.fullUser.login}">
                <div class="form-group">
                    <label>Name</label>
                    <input name="name" type="text" value="${requestScope.fullUser.name}" readonly="true" class="form-control"
                           placeholder="Enter name" required maxlength="25">
                </div>
                <div class="form-group">
                    <label>Surname</label>
                    <input name="surname" type="text" value="${requestScope.fullUser.surname}" readonly="true" class="form-control"
                           placeholder="Enter surname" required maxlength="25">
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input name="email" type="email" value="${requestScope.fullUser.email}" readonly="true" class="form-control"
                           placeholder="Enter email" required maxlength="25">
                </div>
                <button id="edit-profile-btn" type="button" class="btn btn-primary">Edit</button>
                <button id="submit-changes-btn" disabled="true" type="submit" class="btn btn-primary">Submit changes</button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>