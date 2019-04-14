const init = () => {
    let optionsArrow = document.querySelector("#optionsArrowButton");
    let optionsMenu = document.querySelector("#optionsMenu");
    let navSearch = document.querySelector("#navSearchBar");

    optionsArrow.addEventListener("click", function() {
        optionsArrow.classList.toggle("menuArrowRotate");
        optionsMenu.classList.toggle("optionsMenuShow");
    });

    navSearch.addEventListener("keyup", function(e) {
        if (e.keyCode == 13) {
            e.preventDefault();
            window.location.href = `/search?q=${navSearch.value}`;
        }

    });
}

window.onload = init;