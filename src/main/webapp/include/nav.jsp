<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="nav">
    <div id="leftNav">
        <a href="/" id="mm-main-logo">
            <h1 class="orange">MM</h1>
        </a>
    </div>
    <div id="rightNav">
        <ul>
            <li>
                <input id="navSearchBar" type="text" name="search" placeholder="Search routes or by ZIP" />
            </li>

            <% if(request.getRemoteUser() != null) { %>
                <li>
                    <a href="/account/addride.jsp" class="nav-link">+ New Ride</a>
                </li>
                <li>
<%--                    <a href="/account/" class="link-no-style">--%>
<%--                        <div id="accountImageMini"></div>--%>
<%--                    </a>--%>
                    <a href="/account" class="link-no-style nav-link transitionMedium">
                        <%= request.getRemoteUser() %>
                    </a>
                </li>

            <% } else { %>
                <li>
                    <a href="/account" class="nav-link transitionMedium">Sign In</a>
                </li>
            <% }%>
            <li>
                <button type="button" id="optionsArrowButton" class="transitionMedium">
                    <i class="fas fa-angle-down transitionMedium" id="optionsArrow"></i>
                </button>
                <div id="optionsMenu" class="transitionFast">
                    <ul>
                        <% if(request.getRemoteUser() != null) { %>
                            <li><a href="/account/">My Rides</a></li>
                            <%-- <li><a href="/account/requests.jsp">Ride Requests</a></li> --%>
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