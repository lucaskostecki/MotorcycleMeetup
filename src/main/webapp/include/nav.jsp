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
                    <a href="/account/" class="link-no-style navLink transitionMedium">
                        <%= request.getRemoteUser() %>
                    </a>
                </li>
                <li>
                    <button type="button" id="optionsArrowButton" class="transitionMedium">
                        <i class="fas fa-angle-down transitionMedium" id="optionsArrow"></i>
                    </button>
                    <div id="optionsMenu" class="transitionFast">
                        <ul>
                            <li><a href="">Add Ride</a></li>
                            <li><a href="">Requests</a></li>
                            <li><a href="/signout">Sign Out</a></li>
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