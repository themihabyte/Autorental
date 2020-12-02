<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <title>Catalogue</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <h1>Catalogue</h1><br>
        <%
            List<String> automobiles =(List) request.getAttribute("automobiles");
            for(String str : automobiles){
                out.print(str);
            }
        %>
    </body>
</html>