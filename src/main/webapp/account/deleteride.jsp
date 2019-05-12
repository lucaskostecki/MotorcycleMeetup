<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Delete Ride - ${route.title}</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange uppercase">Delete Ride</h1>

        <% if (request.getParameter("p") != null && request.getParameter("p").equals("generalerror")) { %>
            <p class="form-error">An error has occurred while processing your request.</p>
        <% } else if (request.getParameter("p") != null && request.getParameter("p").equals("norights")) { %>
            <p class="form-error">You don't have sufficient permissions to edit this route.</p>
        <% } else { %>

            <form action="/account/deleteride/submit" method="post">
                <p>Are you sure you want to delete ride <em>${route.title}</em>?</p>

                <input type="hidden" name="routeID" value="${route.routeID}" />

                <input type="checkbox" id="delete" name="delete" required />
                <label for="delete">Confirm deletion</label>

                <br>
                <br>
                <button type="button" id="cancel-delete" class="btn-lg btn-dark">Cancel</button>
                <button type="submit" class="btn-lg btn-orange">Delete</button>
            </form>
        <% } %>
    </div>

    <script>
        document.querySelector('#cancel-delete').addEventListener('click', function() {
            window.history.back();
        });
    </script>
</body>
</html>
