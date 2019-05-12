<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Share Ride - ${route.title}</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange uppercase">Share Ride</h1>

        <% if (request.getParameter("p") != null && request.getParameter("p").equals("generalerror")) { %>
            <p class="form-error">An error has occurred while processing your request.</p>
        <% } else if (request.getParameter("p") != null && request.getParameter("p").equals("norights")) { %>
            <p class="form-error">You don't have sufficient permissions to share this route.</p>
        <% } else { %>
            <form action="/account/shareride/submit" method="post">
                <input type="hidden" name="routeID" value="${route.routeID}" />

                <label for="email">Enter email to share with</label>
                <br>
                <input type="email" id="email" name="email" />

                <br>
                <br>
                <button type="button" id="cancel" class="btn-lg btn-dark">Cancel</button>
                <button type="submit" class="btn-lg btn-orange">Invite</button>
            </form>
        <% } %>
    </div>

    <script>
        document.querySelector('#cancel').addEventListener('click', function() {
            window.history.back();
        });
    </script>
</body>
</html>
