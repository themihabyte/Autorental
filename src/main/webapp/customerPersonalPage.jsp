<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <title>Personal page</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
    <a href="/start-page">Home</a>
    <h1>Your orders, <c:out value="${service.getUser().getUsername()}"/></h1><br>
        <table style="width:100%">
                <c:forEach items="${list}" var="element">
                      <tr>
                      <th>
                            <c:out value="${element.getId()}"/>
                      </th>
                        <th>
                            <c:out value="${element.getStartDate()}"/>
                            <c:out value="${'-'}"/>
                            <c:out value="${element.getEndDate()}"/>
                        </th>
                        <th>
                            <c:out value="${element.getAutomobile().getManufacturer()}"/>
                            <c:out value="${element.getAutomobile().getName()}"/>
                        </th>
                        <th>
                        <form action="/order-servlet" method="POST">
                            <input type="hidden" id="order_id" name="order_id" value="${element.getId()}">
                            <input type="submit" value="Details">
                        </form>
                        </th>
                      </tr>
                </c:forEach>
                <c:out value="${error_message}"/>
                </table>
    </body>
</html>