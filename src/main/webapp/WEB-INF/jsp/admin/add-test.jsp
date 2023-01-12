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
                    <a href="${pageContext.request.contextPath}/controller?action=adminMain" class="float-right btn btn-outline-primary">Back to main</a>
                </div>
                <div class="item-wrapper">
                    <a href="${pageContext.request.contextPath}/controller?action=logOut" class="float-right btn btn-outline-primary">Log out</a>
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

        <div id="addingTest">
            <form method="post" action="${pageContext.request.contextPath}/controller?action=submitTestInfo">
                <h3>Please enter test info</h3><br>
                <div class="form-row">
                    <div class="form-group col-md-3 mb-3">
                        <label for="inputTestName">Test name</label>
                        <input type="text" name="name" class="form-control" id="inputTestName" required placeholder="Enter test name">
                    </div>

                    <div class="form-group col-md-3 mb-3">
                        <label for="inputSubject">Subject</label>
                        <input type="text" name="subject" class="form-control" id="inputSubject" required placeholder="Enter subject">
                    </div>

                    <div class="form-group col-md-2">
                        <label for="inputDuration">Duration(min)</label>
                        <input type="number" name="duration" class="form-control" id="inputDuration" value="1" placeholder="1" min="1">
                    </div>

                    <div class="form-group col-md-2">
                        <label for="inputDifficulty">Difficulty</label>
                        <select id="inputDifficulty" name="difficulty" class="form-control">
                            <option value="0" selected>Easy</option>
                            <option value="1">Medium</option>
                            <option value="2">Hard</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <label for="amountOfQuestions" class="col-sm-5 col-form-label">Amount of question you would like to add:</label>
                    <div class="col-md-2">
                        <input type="number" name="numOfQuestions" class="form-control" id="amountOfQuestions" value="3" placeholder="3" min="3">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" id="submitTestInfoButton">Submit</button>
            </form>
        </div>

        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>