<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>

    <title>Edit Ride Success</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange uppercase">Ride was edited successfully</h1>

        <p><a href="/account" class="btn-sm btn-orange">Return to Profile</a> or <a href="/account/addride.jsp" class="btn-sm btn-orange">Add a Ride</a></p>
    </div>

</body>
</html>
