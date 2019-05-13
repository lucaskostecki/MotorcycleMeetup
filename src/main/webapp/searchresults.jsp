<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 3/12/19
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Search Results For ${search}</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange uppercase">Search results</h1>
        <%--
        <c:choose>
            <c:when test="${empty users}">
                <p class="orange uppercase">No users found</p>
            </c:when>
            <c:otherwise>
                <table class="uppercase search-result-table">
                    <tr>
                        <th>Username</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Phone</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        --%>




        <c:choose>
            <c:when test="${empty routes}">
                <p class="orange uppercase">No rides found</p>
            </c:when>
            <c:otherwise>
                <table class="uppercase search-result-table" style="margin-top: 30px;">
                    <p class="orange uppercase">Ride results</p>
                    <tr>
                        <th>Route Title</th>
                        <th>Route Description</th>
                        <th>Start</th>
                        <th>Waypoints</th>
                        <th>End</th>
                    </tr>
                    <c:forEach items="${routes}" var="route">
                        <tr>
                            <td class="uppercase">
                                <a href="/viewride?id=${route.routeID}" target="_blank">${route.title}</a>
                            </td>
                            <td>${route.description}</td>
                            <td>${route.start}</td>
                            <td>${fn:length(route.waypoints)} Planned stops</td>
                            <td>${route.end}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>



        <c:if test="${!empty routesByZipCode}">
            <table class="uppercase search-result-table" style="margin-top: 30px;">
                <p class="orange uppercase">Ride results by zip code</p>
                <tr>
                    <th>Route Title</th>
                    <th>Route Description</th>
                    <th>Start</th>
                    <th>Waypoints</th>
                    <th>End</th>
                    <th>ZIP Code</th>
                </tr>
                <c:forEach items="${routesByZipCode}" var="route">
                    <tr>
                        <td class="uppercase">
                            <a href="/viewride?id=${route.routeID}" target="_blank">${route.title}</a>
                        </td>
                        <td>${route.description}</td>
                        <td>${route.start}</td>
                        <td>${fn:length(route.waypoints)} Planned stops</td>
                        <td>${route.end}</td>
                        <td>${route.zipCode}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
