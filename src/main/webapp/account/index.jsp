<%@ page import="entity.Route" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>
    <script src="/js/account.js"></script>

    <title>Account</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1>Welcome <% if(request.getRemoteUser() != null) out.print(request.getRemoteUser()); %></h1>

        <div class="rides-list-container">
            <h2 style="color: #000;">Your rides</h2>

            <ul id="rides-list">
                <c:forEach var="route" items="${routesList}">
                    <li>
                        <h2>${route.title}</h2>
                        <p>${route.start}</p>
                        <p>${route.end}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

</body>
</html>
