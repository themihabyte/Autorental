<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <title>list</title>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <%
            List<String> list =(List) request.getAttribute("list");
            for(String str : list){
                out.print(str+"<br>");
            }
        %>
    </body>
</html>