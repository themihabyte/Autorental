<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>index</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
    <%@ page import ="model.service.*" %>
        <h1>Welcome!</h1>
        <c:choose>
            <c:when test="${service['class'].simpleName eq 'UnauthorizedUserService'}">
                <form>
                    <a href="loginPage.jsp">Login</a>
                    <a href="registerPage.jsp">Register</a>
                </form>
            </c:when>
            <c:when test="${service['class'].simpleName eq 'UserCustomerService'}">
                            <h1><c:out value="${service.getUser().getUsername()}"/><h1>
                            <form>
                                <a href="/Main">Exit</a>
                            </form>
                        </c:when>
        </c:choose>
        <c:url value="/catalogue-process" var = "importUrl">
                            <c:param name="pageName" value="import catalogue"/>
                        </c:url>
                        <c:import url ="${importUrl}"/>
    </body>
</html>