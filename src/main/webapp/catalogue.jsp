<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <html>

        <head>
            <title>Catalogue</title>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        </head>

        <body>
            <h1>Catalogue</h1><br>
            <form action="/start-page" method="GET">
                <tr>
                    <th>
                        <select name="sort" id="sort">
                            <option value="none">None</option>
                            <option value="value-asc">By Value↓</option>
                            <option value="value-desc">By Value↑</option>
                            <option value="alphabet">Alphabetical</option>
                        </select>
                    </th>
                    <select name="filter" id="manufacturer_filter">
                        <option value="none">None</option>
                        <c:forEach items="${manufacturers}" var="manufacturer">
                            <option value="${manufacturer}">
                                <c:out value='${manufacturer}' />
                            </option>
                        </c:forEach>
                    </select>
                    <th>
                        <select name="filter" id="segment_filter">
                            <option value="none">None</option>
                            <c:forTokens items="A,B,C,D,E,F,J,M,S" delims="," var="segment">
                                <option value="${segment}">
                                    <c:out value='${segment}' />
                                </option>
                            </c:forTokens>
                        </select>
                    </th>
                    <th>
                        <input type="submit" value="Apply filters" />
                    </th>
                </tr>
            </form>
            <table style="width:100%">
                <c:forEach items="${list}" var="element">
                    <tr>
                        <th>
                            <c:out value="${element.getManufacturer()}" />
                        </th>
                        <td>
                            <c:out value="${element.getName()}" />
                        </td>
                        <td>
                            <c:out value="${element.getSegment()}" />
                            <c:out value="${'-class'}" />
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${element.isInStock() == true}">
                                    Available
                                </c:when>
                                <c:otherwise>
                                    Not Available
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:out value="${element.getPrice()}" />
                            <c:out value="${'$'}" />
                        </td>
                        <td>

                            <c:if test="${service['class'].simpleName eq 'UserCustomerService'}">
                                <form action="/create-order" method="GET">
                                    <input type="hidden" id="autoId" name="autoId" value="${element.getId()}">
                                    <input type="submit" value="Order" />
                                </form>
                            </c:if>
                            <c:if test="${service['class'].simpleName eq 'UserAdministratorService'}">
                                <form action="/administrator-servlet" method="POST">
                                    <input type="hidden" id="action" name="action" value="delete_automobile">
                                    <input type="hidden" id="automobile_id" name="automobile_id" value="${element.getId()}">
                                    <input type="submit" value="Delete" />
                                </form>
                                <form action="/add-automobile" method="POST">
                                    <input type="hidden" id="action" name="action" value="update_automobile">
                                    <input type="hidden" id="automobile_id" name="automobile_id" value="${element.getId()}">
                                    <input type="hidden" id="automobile_name" name="automobile_name" value="${element.getName()}">
                                    <input type="hidden" id="automobile_manufacturer" name="automobile_manufacturer" value="${element.getManufacturer()}">
                                    <input type="hidden" id="automobile_price" name="automobile_price" value="${element.getPrice()}">
                                    <input type="submit" value="Change information" />
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>