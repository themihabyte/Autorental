<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
    <html>

    <head>
        <title>index</title>
    </head>

    <body>
        <%@ page import="model.service.*" %>
            <h1><fmt:message key="startPage.welcome"/></h1>
            <c:choose>
                <c:when test="${service['class'].simpleName eq 'UnauthorizedUserService'}">
                    <a href="loginPage.jsp"><fmt:message key="startPage.loginButton"/></a>
                    <a href="registerPage.jsp"><fmt:message key="startPage.registerButton"/></a>
                </c:when>
                <c:when test="${service['class'].simpleName eq 'UserCustomerService'}">
                    <a href="/logout"><fmt:message key="startPage.logoutButton"/></a>
                    <a href="/customer-personal-page-servlet"><fmt:message key="startPage.personalPageButton"/></a>
                </c:when>
                <c:when test="${service['class'].simpleName eq 'UserAdministratorService'}">
                    <a href="/logout"><fmt:message key="startPage.logoutButton"/></a>
                    <form action="/administrator-servlet" method="GET">
                        <input type="hidden" id="action" name="action" value="show_customers">
                        <input type="submit" value="<fmt:message key='startPage.showAllCustomersButton'/>"/>
                    </form>
                    <a href="/register"><fmt:message key="startPage.registerManagerButton"/></a>
                    <a href="/add-automobile"><fmt:message key="startPage.addAutomobileButton"/></a>
                </c:when>
                <c:when test="${service['class'].simpleName eq 'UserManagerService'}">
                    <a href="/logout"><fmt:message key="startPage.logoutButton"/></a>
                    <form action="/manager-servlet" method="GET">
                        <input type="hidden" id="action" name="action" value="show_customers">
                        <input type="submit" value="<fmt:message key='startPage.showAllCustomersButton'/>" />
                    </form>
                </c:when>
            </c:choose>
            <c:url value="/catalogue-servlet" var="importUrl"></c:url>
            <c:import url="${importUrl}"/><br>
            <c:out value="${error_message}"/>
            <form>
                <label for="language"><fmt:message key="startPage.language"/></label>
                <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                </select>
            </form>
    </body>

    </html>