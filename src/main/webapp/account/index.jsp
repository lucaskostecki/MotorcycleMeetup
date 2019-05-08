<%@ page import="entity.Route" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <h1 class="orange">Welcome <% if(request.getRemoteUser() != null) out.print(request.getRemoteUser()); %></h1>

        <div>
            <h2>Your rides</h2>

            <div id="ride-item-container">
                <c:forEach var="route" items="${routesList}">
                    <c:if test="${fn:length(route.title) > 0}">
                        <div class="ride-item">
                            <h2 class="rides-list-title"><a href="/viewroute?id=${route.routeID}" class="orange">${route.title}</a></h2>
                            <p><a href="/account/editroute?id=${route.routeID}"><i class="fas fa-edit ride-list-icon"></i>Edit</a></p>
                            <div class="rides-list-descriptive">
                                <c:if test="${fn:length(route.description) > 0}">
                                    <p>
                                        <i class="fas fa-align-left ride-list-icon"></i>${route.description}
                                    </p>
                                </c:if>
                                <c:if test="${fn:length(route.start) > 0}">
                                    <p>
                                        <i class="fas fa-arrow-alt-circle-right ride-list-icon"></i>${route.start}
                                    </p>
                                </c:if>
                                <c:if test="${fn:length(route.end) > 0}">
                                    <p>
                                        <i class="fas fa-flag-checkered ride-list-icon"></i>${route.end}
                                    </p>
                                </c:if>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>

</body>
</html>
