let directionsService = null;
let directionsDisplay = null;

const initRide = () => {



}

const getWaypointsAsArray = () => {
    let values = [];
    let containerChildren = document.querySelectorAll('#waypoint-inputs input');

    console.log(containerChildren);

    for (let i = 0; i < containerChildren.length; i++) {
        values.push({
            location: containerChildren[i].value,
            stopover: true
        });
    }

    return values;
}

const updateMap = (directionsService, directionsDisplay) => {

    let waypoints = getWaypointsAsArray();
    let avoidHighways = document.querySelector('#avoid-highways').value == 'true' ? true : false;
    let start = document.querySelector('#start').value;
    let end = document.querySelector('#end').value;

    console.log(directionsService);
    console.log(directionsDisplay);

    console.log(avoidHighways);
    console.log(waypoints);
    console.log(start);
    console.log(end);

    directionsService.route({
        origin: start,
        destination: end,
        waypoints: waypoints,
        optimizeWaypoints: true,
        travelMode: 'DRIVING',
        avoidHighways: avoidHighways
    }, function(response, status) {
        if (status == 'OK') {
            directionsDisplay.setDirections(response);
            let route = response.routes[0];
        } else {
            window.alert(`Failed to find route: ${status}`);
        }
    });

}

let previousOnload = window.onload;
window.onload = () => {
    previousOnload();

    setTimeout(() => {
        initRide();
    }, 3000);
}