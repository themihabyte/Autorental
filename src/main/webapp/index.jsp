<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <title>index</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>

    <body>
        <%@ page import="model.service.*" %>
            <h1>Welcome!</h1>
            <c:choose>
                <c:when test="${service['class'].simpleName eq 'UnauthorizedUserService'}">
                    <a href="loginPage.jsp">Login</a>
                    <a href="registerPage.jsp">Register</a>
                </c:when>
                <c:when test="${service['class'].simpleName eq 'UserCustomerService'}">
                    <a href="/logout">Exit</a>
                    <a href="/customer-personal-page-servlet">Personal page</a>
                </c:when>
                <c:when test="${service['class'].simpleName eq 'UserAdministratorService'}">
                    <a href="/logout">Exit</a>
                    <form action="/administrator-servlet" method="GET">
                        <input type="hidden" id="action" name="action" value="show_customers">
                        <input type="submit" value="Show all customers" />
                    </form>
                    <a href="/register">Register manager</a>
                    <a href="/add-automobile">Add automobile</a>
                </c:when>
                <c:when test="${service['class'].simpleName eq 'UserManagerService'}">
                    <a href="/logout">Exit</a>
                    <form action="/manager-servlet" method="GET">
                        <input type="hidden" id="action" name="action" value="show_customers">
                        <input type="submit" value="Show all customers" />
                    </form>
                </c:when>
            </c:choose>
            <c:url value="/catalogue-servlet" var="importUrl"></c:url>
            <c:import url="${importUrl}"/><br>
            <c:out value="${error_message}"/>
    </body>

    </html>