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

    <title>Search Results</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange">Search results</h1>
        <c:choose>
            <c:when test="${empty users}">
                <p class="orange uppercase">No users found</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><c:out value="${user.firstName}"/></td>
                            <td><c:out value="${user.lastName}"/></td>
                            <td><c:out value="${user.username}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><c:out value="${user.phone}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${empty routes}">
                <p class="orange uppercase">No routes found</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Route Title</th>
                        <th>Route Description</th>
                    </tr>
                    <c:forEach items="${routes}" var="route">
                        <tr>
                            <td><c:out value="${route.title}"/></td>
                            <td><c:out value="${route.description}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
