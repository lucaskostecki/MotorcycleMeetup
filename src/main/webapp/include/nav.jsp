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
            <% if(request.getRemoteUser() != null) { %>
                <li>
                    <a href="/account/">
                        <div id="accountImageMini"></div>
                    </a>
                </li>
                <li>
                    <%= request.getRemoteUser() %>
                </li>
                <li>
                    <button type="button" id="optionsArrowButton" class="transitionMedium">
                        <a href="/account/">
                            <i class="fas fa-angle-down transitionMedium" id="optionsArrow"></i>
                        </a>
                    </button>
                    <div id="optionsMenu" class="transitionFast">
                        <ul>
                            <li><a href="">Add Ride</a></li>
                            <li><a href="">Requests</a></li>
                            <li><a href="">History</a></li>
                        </ul>
                    </div>
                </li>
            <% } else { %>
                <li>
                    <a href="/account/" class="navLink transitionMedium">Sign In</a>
                </li>
            <% }%>
        </ul>
    </div>
</nav>