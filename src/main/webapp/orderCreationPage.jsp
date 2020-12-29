<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
    <head>
        <title>create order</title>
    </head>
    <body>
    <a href="/start-page"><fmt:message key='homeButton'/></a>
    <a href="/customer-personal-page-servlet"><fmt:message key='personalPageButton'/></a><br>
    <%@ page import ="model.service.*" %>
    <c:set var="auto" value='${param.autoId}'/>
    <form action="/create-order-servlet" method="POST">
    <input type="hidden" id="autoId" name="autoId" value="${auto}">
    Auto ID: <c:out value="${auto}"/><br>
        <label for="passportDetails"><fmt:message key='order.passportDetails'/></label><br>
        <input type="text" id="passportDetails" name="passportDetails"><br>
        <label for="startDate"><fmt:message key='order.startDate'/></label><br>
        <input type="date" id="startDate" name="date"><br>
        <label for="endDate"><fmt:message key='order.endDate'/></label><br>
        <input type="date" id="endDate" name="date"><br>
        <label for="isHasDriver"><fmt:message key='order.INeedDriver'/> </label>
        <input type="checkbox" id="isHasDriver" name="isHasDriver" value="true"><br>
        <input type="submit" value="<fmt:message key='order.createOrder'/>">
        <c:out value="${error_message}"/>
      </form>
    </body>
</html>