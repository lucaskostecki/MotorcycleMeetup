<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Account</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1>Welcome <% if(request.getRemoteUser() != null) out.print(request.getRemoteUser()); %></h1>
    </div>

</body>
</html>
