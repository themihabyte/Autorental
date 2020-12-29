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
            <a href="/start-page"><fmt:message key='homeButton'/></a><br>
            <c:if test="${service['class'].simpleName eq 'UserCustomerService'}">
                <a href="/customer-personal-page-servlet"><fmt:message key='personalPageButton'/></a><br>
            </c:if>
            <tr>
                <th><fmt:message key='order.orderId'/></th>
                <th>
                    <c:out value="${order.getId()}" />
                </th>
            </tr><br>
            <tr>
                <th><fmt:message key='order.passportDetails'/></th>
                <th>
                    <c:out value="${order.getPassportDetails()}" />
                </th>
            </tr><br>
            <tr>
                <th><fmt:message key='order.period'/></th>
                <th>
                    <fmt:parseDate value = "${order.getStartDate()}" var = "parsedStartDate" pattern = "yyyy-MM-dd" />
                    <fmt:formatDate type = "date" value = "${parsedStartDate}" />
                    <c:out value="${'-'}" />
                    <c:set var = "endDate" value = "${order.getEndDate()}" />
                    <fmt:parseDate value = "${endDate}" var = "parsedEndDate" pattern = "yyyy-MM-dd" />
                    <fmt:formatDate type = "date" value = "${parsedEndDate}" />
                </th>
            </tr><br>
            <tr>
                <th><fmt:message key='order.automobile'/></th>
                <th>
                    <c:out value="${order.getAutomobile().getManufacturer()}" />
                    <c:out value="${order.getAutomobile().getName()}" />
                    <c:out value="${order.getAutomobile().getSegment()}"/>
                </th>
            </tr><br>
            <tr>
                <th>
                    <c:if test="${order.isHasDriver() == true}">
                        <fmt:message key='order.INeedDriver'/>
                    </c:if>
                </th>
            </tr><br>
            <c:choose>
                <c:when test="${order.isDenied() == false}">
                    <tr>
                        Bill:<br>
                        <th>
                            <c:out value="${order.getBill().getPrice()}" /><br>
                            <c:if test="${order.getBill().isPayed() == true}">
                                <fmt:message key='order.billPayed'/>
                            </c:if>
                            <c:if test="${order.getBill().isPayed() == false}">
                                <c:if test="${service['class'].simpleName eq 'UserCustomerService'}">
                                    <form action="/payment-servlet" method="POST">
                                        <input type="hidden" id="bill_id" name="bill_id"
                                            value="${order.getBill().getOrderId()}">
                                        <input type="submit" value="<fmt:message key='bill.pay'/>">
                                    </form>
                                    <c:out value="${payment_error_message}" />
                                </c:if>
                            </c:if>
                        </th>

                        <c:if test="${service['class'].simpleName eq 'UserManagerService'}">
                            <c:if test="${order.getAutomobile().isInStock() == false}">
                                <form action="/manager-servlet" method="POST">
                                    <input type="hidden" name="action" value="deny_order" />
                                    <input type="hidden" name="order_id" value="${order.getId()}" />
                                    <label for="rejection_reason"><fmt:message key='order.rejectionReason'/></label><br>
                                    <input type="text" id="rejection_reason" name="rejection_reason" /><br>
                                    <input type="submit" value="<fmt:message key='order.deny'/>" />
                                </form>

                                <form action="/manager-servlet" method="POST">
                                    <input type="hidden" name="action" value="receive_automobile" />
                                    <input type="hidden" id="automobile_id" name="automobile_id"
                                        value="${order.getAutomobileID()}" />
                                    <input type="hidden" id="bill_id" name="bill_id"
                                        value="${order.getBill().getOrderId()}" />
                                    <label for="bill_price"><fmt:message key='order.price'/></label><br>
                                    <input type="text" name="bill_price" value="${order.getBill().getPrice()}" />
                                    <input type="submit" value="Automobile received">
                                </form>
                            </c:if>
                        </c:if>
                    </tr>
                </c:when>
                <c:when test="${order.isDenied() == true}">
                    <fmt:message key='order.isDenied'/>
                    <c:out value="${order.getRejectionReason()}" />
                </c:when>
            </c:choose><br>
            <c:out value="${error_message}" />
        </body>

        </html>