<!-- Index -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <c:import url="/include/head.jsp"></c:import>
    <script src="/js/viewride.js"></script>

    <title>View Ride - ${route.title}</title>
</head>
<body>

    <c:import url="/include/nav.jsp"></c:import>

    <div class="container">
        <div class="row">
            <h1 class="orange uppercase">VIEW RIDE</h1>

            <div class="col-8">
                <div id="map"></div>
            </div>
            <div class="col-4">
                <input type="hidden" id="avoid-highways" value="${route.avoidHighways}">
                <input type="hidden" id="start" value="${route.start}">
                <input type="hidden" id="end" value="${route.end}">

                <span id="waypoint-inputs">
                    <c:forEach var="waypoint" items="${waypoints}">
                        <input type="hidden" value="${waypoint.waypointName}">
                    </c:forEach>
                </span>

                <div id="view-ride-container">
                    <h2 class="uppercase">${route.title}</h2>

                    <p class="uppercase">
                        <i class="fas fa-align-left ride-list-icon"></i>${route.description}
                    </p>

                    <p class="uppercase">
                        <i class="fas fa-arrow-alt-circle-right ride-list-icon"></i>${route.start}
                    </p>

                    <c:forEach var="waypoint" items="${waypoints}">
                        <p class="uppercase">
                            <i class="fas fa-map-marker-alt ride-list-icon"></i>${waypoint.waypointName}
                        </p>
                    </c:forEach>

                    <p class="uppercase">
                        <i class="fas fa-flag-checkered ride-list-icon"></i>${route.end}
                    </p>
                </div>
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

            directionsService = new google.maps.DirectionsService;
            directionsDisplay = new google.maps.DirectionsRenderer({
                // draggable: true,
                map: map
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

            updateMap(directionsService, directionsDisplay);
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