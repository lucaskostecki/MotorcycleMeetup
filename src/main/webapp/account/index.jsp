<%@ page import="entity.Route" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>My Rides</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange uppercase">Welcome <% if(request.getRemoteUser() != null) out.print(request.getRemoteUser()); %></h1>

        <div>
            <h2 class="uppercase">Your rides</h2>

            <div id="ride-item-container">
                <c:choose>
                    <c:when test="${fn:length(routesList) > 0}">
                        <c:forEach var="route" items="${routesList}">
                            <c:if test="${fn:length(route.title) > 0}">
                                <div class="ride-item">
                                    <h2 class="rides-list-title uppercase"><a href="/viewroute?id=${route.routeID}" class="orange">${route.title}</a></h2>

                                    <div class="rides-list-descriptive">
                                        <p><a href="/account/editride?id=${route.routeID}"><i class="fas fa-edit ride-list-icon"></i>EDIT</a></p>

                                        <c:if test="${fn:length(route.description) > 0}">
                                            <p class="uppercase">
                                                <i class="fas fa-calendar-day ride-list-icon"></i>${route.dateAdded}
                                            </p>
                                        </c:if>

                                        <c:if test="${fn:length(route.description) > 0}">
                                            <p class="uppercase">
                                                <i class="fas fa-align-left ride-list-icon"></i>${route.description}
                                            </p>
                                        </c:if>

                                        <c:if test="${fn:length(route.start) > 0}">
                                            <p class="uppercase">
                                                <i class="fas fa-arrow-alt-circle-right ride-list-icon"></i>${route.start}
                                            </p>
                                        </c:if>

                                        <p class="uppercase">
                                            <i class="fas fa-map-marker-alt ride-list-icon"></i>${fn:length(waypoints)}
                                        </p>

                                        <c:if test="${fn:length(route.end) > 0}">
                                            <p class="uppercase">
                                                <i class="fas fa-flag-checkered ride-list-icon"></i>${route.end}
                                            </p>
                                        </c:if>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>It seems that you don't have any rides... <a href="/account/addride.jsp" class="btn-sm btn-orange">Add Ride</a></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

</body>
</html>
