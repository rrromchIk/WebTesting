<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/_head.jsp">
            <jsp:param name="title" value="Error"/>
        </jsp:include>
    </head>
    <body>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a id="navbar-logo" class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TestPortal</a>
            <div id="navbar-table" class="collapse navbar-collapse">
                <div class="item-wrapper">
                    <c:choose>
                        <c:when test="${requestScope.commandToGoBack eq null}">
                            <c:set var="commandToGoBack" value="index.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.commandToGoBack != null}">
                            <c:set var="commandToGoBack" value="${requestScope.commandToGoBack}"/>
                        </c:when>
                    </c:choose>
                    <a href="${pageScope.commandToGoBack}" class="float-right btn btn-outline-primary">
                        &#8592 <fmt:message key="button.back"/>
                    </a>
                </div>
            </div>
        </nav>
        <div>
            <c:if test="${requestScope.errorMessage eq null}">
                <h4>Ups...</h4>
                <h3>Something went wrong!</h3>
            </c:if>
            <h3>${requestScope.errorMessage}</h3>
            <div id="main-content">
                <img src="https://a0.muscache.com/airbnb/static/error_pages/404-Airbnb_final-d652ff855b1335dd3eedc3baa8dc8b69.gif"
                     width="313" height="428" class="hide-sm" alt="Девочка уронила свое мороженое.">
            </div>
        </div>
    </body>
</html>