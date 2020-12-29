<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

    <html>

    <head>
        <title>Add automobile</title>
    </head>

    <body>
        <a href="/start-page"><fmt:message key='homeButton'/></a>
        <form action="/administrator-servlet" method="POST">
            <c:if test="${param.automobile_id == null}">
                <input type="hidden" id="action" name="action" value="add_automobile">
            </c:if>
            <c:if test="${param.automobile_id != null}">
                <input type="hidden" id="action" name="action" value="update_automobile">
                <input type="hidden" id="automobile_id" name="automobile_id" value="${param.automobile_id}">
            </c:if>
            <label for="name"><fmt:message key='automobile.name'/></label><br>
            <input type="text" id="name" name="name" value="${param.automobile_name}"><br>
            <label for="manufacturer"><fmt:message key='automobile.manufacturer'/></label><br>
            <input type="text" id="manufacturer" name="manufacturer" value="${param.automobile_manufacturer}"><br>
            <label for="segment"><fmt:message key='automobile.segment'/></label><br>
            <select name="segment" id="segment">
                <c:forTokens items="A,B,C,D,E,F,J,M,S" delims="," var="segment">
                    <option value="${segment}">
                        <c:out value='${segment}' />
                    </option>
                </c:forTokens>
            </select><br>
            <label for="price"><fmt:message key='automobile.price'/></label><br>
            <input type="text" id="price" name="price" value="${param.automobile_price}"><br>
            <label for="is_in_stock"><fmt:message key='automobile.isInStock'/></label>
            <input type="checkbox" id="is_in_stock" name="is_in_stock" value="true"><br>
            <input type="submit" value="<fmt:message key='addAutomobile.submitButton'/>">
            <c:out value="${error_message}" />
        </form>
    </body>

    </html>