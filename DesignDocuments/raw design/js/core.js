const init = () => {
    // Get the items for nav
    let optionsArrowButton = document.querySelector("#optionsArrowButton");
    let optionsArrow = document.querySelector("#optionsArrow");
    let optionsMenu = document.querySelector("#optionsMenu");

    // Add click event to arrow button
    optionsArrowButton.addEventListener("click", function() {
        // Add rotate class to arrow not button
        optionsArrow.classList.toggle("menuArrowRotate");
        // Show the menu by adding class
        optionsMenu.classList.toggle("optionsMenuShow");
    });
}

window.onload = init;