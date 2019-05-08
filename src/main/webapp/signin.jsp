<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 3/25/19
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Sign In</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>
    <div class="container">
        <h1 class="orange uppercase">Sign in to continue</h1>

        <% if (request.getParameter("e") == "true") { %>
            <p>There was a problem signing you in</p>
        <% } %>

        <form action="j_security_check" method="post">
            <input type="text" placeholder="Username" name="j_username" autofocus />
            <br>
            <input type="password" placeholder="Password" name="j_password" />
            <br><br>
            <input class="btn-sm btn-orange" type="submit" value="Log in" />
        </form>

        <a href="/signup.jsp">Don't have an account?</a>
    </div>

</body>
</html>
