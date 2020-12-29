<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
    <html>

    <head>
        <title>Login</title>
    </head>

    <body>
        <%@ page import="model.service.*" %>
            <form action="login-servlet" method="POST">
                <label for="username"><fmt:message key="username"/></label><br>
                <input type="text" id="username" name="username"><br>
                <label for="password"><fmt:message key='password'/></label><br>
                <input type="password" id="password" name="password">
                <c:out value="${error_message}" /><br>
                <input type="submit" value="<fmt:message key='loginPage.loginButton'/>">
            </form>
    </body>

    </html>