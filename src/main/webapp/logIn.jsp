<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/templates/_head.jsp">
        <jsp:param name="title" value="Sign in"/>
    </jsp:include>

    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <a href="signUp.jsp" class="float-right btn btn-primary"><fmt:message key="button.signUp"/></a>
                </div>
                <jsp:include page="/WEB-INF/templates/_lang-drop-down.jsp">
                    <jsp:param name="command" value="/logIn.jsp"/>
                </jsp:include>
            </div>
        </nav>

        <h1 id="signInText"><fmt:message key="registrationForm.signIn.label"/></h1>

        <div id="signInFormDIV">
            <div id="signInForm">
                <form method="post" action="${pageContext.request.contextPath}/controller?action=logIn">

                    <c:if test="${sessionScope.signUpSuccess eq true}">
                        <input type="hidden" class="is-valid">
                        <div class="valid-feedback">
                            <h6><fmt:message key="validation.signUpSuccess"/></h6>
                        </div>
                        <c:remove var="signUpSuccess" scope="session"/>
                    </c:if>
                    <c:if test="${sessionScope.invalid eq true}">
                        <input type="hidden" class="is-invalid">
                        <div class="invalid-feedback">
                            <h6 id="invalidFeedbackId"><fmt:message key="validation.noSuchUser"/></h6>
                        </div>
                        <c:remove var="invalid" scope="session"/>
                    </c:if>

                    <div class="form-group">
                        <label class="color"><fmt:message key="registrationForm.login.label"/></label>
                        <input class="form-control" type="text" name="login"  required title=""
                               placeholder="<fmt:message key="registrationForm.login.placeholder"/>"  maxlength="25"
                               oninvalid="this.setCustomValidity('<fmt:message key="validation.fillThisField"/>')"
                               oninput="this.setCustomValidity('')">
                    </div>

                    <div class="form-group">
                        <label class="color"><fmt:message key="registrationForm.password.label"/></label>
                        <input class="form-control" type="password" name="password" required
                               placeholder="<fmt:message key="registrationForm.password.placeholder"/>" maxlength="25"
                               oninvalid="this.setCustomValidity('<fmt:message key="validation.fillThisField"/>')"
                               oninput="this.setCustomValidity('')">
                    </div>

                    <button type="submit" class="btn btn-primary btn-block"><fmt:message key="registrationForm.logIn.button"/></button>
                </form>
            </div>
            <div id="registrTip">
                <span id="registrTipText"><fmt:message key="registrationForm.dontHaveAnAccount.label"/></span>
                <a href="signUp.jsp"><fmt:message key="button.signUp"/></a>
            </div>

        </div>
        <jsp:include page="/WEB-INF/templates/_footer.jsp"/>
        <jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
    </body>
</html>