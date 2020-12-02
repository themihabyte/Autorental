<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>index</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <h1>Welcome!</h1>
        <form>
            <a href>Login</a>
            <a href>Register</a>
        </form>
        <c:url value="/catalogue-process" var = "importUrl">
            <c:param name="pageName" value="import catalogue"/>
        </c:url> 
        <c:import url ="${importUrl}"/>
    </body>
</html>