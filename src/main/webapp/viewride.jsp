<!-- Index -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>
    <script src="/js/viewride.js"></script>

    <title>Motorcycle Meetup</title>
</head>
<body>

    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <div class="row">
            <h1 class="orange uppercase">${route.title}</h1>

            <div class="col-8">
                <div id="map"></div>
            </div>
            <div class="col-4">
                <ul>
                    <c:forEach var="waypoint" items="${waypoints}">
                        <li>
                            ${waypoint.waypointName}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
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

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBZP0zbpUvcDB3dhRQ6rwBGWtL7swgjp1M&callback=initMap"
            async defer></script>

</body>
</html>