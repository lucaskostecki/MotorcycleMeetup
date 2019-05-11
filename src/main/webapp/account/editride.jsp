<%@ page import="entity.Route" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>
    <script src="/js/editride.js"></script>

    <title>Edit Ride - ${route.title}</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1 class="orange uppercase">Edit Ride</h1>

        <% if (request.getParameter("p") != null && request.getParameter("p").equals("missingdata")) { %>
            <p class="form-error">You must fill in all necessary data.</p>
        <% } else if (request.getParameter("p") != null && request.getParameter("p").equals("generalerror")) { %>
            <p class="form-error">An error has occurred while processing your request.</p>
        <% } else if (request.getParameter("p") != null && request.getParameter("p").equals("norights")) { %>
            <p class="form-error">You don't have sufficient permissions to edit this route.</p>
        <% } else { %>

            <form action="/account/editride/submit" method="post">
                <div class="col-8">
                    <div id="map"></div>
                </div>
                <div class="col-4">
                    <label for="start-time">Start Date and Time</label>
                    <br>
                    <input type="date" id="start-date" name="start-date" value="${startDateString}" required />
                    <br>
                    <input type="time" id="start-time" name="start-time" value="${route.startTime}" required />

                    <br><br>
                    <input type="hidden" name="current-routeID" value="${route.routeID}">
                    <label for="routename">Ride Name</label>
                    <br>
                    <input type="text" id="routename" name="routename" value="${route.title}" />

                    <br><br>
                    <label for="routedesc">Ride Description</label>
                    <br>
                    <input type="text" id="routedesc" name="routedesc" value="${route.description}"/>

                    <br><br>
                    <label for="start">Start</label>
                    <br>
                    <input type="text" id="start" name="start" value="${route.start}" />

                    <br><br>
                    <label>Waypoints</label>
                    <br>
                    <div id="waypoint-container"></div>
                    <br>
                    <button id="add-waypoint" class="btn-sm btn-orange" type="button">+ Add Waypoint</button>

                    <br><br>
                    <label for="end">End</label>
                    <br>
                    <input type="text" id="end" name="end" value="${route.end}" />

                    <br><br>
                    <input type="checkbox" id="avoid-highways" name="avoid-highways"
                        <c:if test="${route.avoidHighways}">checked</c:if>
                    >
                    <label for="avoid-highways">Avoid Highways</label>

                    <br><br>
                    <input type="checkbox" id="public" name="public"
                           <c:if test="${route.publicRide}">checked</c:if>
                    >
                    <label for="public">Make Ride Publicly Visible</label>

                    <br>
                    <br>
                    <button type="button" id="update-map" class="btn-lg btn-orange">Update Map</button>
                    <button type="submit" class="btn-lg btn-orange">Save Changes</button>
                </div>
            </form>
        <% } %>

        <input type="hidden" id="avoid-highways-input" value="${route.avoidHighways}">
        <span id="waypoint-inputs">
            <c:forEach var="waypoint" items="${waypoints}">
                <input type="hidden" data-waypoint-id="${waypoint.waypointID}" value="${waypoint.waypointName}">
            </c:forEach>
        </span>
    </div>

    <script>
        let map = null;

        function initMap() {
            map = new google.maps.Map(document.querySelector('#map'), {
                center: {lat: 43.085, lng: -89.476},
                zoom: 12
            });

            directionsService = new google.maps.DirectionsService;
            directionsDisplay = new google.maps.DirectionsRenderer({
                // draggable: true,
                map: map
            });

            document.querySelector('#update-map').addEventListener('click', function() {
                updateMap(directionsService, directionsDisplay);
            });

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    let pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    let initMarker = new google.maps.Marker({
                        position: pos,
                        map: map,
                        title: ''
                    });

                    map.setCenter(pos);
                    map.setZoom(12);
                }, function() {
                    handleLocationError(true, infoWindow, map.getCenter());
                });
            } else {
                // Browser doesn't support Geolocation
                handleLocationError(false, infoWindow, map.getCenter());
            }

            initEditRide();
        }

        const handleLocationError = (browserHasGeolocation, infoWindow, pos) => {
            infoWindow.setPosition(pos);
            infoWindow.setContent(browserHasGeolocation ?
                'Error: The Geolocation service failed.' :
                'Error: Your browser doesn\'t support geolocation.');
            infoWindow.open(map);
        }
    </script>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBZP0zbpUvcDB3dhRQ6rwBGWtL7swgjp1M&callback=initMap"
            async defer></script>

</body>
</html>
