<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Change password"/>
    </jsp:include>

    <body>
    <nav id="navbar" class="navbar navbar-expand-lg navbar-light">
        <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
        <div id="navbar-table" class="collapse navbar-collapse">
            <div class="item-wrapper">
                <a href="logIn.jsp" class="float-right btn btn-primary">&#8592 <fmt:message key="button.back"/></a>
            </div>
            <jsp:include page="/WEB-INF/templates/_lang-drop-down.jsp">
                <jsp:param name="command" value="/forgotPassword.jsp"/>
            </jsp:include>
        </div>
    </nav>
        <h1 id="forgotPasswordText">Change password</h1>
        <div id="forgotPasswordDIV">
            <div id="signInForm">
                <form method="post"
                      action="${pageContext.request.contextPath}/controller?action=updatePassword&token=${requestScope.token}">
                    <div class="form-group">
                        <label class="color"><fmt:message key="registrationForm.password.label"/></label>
                        <input class="form-control" type="password" name="password" required
                               placeholder="<fmt:message key="registrationForm.password.placeholder"/>" maxlength="25"
                               oninvalid="this.setCustomValidity('<fmt:message key="validation.fillThisField"/>')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label class="color">Confirm password</label>
                        <input class="form-control" type="password" name="confirmPassword" required
                               placeholder="<fmt:message key="registrationForm.password.placeholder"/>" maxlength="25"
                               oninvalid="this.setCustomValidity('<fmt:message key="validation.fillThisField"/>')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Change</button>
                    <c:if test="${sessionScope.invalid eq true}">
                        <input type="hidden" class="is-invalid">
                        <div class="invalid-feedback">
                            <h6 id="invalidFeedbackId">Failed to send email</h6>
                        </div>
                        <c:remove var="invalid" scope="session"/>
                    </c:if>
                    <c:if test="${sessionScope.success eq true}">
                        <input type="hidden" class="is-valid">
                        <div class="valid-feedback">
                            <h6>Email sent successfully</h6>
                        </div>
                        <c:remove var="success" scope="session"/>
                    </c:if>
                </form>
            </div>


        </div>
        <jsp:include page="/WEB-INF/templates/_footer.jsp"/>
        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>
