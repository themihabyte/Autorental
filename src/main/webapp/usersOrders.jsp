<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <html>

        <head>
            <title>Personal page</title>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        </head>

        <body>
            <a href="/start-page">Home</a>
            <h1>Users</h1><br>
            <c:forEach items="${usersOrdersMap}" var="map">
                <table>
                    <tr>
                        <th>
                            <c:out value="${map.key.getUsername()}" />
                        </th>
                        <td>
                            <c:if test="${service['class'].simpleName eq 'UserAdministratorService'}">
                                <form action="/administrator-servlet" method="POST">
                                    <input type="hidden" name="action" value="ban_customer">
                                    <input type="hidden" name="customer_id" value="${map.key.getId()}">
                                    <c:if test="${map.key.isBanned()==false}">
                                        <input type="submit" value="Ban customer">
                                    </c:if>
                                    <c:if test="${map.key.isBanned()==true}">
                                        <input type="submit" value="Unban customer">
                                    </c:if>
                                </form>
                        </td>
                        </c:if>
                    </tr>
                    <c:forEach items="${map.value}" var="order">
                        <tr>
                            <td>
                                <c:out value="${order.getStartDate()}" />
                                <c:out value="${'-'}" />
                                <c:out value="${order.getEndDate()}" />
                            </td>
                            <td>
                                <c:out value="${order.getAutomobile().getManufacturer()}" />
                                <c:out value="${order.getAutomobile().getName()}" />
                            </td>
                            <td>
                                <c:if test="${order.getBill().isPayed()==true}">
                                    Payed
                                </c:if>
                                <c:if test="${order.getBill().isPayed()==false}">
                                    Not payed
                                </c:if>
                            </td>
                            <c:if test="${service['class'].simpleName eq 'UserManagerService'}">
                                <td>
                                    <form action="/manager-servlet" method="POST">
                                        <input type="hidden" id="action" name="order_id" value="${element.getId()}">
                                        <input type="submit" value="Details">
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>

                </table><br>
            </c:forEach>
            <c:out value="${error_message}" />

        </body>

        </html>