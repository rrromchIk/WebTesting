<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="User-main"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>

            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=logOut"
                       class="float-right btn btn-outline-primary"><fmt:message key="button.logOut"/></a>
                </div>
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=profile"
                       class="float-right btn btn-outline-primary"><fmt:message key="profile.label"/></a>
                </div>
            </div>

            <div id="lang-dropdown" class="dropdown">
                <button id="dropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="lang"/>
                </button>
                <div class="dropdown-menu" id="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <p onclick="window.location.href=
                            '/controller?action=i18n&en&command=/controller?action=userMain&tab=${requestScope.activeTab}&sortMethod=${requestScope.sortMethod}&page=${requestScope.activePage}&groupBy=${requestScope.selectedSubject}'"
                       id="engDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/en-flag.webp" alt="ENG"></p>
                    <p onclick="window.location.href=
                            '/controller?action=i18n&ua&command=/controller?action=userMain&tab=${requestScope.activeTab}&sortMethod=${requestScope.sortMethod}&page=${requestScope.activePage}&groupBy=${requestScope.selectedSubject}'"
                       id="uaDropdown" class="dropdown-item"><img src="${pageContext.request.contextPath}/img/ua-flag.png" alt="UA"></p>
                </div>
            </div>
        </nav>

        <div id="choice">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a id="tests-choice" class="nav-link <c:if test="${requestScope.activeTab eq 'tests'}">active</c:if>"
                       href="${pageContext.request.contextPath}/controller?action=userMain&tab=tests&sortMethod=default"><fmt:message key="user-main.tab.tests"/></a>
                </li>
                <li class="nav-item">
                    <a id="users-choice" class="nav-link <c:if test="${requestScope.activeTab eq 'passedTests'}">active</c:if>"
                       href="${pageContext.request.contextPath}/controller?action=userMain&tab=passedTests"><fmt:message key="user-main.tab.passedTests"/></a>
            </ul>

            <c:if test="${requestScope.activeTab eq 'tests'}">
                <div class="sort">
                    <span><fmt:message key="user-main.sortBy.label"/>: </span>
                    <a href="${pageContext.request.contextPath}/controller?action=userMain&tab=tests&sortMethod=name"
                       class="sort" title="Sort by duration"><fmt:message key="user-main.sortBy.name"/> &#8693</a>
                    <a href="${pageContext.request.contextPath}/controller?action=userMain&tab=tests&sortMethod=difficulty"
                       class="sort" title="Sort by name"><fmt:message key="user-main.sortBy.difficulty"/> &#8693</a>
                    <a href="${pageContext.request.contextPath}/controller?action=userMain&tab=tests&sortMethod=numOfQuest"
                       class="sort" title="Sort by name" ><fmt:message key="user-main.sortBy.numOfQuestions"/> &#8693</a>
                    <span><fmt:message key="user-main.onPartSubj.label"/>: </span>

                    <select name="choice" onchange="window.location.href=this.options[this.selectedIndex].value">
                        <option>              </option>
                        <c:forEach var="subject" items="${requestScope.subjects}">
                            <option VALUE="/controller?action=userMain&tab=tests&groupBy=${subject}"
                            <c:if test="${requestScope.selectedSubject eq subject}">selected</c:if>>${subject}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
        </div>

        <div id="tests">
            <c:forEach var="test" items="${requestScope.tests}">
                <div id="test-item" class="card">
                    <h5 class="card-header">${test.name}</h5>
                    <div class="card-body">
                        <h4 class="card-title">${test.subject}</h4>
                        <p class="card-text"><span class="spanName"><fmt:message key="user-main.testCard.difficulty.label"/>: </span>${test.difficulty.name}</p>
                        <p class="card-text"><span class="spanName"><fmt:message key="user-main.testCard.duration.label"/>: </span>${test.duration} minutes</p>
                        <p class="card-text"><span class="spanName"><fmt:message key="user-main.testCard.numOfQuest.label"/>: </span>${test.numberOfQuestions}</p>

                        <c:choose>
                            <c:when test="${test.status.value eq 'not_started'}">
                                <a href="${pageContext.request.contextPath}/controller?action=startTest&testId=${test.id}"
                                   class="btn btn-success"><fmt:message key="user-main.testCard.button.startTest"/></a>
                            </c:when>

                            <c:when test="${test.status.value eq 'started'}">
                                <a href="${pageContext.request.contextPath}/controller?action=passTest&testId=${test.id}"
                                   class="btn btn-primary"><fmt:message key="user-main.testCard.button.continue"/></a>
                            </c:when>

                            <c:when test="${test.status.value eq 'passed'}">
                                <a class="btn btn-primary disabled"><fmt:message key="user-main.testCard.button.passed"/></a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id="passedTests">
            <c:forEach var="testInfo" items="${requestScope.passedTests}">
                <div id="test-item" class="card">
                    <h5 class="card-header">${testInfo.testName}</h5>
                    <div class="card-body">
                        <h3 class="card-title">${testInfo.testSubject}</h3>
                        <p class="card-text">
                            <span class="spanName"><fmt:message key="user-main.testCard.difficulty.label"/>: </span>
                                ${testInfo.testDifficulty.name}
                        </p>
                        <p class="card-text">
                            <span class="spanName"><fmt:message key="user-main.testCard.startingTime.label"/>: </span>
                                ${testInfo.startingTime}
                        </p>
                        <p class="card-text">
                            <span class="spanName"><fmt:message key="user-main.testCard.endingTime.label"/>: </span>
                            <c:choose>
                                <c:when  test="${testInfo.endingTime eq 'not ended'}">
                                    <fmt:message key="user-main.testCard.notEnded"/>
                                </c:when>
                                <c:otherwise>
                                    ${testInfo.endingTime}
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p class="card-text">
                            <span class="spanName"><fmt:message key="user-main.testCard.result.label"/>: </span>
                                ${testInfo.result} %
                        </p>
                    </div>


                </div>
            </c:forEach>
        </div>

        <nav id="pagination-nav">
            <ul id="paginationLine" class="pagination justify-content-center">
              <ul id="paginationNumbers" class="pagination justify-content-center">
                  <c:if test="${requestScope.amountOfPages > 1}">
                      <c:forEach begin="1" end="${requestScope.amountOfPages}" varStatus="loop">
                          <li class="page-item page-number <c:if test="${requestScope.activePage eq loop.index}">active</c:if>">
                              <a class="page-link"
                                 href="${pageContext.request.contextPath}/controller?action=userMain&tab=${requestScope.activeTab}<c:if test="${requestScope.sortMethod != null}">&sortMethod=${requestScope.sortMethod}</c:if>&page=${loop.index}">${loop.index}</a>
                          </li>
                      </c:forEach>
                  </c:if>
              </ul>
            </ul>
        </nav>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>    