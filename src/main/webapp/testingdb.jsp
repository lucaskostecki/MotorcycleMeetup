<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 3/10/19
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Testing DB Connection</title>
</head>
<body>
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
</body>
</html>
