const initRide = () => {

    document.querySelector('#add-waypoint').addEventListener('click', function() {
        addWaypoint(null);
    });

}

const removeWaypoint = e => {

    let el = e.target.parentElement;
    let childrenValues = [];

    el.remove();

    let containerChildren = document.querySelectorAll('#waypoint-container .waypoint-node');
    let updatedLength = containerChildren.length;

    for (let i = 0; i < containerChildren.length; i++) {
        childrenValues.push(containerChildren[i].childNodes[2].value);
    }

    document.querySelector('#waypoint-container').innerHTML = '';

    for (let i = 0; i < updatedLength; i++) {
        addWaypoint(childrenValues[i]);
    }

}

const addWaypoint = value => {

    let container = document.querySelector('#waypoint-container');
    let containerChildren = document.querySelectorAll('#waypoint-container .waypoint-node');

    let insertValue = (value != null) ? value : '';

    // Check to see if the user has added more than allowed waypoints
    if (containerChildren.length < 10) {
        let waypointNumber = containerChildren.length + 1;

        let newNode = document.createElement('div');
        newNode.setAttribute('class', 'waypoint-node');

        let br = document.createElement('br');

        let newNodeLabel = document.createElement('label');
        newNodeLabel.setAttribute('for', `node${waypointNumber}`);
        newNodeLabel.innerText = `Waypoint ${waypointNumber}`;

        let newNodeInput = document.createElement('input');
        newNodeInput.setAttribute('type', 'text');
        newNodeInput.setAttribute('id', `node${waypointNumber}`);
        newNodeInput.setAttribute('data-node-number', `${waypointNumber}`);
        newNodeInput.setAttribute('placeholder', 'Waypoint address or name');
        newNodeInput.setAttribute('value', insertValue);

        let removeNodeButton = document.createElement('button');
        removeNodeButton.setAttribute('type', 'button');
        removeNodeButton.setAttribute('class', 'btn-sm btn-dark');
        removeNodeButton.addEventListener('click', removeWaypoint);
        removeNodeButton.innerText = 'X';

        newNode.append(br);
        newNode.append(br);
        newNode.append(newNodeLabel);
        newNode.append(br);
        newNode.append(newNodeInput);
        newNode.append(removeNodeButton);

        container.append(newNode);
    }

}

const getWaypointsAsArray = () => {
    let values = [];
    let containerChildren = document.querySelectorAll('#waypoint-container .waypoint-node');

    for (let i = 0; i < containerChildren.length; i++) {
        values.push({
            location: containerChildren[i].childNodes[2].value,
            stopover: true
        });
    }

    return values;
}

const updateMap = (directionsService, directionsDisplay) => {

    let waypoints = getWaypointsAsArray();
    let avoidHighways = document.querySelector('#avoid-highways').checked ? true : false;

    directionsService.route({
        origin: document.querySelector('#start').value,
        destination: document.querySelector('#end').value,
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
    initRide();
}