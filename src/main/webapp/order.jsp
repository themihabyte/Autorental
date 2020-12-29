<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <html>

        <head>
            <title>Personal page</title>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        </head>

        <body>
            <a href="/start-page">Home</a><br>
            <c:if test="${service['class'].simpleName eq 'UserCustomerService'}">
                <a href="/customer-personal-page-servlet">Personal page</a><br>
            </c:if>
            <tr>
                <th>Order number:</th>
                <th>
                    <c:out value="${order.getId()}" />
                </th>
            </tr><br>
            <tr>
                <th>Passport:</th>
                <th>
                    <c:out value="${order.getPassportDetails()}" />
                </th>
            </tr><br>
            <tr>
                <th>Period:</th>
                <th>
                    <c:out value="${order.getStartDate()}" />
                    <c:out value="${'-'}" />
                    <c:out value="${order.getEndDate()}" />
                </th>
            </tr><br>
            <tr>
                <th>Automobile:</th>
                <th>
                    <c:out value="${order.getAutomobile().getManufacturer()}" />
                    <c:out value="${order.getAutomobile().getName()}" />
                </th>
            </tr><br>
            <tr>
                <th>Driver:</th>
                <th>
                    <c:out value="${order.isHasDriver()}" />
                </th>
            </tr><br>
            <c:choose>
                <c:when test="${order.isDenied() == false}">
                    <tr>
                        Bill:<br>
                        <th>
                            <c:out value="${order.getBill().getPrice()}" /><br>
                            <c:if test="${order.getBill().isPayed() == true}">
                                Payed
                            </c:if>
                            <c:if test="${order.getBill().isPayed() == false}">
                                <c:if test="${service['class'].simpleName eq 'UserCustomerService'}">
                                    <form action="/payment-servlet" method="POST">
                                        <input type="hidden" id="bill_id" name="bill_id"
                                            value="${order.getBill().getOrderId()}">
                                        <input type="submit" value="Pay">
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
                                    <label for="rejection_reason">Rejection reason</label><br>
                                    <input type="text" id="rejection_reason" name="rejection_reason" /><br>
                                    <input type="submit" value="Deny" />
                                </form>

                                <form action="/manager-servlet" method="POST">
                                    <input type="hidden" name="action" value="receive_automobile" />
                                    <input type="hidden" id="automobile_id" name="automobile_id"
                                        value="${order.getAutomobileID()}" />
                                    <input type="hidden" id="bill_id" name="bill_id"
                                        value="${order.getBill().getOrderId()}" />
                                    <label for="bill_price">Price:</label><br>
                                    <input type="text" name="bill_price" value="${order.getBill().getPrice()}" />
                                    <input type="submit" value="Automobile received">
                                </form>
                            </c:if>
                        </c:if>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:out value="${'Order is denied, reason:'}" />
                    <c:out value="${order.getRejectionReason()}" />
                </c:otherwise>
            </c:choose>
            <c:out value="${error_message}" />
        </body>

        </html>