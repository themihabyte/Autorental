<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>index</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
    <%@ page import ="model.service.*" %>
        <h1>Welcome!</h1>
        <c:url value="/list" var = "importUrl">
                            <c:param name="pageName" value="import list"/>
                        </c:url>
                        <c:import url ="${importUrl}"/>
    </body>
</html>