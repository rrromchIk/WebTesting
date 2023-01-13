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
                <jsp:include page="/WEB-INF/templates/_lang-drop-down.jsp">
                    <jsp:param name="command" value="/controller?action=addQuestions&testId=${requestScope.testId}"/>
                </jsp:include>
            </div>
        </nav>

        <div class="card" id="addQuestionsMain">
            <h3 class="card-header"><fmt:message key="registration-form.name.label"/>: ${requestScope.fullTest.name}</h3>
            <div class="card-body">
                <p class="card-text">
                    <span class="spanName"><fmt:message key="test-card.subject.label"/>: </span>
                    ${requestScope.fullTest.subject}
                </p>
                <p class="card-text"><span class="spanName"><fmt:message key="testCard.difficulty.label"/>: </span>
                    <c:choose>
                        <c:when test="${requestScope.fullTest.difficulty.name eq 'easy'}">
                            <fmt:message key="testCard.difficulty.easy"/>
                        </c:when>
                        <c:when test="${requestScope.fullTest.difficulty.name eq 'medium'}">
                            <fmt:message key="testCard.difficulty.medium"/>
                        </c:when>
                        <c:when test="${requestScope.fullTest.difficulty.name eq 'hard'}">
                            <fmt:message key="testCard.difficulty.hard"/>
                        </c:when>
                    </c:choose>
                </p>
                <p class="card-text"><span class="spanName"><fmt:message key="testCard.duration.label"/>: </span>
                    ${requestScope.fullTest.duration} <fmt:message key="testCard.minutes"/></p>
                <p class="card-text"><span class="spanName"><fmt:message key="testCard.numOfQuest.label"/>: </span>
                    ${requestScope.fullTest.numberOfQuestions}</p>
            </div>

            <div class="card-body" id="questionsDiv">
                <form method="post" action="${pageContext.request.contextPath}/controller?action=submitQuestionInfo&testId=${requestScope.testId}">
                    <br><hr><br>
                    <h3 class="testHeader"><fmt:message key="test-passing.question.label"/> ${requestScope.questionNumber}/${requestScope.fullTest.numberOfQuestions}</h3>
                    <textarea class="form-control" name="questionText" required
                              placeholder="<fmt:message key="add-questions.question.placeholder"/>"></textarea>
                    <h3 class="answerHeader"><fmt:message key="add-questions.answers.label"/>:</h3>

                    <div class="form-check">
                        <input class="form-check-input" name="answer1correct" type="checkbox" id="firstAnswerCheck">
                        <input type="text" class="form-control" name="answer1" id="firstAnswer" required
                               placeholder="<fmt:message key="add-questions.answer.placeholder"/>">
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" name="answer2correct" type="checkbox" value="" id="secondAnswerCheck">
                        <input type="text" class="form-control" name="answer2" id="secondAnswer" required
                               placeholder="<fmt:message key="add-questions.answer.placeholder"/>">
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" name="answer3correct" type="checkbox" value="" id="thirdAnswerCheck">
                        <input type="text" class="form-control" name="answer3" id="thirdAnswer" required
                               placeholder="<fmt:message key="add-questions.answer.placeholder"/>">
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" name="answer4correct" type="checkbox" value="" id="fourtAnswerCheck">
                        <input type="text" class="form-control" name="answer4" id="fourtAnswer" required
                               placeholder="<fmt:message key="add-questions.answer.placeholder"/>">
                    </div>
                    <button type="submit" class="btn btn-primary" id="submitTestInfoButton"><fmt:message key="button.submit"/></button>
                </form>
            </div>


        </div>
        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>