<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 3/25/19
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Login Error</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>
    <div class="container">
        <p class="orange">There was a problem signing you in</p>
        <p><a href="/account" class="btn-sm btn-orange">Try Again</a></p>
    </div>

</body>
</html>
