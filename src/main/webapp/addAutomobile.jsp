<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <title>Add automobile</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>

    <body>
        <a href="/start-page">Home</a>
        <form action="/administrator-servlet" method="POST">
            <c:if test="${param.automobile_id == null}">
                <input type="hidden" id="action" name="action" value="add_automobile">
            </c:if>
            <c:if test="${param.automobile_id != null}">
                <input type="hidden" id="action" name="action" value="update_automobile">
                <input type="hidden" id="automobile_id" name="automobile_id" value="${param.automobile_id}">
            </c:if>
            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name" value="${param.automobile_name}"><br>
            <label for="manufacturer">Manufacturer:</label><br>
            <input type="text" id="manufacturer" name="manufacturer" value="${param.automobile_manufacturer}"><br>
            <label for="segment">Segment:</label><br>
            <select name="segment" id="segment">
                <c:forTokens items="A,B,C,D,E,F,J,M,S" delims="," var="segment">
                    <option value="${segment}">
                        <c:out value='${segment}' />
                    </option>
                </c:forTokens>
            </select><br>
            <label for="price">price:</label><br>
            <input type="text" id="price" name="price" value="${param.automobile_price}"><br>
            <label for="is_in_stock">In stock</label>
            <input type="checkbox" id="is_in_stock" name="is_in_stock" value="true"><br>
            <input type="submit" value="Submit">
            <c:out value="${error_message}" />
        </form>
    </body>

    </html>