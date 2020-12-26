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
                    <a href="loginPage.jsp">Login</a>
                    <a href="registerPage.jsp">Register</a>
            </c:when>
            <c:when test="${service['class'].simpleName eq 'UserCustomerService'}">
                            <h1><c:out value="${service.getUser().getUsername()}"/></h1>
                            <a href="/logout">Exit</a>
                            <a href="/customer-personal-page-servlet">Personal page</a>
                        </c:when>
        </c:choose>

        <c:url value="/catalogue-servlet" var = "importUrl"></c:url>
        <c:import url ="${importUrl}"/>
    </body>
</html>