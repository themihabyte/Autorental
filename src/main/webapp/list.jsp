<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <title>list</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <c:forEach items="${list}" var="element">
              <tr>
                <td>${element}</td>
              </tr><br>
        </c:forEach>
    </body>
</html>