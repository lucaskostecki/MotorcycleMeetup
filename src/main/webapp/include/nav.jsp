<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="nav">
    <div id="leftNav">
        <a href="/">
            <h1>MM</h1>
        </a>
    </div>
    <div id="rightNav">
        <ul>
            <li>
                <input id="navSearchBar" type="text" name="search" placeholder="Search" />
            </li>
            <li>
                <a href="/account/myrides.jsp" class="navLink transitionMedium">My Rides</a>
            </li>
            <li>
                <div id="accountImageMini"></div>
            </li>
            <li>
                <button type="button" id="optionsArrowButton" class="transitionMedium">
                    <i class="fas fa-angle-down transitionMedium" id="optionsArrow"></i>
                </button>
                <div id="optionsMenu" class="transitionFast">
                    <ul>
                        <li><a href="">Test</a></li>
                        <li><a href="">Test</a></li>
                        <li><a href="">Test</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</nav>