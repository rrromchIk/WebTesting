<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Add test"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand">TestsPortal</a>

            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=adminMain"
                       class="float-right btn btn-outline-primary">&#8592 <fmt:message key="button.back"/></a>
                </div>
                <jsp:include page="/WEB-INF/templates/_lang-drop-down.jsp">
                    <jsp:param name="command" value="/controller?action=addTest"/>
                </jsp:include>
            </div>
        </nav>

        <div id="addingTest">
            <form method="post" action="${pageContext.request.contextPath}/controller?action=submitTestInfo">
                <h3>Please enter test info</h3><br>
                <div class="form-row">
                    <div class="form-group col-md-3 mb-3">
                        <label for="inputTestName"><fmt:message key="registration-form.name.label"/></label>
                        <input type="text" name="name" class="form-control" id="inputTestName" required
                               placeholder="<fmt:message key="registration-form.name.placeholder"/>">
                    </div>

                    <div class="form-group col-md-3 mb-3">
                        <label for="inputSubject"><fmt:message key="test-card.subject.label"/></label>
                        <input type="text" name="subject" class="form-control" id="inputSubject" required
                               placeholder="<fmt:message key="test-card.subject.placeholder"/>">
                    </div>

                    <div class="form-group col-md-2">
                        <label for="inputDuration"><fmt:message key="testCard.duration.label"/>(<fmt:message key="testCard.minutes"/>)</label>
                        <input type="number" name="duration" class="form-control" id="inputDuration" value="1" placeholder="1" min="1">
                    </div>

                    <div class="form-group col-md-2">
                        <label for="inputDifficulty"><fmt:message key="testCard.difficulty.label"/></label>
                        <select id="inputDifficulty" name="difficulty" class="form-control">
                            <option value="0" selected><fmt:message key="testCard.difficulty.easy"/></option>
                            <option value="1"><fmt:message key="testCard.difficulty.medium"/></option>
                            <option value="2"><fmt:message key="testCard.difficulty.hard"/></option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <label for="amountOfQuestions"
                           class="col-sm-5 col-form-label"><fmt:message key="add-test.addAmountOfQuestions"/>:</label>
                    <div class="col-md-2">
                        <input type="number" name="numOfQuestions" class="form-control" id="amountOfQuestions"
                               value="3" placeholder="3" min="3">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" id="submitTestInfoButton"><fmt:message key="button.submit"/></button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>