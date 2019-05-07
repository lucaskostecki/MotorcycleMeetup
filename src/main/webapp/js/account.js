const initAccount = () => {

    let ridesListContainer = document.querySelector('#rides-list');

    fetch('')
        .then(resp => resp.json())
        .then(data => {
            console.log(data.rides);
        });

    // ridesList.append(rideItem);

}

let previousOnload = window.onload;
window.onload = () => {
    previousOnload();
    initAccount();
}