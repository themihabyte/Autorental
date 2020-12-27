<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <title>Register</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>

    <body>
        <%@ page import="model.service.*" %>
            <form action="/register-servlet" method="POST">
                <label for="username">username:</label><br>
                <input type="text" id="username" name="username"><br>
                <label for="password">password:</label><br>
                <input type="password" id="password" name="password">
                <c:out value="${error_message}" /><br>
                <input type="submit" value="Register">
            </form>
    </body>

    </html>