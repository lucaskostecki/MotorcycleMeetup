const initAccount = () => {

    let ridesListContainer = document.querySelector('#rides-list');

    fetch('/account/getridesaslist')
        .then(resp => resp.json())
        .then(data => {
            data.rides;
        });

    ridesList.append(rideItem);

}

let previousOnload = window.onload;
window.onload = () => {
    previousOnload();
    initAccount();
}