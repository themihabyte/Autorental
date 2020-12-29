<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
    <%@ page import="java.util.List" %>
        <html>

        <head>
            <title>Catalogue</title>
        </head>

        <body>
            <h1><fmt:message key="catalogue.catalogue"/></h1><br>
            <form action="/start-page" method="GET">
                <tr>
                    <th>
                        <select name="sort" id="sort">
                            <option value="none"><fmt:message key="catalogue.option.noSorting"/></option>
                            <option value="value-asc"><fmt:message key="catalogue.option.sortByValueAscending"/></option>
                            <option value="value-desc"><fmt:message key="catalogue.option.sortByValueDescending"/></option>
                            <option value="alphabet"><fmt:message key="catalogue.option.sortAlphabetically"/></option>
                        </select>
                    </th>
                    <select name="filter" id="manufacturer_filter">
                        <option value="none"><fmt:message key="catalogue.option.noFilter"/></option>
                        <c:forEach items="${manufacturers}" var="manufacturer">
                            <option value="${manufacturer}">
                                <c:out value='${manufacturer}' />
                            </option>
                        </c:forEach>
                    </select>
                    <th>
                        <select name="filter" id="segment_filter">
                            <option value="none"><fmt:message key='catalogue.option.noFilter'/></option>
                            <c:forTokens items="A,B,C,D,E,F,J,M,S" delims="," var="segment">
                                <option value="${segment}">
                                    <c:out value='${segment}' />
                                </option>
                            </c:forTokens>
                        </select>
                    </th>
                    <th>
                        <input type="submit" value="<fmt:message key='catalogue.applyFilters'/>" />
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
                            <fmt:message key='automobile.class'/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${element.isInStock() == true}">
                                    <fmt:message key="automobile.isInStock"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="automobile.isNotInStock"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:set var = "price" value = "${element.getPrice()}" />
                            <fmt:formatNumber value="${price}" type="currency"/>
                        </td>
                        <td>
                        <c:if test="${element.isInStock() == true}">
                            <c:if test="${service['class'].simpleName eq 'UserCustomerService'}">
                                <form action="/create-order" method="GET">
                                    <input type="hidden" id="autoId" name="autoId" value="${element.getId()}">
                                    <input type="submit" value="<fmt:message key='catalogue.orderButton'/>" />
                                </form>
                            </c:if>
                        </c:if>
                            <c:if test="${service['class'].simpleName eq 'UserAdministratorService'}">
                                <form action="/administrator-servlet" method="POST">
                                    <input type="hidden" id="action" name="action" value="delete_automobile">
                                    <input type="hidden" id="automobile_id" name="automobile_id" value="${element.getId()}">
                                    <input type="submit" value="<fmt:message key='catalogue.deleteButton'/>" />
                                </form>
                                <form action="/add-automobile" method="POST">
                                    <input type="hidden" id="action" name="action" value="update_automobile">
                                    <input type="hidden" id="automobile_id" name="automobile_id" value="${element.getId()}">
                                    <input type="hidden" id="automobile_name" name="automobile_name" value="${element.getName()}">
                                    <input type="hidden" id="automobile_manufacturer" name="automobile_manufacturer" value="${element.getManufacturer()}">
                                    <input type="hidden" id="automobile_price" name="automobile_price" value="${element.getPrice()}">
                                    <input type="submit" value="<fmt:message key='catalogue.changeInformationButton'/>" />
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>