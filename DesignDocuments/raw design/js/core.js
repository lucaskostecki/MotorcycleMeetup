const init = () => {
    let optionsArrow = document.querySelector("#optionsArrow");
    let optionsMenu = document.querySelector("#optionsMenu");

    optionsArrow.addEventListener("click", function() {
        optionsArrow.classList.toggle("menuArrowRotate");
        optionsMenu.classList.toggle("optionsMenuShow");
    });
}

window.onload = init;