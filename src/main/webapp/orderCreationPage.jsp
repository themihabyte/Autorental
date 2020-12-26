<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>create order</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
    <a href="/start-page">Home</a>
    <a href="/customer-personal-page-servlet">Personal page</a><br>
    <%@ page import ="model.service.*" %>
    <c:set var="auto" value='${param.autoId}'/>
    <form action="/create-order-servlet" method="POST">
    <input type="hidden" id="autoId" name="autoId" value="${auto}">
    Auto ID: <c:out value="${auto}"/><br>
        <label for="passportDetails">passport details:</label><br>
        <input type="text" id="passportDetails" name="passportDetails"><br>
        <label for="startDate">start date:</label><br>
        <input type="date" id="startDate" name="date"><br>
        <label for="endDate">end date:</label><br>
        <input type="date" id="endDate" name="date"><br>
        <label for="isHasDriver">I need driver: </label>
        <input type="checkbox" id="isHasDriver" name="isHasDriver" value="true"><br>
        <input type="submit" value="Create order">
        <c:out value="${error_message}"/>
      </form>
    </body>
</html>