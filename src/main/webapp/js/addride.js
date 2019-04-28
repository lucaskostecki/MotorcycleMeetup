const initRide = () => {

    document.querySelector('#add-waypoint').addEventListener('click', addWaypoint);

}

const removeWaypoint = e => {

    let el = e.target.parentElement;

    el.remove();

}

const addWaypoint = () => {

    let container = document.querySelector('#waypoint-container');
    let containerChildren = document.querySelectorAll('#waypoint-container .waypoint-node');

    console.log(containerChildren);

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

let previousOnload = window.onload;
window.onload = () => {
    previousOnload();
    initRide();
}