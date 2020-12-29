<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
    <%@ page import="java.util.List" %>
        <html>

        <head>
            <title>Personal page</title>
        </head>

        <body>
            <a href="/start-page"><fmt:message key='homeButton'/></a>
            <h1><fmt:message key='personalPage.yourOrders'/>,
                <c:out value="${service.getUser().getUsername()}" />
            </h1><br>
            <table style="width:100%">
                <c:forEach items="${list}" var="element">
                    <tr>
                        <th>
                            <c:out value="${element.getId()}" />
                        </th>
                        <th>
                            <c:out value="${element.getStartDate()}" />
                            <c:out value="${'-'}" />
                            <c:out value="${element.getEndDate()}" />
                        </th>
                        <th>
                            <c:out value="${element.getAutomobile().getManufacturer()}" />
                            <c:out value="${element.getAutomobile().getName()}" />
                        </th>
                        <th>
                            <form action="/order-servlet" method="POST">
                                <input type="hidden" id="order_id" name="order_id" value="${element.getId()}">
                                <input type="submit" value="<fmt:message key='orderDetailsButton'/>">
                            </form>
                        </th>
                    </tr>
                </c:forEach>
                <c:out value="${error_message}" />
            </table>
        </body>

        </html>