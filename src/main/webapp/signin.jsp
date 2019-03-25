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
    <title>Sign In</title>
</head>
<body>

    <form action="j_security_check" method="post">
        <input type="text" placeholder="Username" name="j_username" />
        <input type="text" placeholder="Password" name="j_password" />

        <input type="submit" value="Log in" />
    </form>

</body>
</html>
