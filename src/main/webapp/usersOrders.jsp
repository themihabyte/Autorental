<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
    <%@ page import="java.util.List" %>
        <html>

        <head>
            <title>Users-orders</title>
        </head>

        <body>
            <a href="/start-page"><fmt:message key='homeButton'/></a>
            <h1><fmt:message key='usersOrders.users'/></h1><br>
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
                                        <input type="submit" value="<fmt:message key='usersOrders.banCustomerButton'/>">
                                    </c:if>
                                    <c:if test="${map.key.isBanned()==true}">
                                        <input type="submit" value="<fmt:message key='usersOrders.unbanCustomerButton'/>">
                                    </c:if>
                                </form>
                        </td>
                        </c:if>
                    </tr>
                    <c:forEach items="${map.value}" var="order">
                        <tr>
                            <td>
                                <fmt:parseDate value = "${order.getStartDate()}" var = "parsedStartDate" pattern = "yyyy-MM-dd" />
                                    <fmt:formatDate type = "date" value = "${parsedStartDate}" />
                                    <c:out value="${'-'}" />
                                    <c:set var = "endDate" value = "${order.getEndDate()}" />
                                    <fmt:parseDate value = "${endDate}" var = "parsedEndDate" pattern = "yyyy-MM-dd" />
                                    <fmt:formatDate type = "date" value = "${parsedEndDate}" />
                            </td>
                            <td>
                                <c:out value="${order.getAutomobile().getManufacturer()}" />
                                <c:out value="${order.getAutomobile().getName()}" />
                            </td>
                            <td>
                                <c:if test="${order.getBill().isPayed()==true}">
                                    <fmt:message key='order.billPayed'/>
                                </c:if>
                                <c:if test="${order.getBill().isPayed()==false}">
                                    <fmt:message key='order.billNotPayed'/>
                                </c:if>
                            </td>
                            <c:if test="${service['class'].simpleName eq 'UserManagerService'}">
                                <td>
                                    <form action="/order-servlet" method="POST">
                                        <input type="hidden" id="order_id" name="order_id" value="${order.getId()}">
                                        <input type="submit" value="<fmt:message key='orderDetailsButton'/>">
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