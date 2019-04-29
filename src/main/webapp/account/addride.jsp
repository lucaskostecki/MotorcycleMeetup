<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>
    <script src="/js/addride.js"></script>

    <title>Account</title>
</head>
<body>
    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <h1>Add Ride</h1>

        <form action="/account/addride/submit" method="post">
            <div class="col-8">
                <div id="map"></div>
            </div>
            <div class="col-4">
                <label for="routename">Ride Name</label>
                <br>
                <input type="text" id="routename" name="routename" />

                <br><br>
                <label for="start">Start</label>
                <br>
                <input type="text" id="start" name="start" />

                <br><br>
                <label>Waypoints</label>
                <br>
                <div id="waypoint-container"></div>
                <br>
                <button id="add-waypoint" class="btn-sm btn-dark" type="button">+ Add Waypoint</button>

                <br><br>
                <label for="end">End</label>
                <br>
                <input type="text" id="end" name="end" />

                <br><br>
                <input type="checkbox" id="avoid-highways">
                <label for="avoid-highways">Avoid Highways</label>

                <br>
                <br>
                <button type="button" id="update-map" class="btn-lg btn-dark">Update Map</button>
                <button type="submit" class="btn-lg btn-dark">Save</button>
            </div>
        </form>
    </div>

    <script>
        let map = null;

        function initMap() {
            map = new google.maps.Map(document.querySelector('#map'), {
                center: {lat: 43.085, lng: -89.476},
                zoom: 12
            });

            let directionsService = new google.maps.DirectionsService;
            let directionsDisplay = new google.maps.DirectionsRenderer({
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
        }

        const handleLocationError = (browserHasGeolocation, infoWindow, pos) => {
            infoWindow.setPosition(pos);
            infoWindow.setContent(browserHasGeolocation ?
                'Error: The Geolocation service failed.' :
                'Error: Your browser doesn\'t support geolocation.');
            infoWindow.open(map);
        }
    </script>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCI2U2ju-sI9j7TT7yqrrQLruGTwtpi5P8&callback=initMap"
            async defer></script>
</body>
</html>
