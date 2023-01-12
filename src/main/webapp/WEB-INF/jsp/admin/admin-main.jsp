<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Admin-main"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
            <div id="navbar-table" class="collapse navbar-collapse">
                <div>
                    <a href="${pageContext.request.contextPath}/controller?action=addTest" class="float-right btn btn-success">Add test</a>
                </div>
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=logOut"
                       class="float-right btn btn-outline-primary">Log out</a>
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

        <div id="choice">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a id="tests-choice"
                       class="nav-link <c:if test="${requestScope.activeTab eq 'tests'}">active</c:if>"
                       href="${pageContext.request.contextPath}/controller?action=adminMain&tab=tests">Tests</a>
                </li>
                <li class="nav-item">
                    <a id="users-choice" class="nav-link <c:if test="${requestScope.activeTab eq 'users'}">active</c:if>"
                       href="${pageContext.request.contextPath}/controller?action=adminMain&tab=users">Users</a>
                </li>
            </ul>
        </div>

        <div id="tests">
            <c:forEach var="test" items="${requestScope.tests}">
                <div id="test-item" class="card">
                    <h5 class="card-header">${test.name}</h5>
                    <div class="card-body">
                        <h3 class="card-title">${test.subject}</h3>
                        <p class="card-text"><span class="spanName">Difficulty: </span>${test.difficulty.name}</p>
                        <p class="card-text"><span class="spanName">Duration: </span>${test.duration} minutes</p>
                        <p class="card-text"><span class="spanName">Number of questions: </span>${test.numberOfQuestions}</p>
                        <a href="#" class="btn btn-primary">Edit test</a>
                        <a href="${pageContext.request.contextPath}/controller?action=deleteTest&testId=${test.id}" class="btn btn-danger">Delete Test</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id="users">
            <c:forEach var="user" items="${requestScope.users}">
                <div id="test-item" class="card">
                    <h5 class="card-header">Login: ${user.login}</h5>
                    <div class="card-body">
                        <p class="card-text"><span class="spanName">Name: </span>${user.name}</p>
                        <p class="card-text"><span class="spanName">Surname: </span>${user.surname}</p>
                        <p class="card-text"><span class="spanName">Email: </span>${user.email}</p>
                        <p class="card-text"><span class="spanName">Role: </span>${user.role.name}</p>
                        <p class="card-text"><span class="spanName">Status: </span>${user.status.name}</p>
                        <a href="${pageContext.request.contextPath}/controller?action=userInfo&userId=${user.id}" class="btn btn-primary">Edit user</a>
                        <a href="${pageContext.request.contextPath}/controller?action=changeUserStatus&userId=${user.id}" class="btn btn-danger">
                            <c:if test="${user.status.name eq 'blocked'}">Unblock</c:if>
                            <c:if test="${user.status.name eq 'active'}">Block</c:if>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <nav id="pagination-nav">
            <ul id="paginationLine" class="pagination justify-content-center">
                <ul id="paginationNumbers" class="pagination justify-content-center">
                    <c:forEach begin="1" end="${requestScope.amountOfPages}" varStatus="loop">
                        <li class="page-item page-number <c:if test="${sessionScope.activePage eq loop.index}">active</c:if>">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/controller?action=adminMain&tab=${requestScope.activeTab}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:forEach>
                </ul>
            </ul>
        </nav>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>