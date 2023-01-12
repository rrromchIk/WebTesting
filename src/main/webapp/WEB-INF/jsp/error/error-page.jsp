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
        <div>
            <div id="main-content">
                <img src="https://a0.muscache.com/airbnb/static/error_pages/404-Airbnb_final-d652ff855b1335dd3eedc3baa8dc8b69.gif"
                     width="313" height="428" class="hide-sm" alt="Девочка уронила свое мороженое.">
            </div>
            <h3>${requestScope.errorMessage}</h3>
            <h5>Go back to <a href="${pageContext.request.contextPath}/index.jsp">Main</a></h5>
        </div>
    </body>
</html>