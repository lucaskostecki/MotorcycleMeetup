<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="nav">
    <div id="leftNav">
        <a href="/" id="mm-main-logo">
            <h1>MM</h1>
        </a>
    </div>
    <div id="rightNav">
        <ul>
            <li>
                <input id="navSearchBar" type="text" name="search" placeholder="Search routes or users" />
            </li>

            <% if(request.getRemoteUser() != null) { %>
                <li>
                    <a href="/account/myrides.jsp" class="navLink transitionMedium">My Rides</a>
                </li>
                <li>
<%--                    <a href="/account/" class="link-no-style">--%>
<%--                        <div id="accountImageMini"></div>--%>
<%--                    </a>--%>
                    <a href="/account/" class="link-no-style navLink transitionMedium">
                        <%= request.getRemoteUser() %>
                    </a>
                </li>

            <% } else { %>
                <li>
                    <a href="/account/" class="navLink transitionMedium">Sign In</a>
                </li>
            <% }%>
            <li>
                <button type="button" id="optionsArrowButton" class="transitionMedium">
                    <i class="fas fa-angle-down transitionMedium" id="optionsArrow"></i>
                </button>
                <div id="optionsMenu" class="transitionFast">
                    <ul>
                        <% if(request.getRemoteUser() != null) { %>
                            <li><a href="/account/addride.jsp">+ New Ride</a></li>
                            <li><a href="/account/requests">Ride Requests</a></li>
                            <li><a href="/signout">Sign Out</a></li>
                        <% } else { %>
                            <li><a href="/signup.jsp">Sign Up</a></li>
                        <% } %>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</nav>