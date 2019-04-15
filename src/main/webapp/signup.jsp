<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 4/14/19
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Sign Up</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>
    <div class="container">
        <h1>Sign up</h1>

        <form action="/signup" method="post">
            <input type="text" placeholder="Username" name="username" autofocus />
            <br>
            <input type="password" placeholder="Password" name="password" />
            <br>
            <input type="password" placeholder="Retype Password" name="password2" />

            <br><br>
            <input class="btn-sm btn-dark" type="submit" value="Sign Up" />
        </form>
    </div>

</body>
</html>
