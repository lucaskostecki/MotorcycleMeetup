package controller;

import entity.Route;
import entity.User;
import entity.Waypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
    urlPatterns = {"/account/editride", "/account/editride/submit"}
)
public class EditRideManager extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("route", targetRoute);
        request.getSession().setAttribute("waypoints", targetRoute.getWaypoints());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/editride.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentRouteID = Integer.parseInt(request.getParameter("current-routeID"));
        String routeTitle = request.getParameter("routename");
        String routeDescription = request.getParameter("routedesc");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String startDate = request.getParameter("start-date");
        String startTime = request.getParameter("start-time");
        boolean routeAvoidHighways = false;

        try {
            routeAvoidHighways = request.getParameter("avoid-highways").equals("on") ? true : false;
        } catch (NullPointerException e) {
            routeAvoidHighways = false;
        }

        Route targetRoute = null;

        ArrayList<String> waypoints = new ArrayList<>();

        if (request.getParameter("waypoint1") != null) {
            waypoints.add(request.getParameter("waypoint1"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint2") != null) {
            waypoints.add(request.getParameter("waypoint2"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint3") != null) {
            waypoints.add(request.getParameter("waypoint3"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint4") != null) {
            waypoints.add(request.getParameter("waypoint4"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint5") != null) {
            waypoints.add(request.getParameter("waypoint5"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint6") != null) {
            waypoints.add(request.getParameter("waypoint6"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint7") != null) {
            waypoints.add(request.getParameter("waypoint7"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint8") != null) {
            waypoints.add(request.getParameter("waypoint8"));
        }

        if (request.getParameter("waypoint9") != null) {
            waypoints.add(request.getParameter("waypoint9"));
        } else {
            waypoints.add(null);
        }

        if (request.getParameter("waypoint10") != null) {
            waypoints.add(request.getParameter("waypoint10"));
        } else {
            waypoints.add(null);
        }

        if (routeTitle.length() != 0 &&
                routeDescription.length() != 0 &&
                start.length() != 0 &&
                end.length() != 0) {

            GenericDao dao = null;
            dao = new GenericDao(User.class);
            List<User> targetUsers = dao.getByPropertyLike("username", request.getRemoteUser());

            dao = new GenericDao(Route.class);
            targetRoute = (Route) dao.getById(currentRouteID);

            targetRoute.setStart(start);
            targetRoute.setEnd(end);
            targetRoute.setTitle(routeTitle);
            targetRoute.setDescription(routeDescription);
            targetRoute.setAvoidHighways(routeAvoidHighways);
            targetRoute.setStartDate(startDate);
            targetRoute.setStartTime(startTime);

            dao.saveOrUpdate(targetRoute);

            if (currentRouteID == targetRoute.getRouteID()) {
                logger.debug("EDITED ROUTE: " + targetRoute.getTitle() + " FOR USER: " + targetUsers.get(0).getUserID());

                dao = new GenericDao(Waypoint.class);

                int i = 0;
                int oldWaypointID = -1;

                for (Waypoint waypoint : targetRoute.getWaypoints()) {
                    logger.debug("WAYPOINT FROM ARRAY: " + waypoints.get(i));

                    // Check if form input waypoints is null
                    if (waypoints.get(i) != null && !waypoints.get(0).equals(waypoint.getWaypointName())) {
                        // If it is not null then update the old waypoint to signify it was already there
                        // Set the waypoint name to the input form waypoint name
                        logger.debug("UPDATING WAYPOINT " + waypoint.getWaypointName() + " TO: " + waypoints.get(i));

                        oldWaypointID = waypoint.getWaypointID();
                        waypoint.setWaypointName(waypoints.get(i));
                        dao.saveOrUpdate(waypoint);
                    } else if (waypoints.get(i) == null) {
                        dao.delete(waypoint);
                        logger.debug("REMOVING WAYPOINT FROM DB");
                    } else {
                        Waypoint newWaypoint = new Waypoint(waypoints.get(i), targetRoute);
                        int checksum = dao.insert(newWaypoint);

                        if (checksum > 0) {
                            logger.debug("NEW WAYPOINT ADDED: " + newWaypoint.getWaypointName());
                        } else {
                            logger.error("FAILED TO ADD WAYPOINT");
                        }
                    }

                    if (oldWaypointID == waypoint.getWaypointID()) {
                        logger.debug("EDITED WAYPOINT: " + waypoint.getWaypointName());
                    } else {
                        logger.error("FAILED TO EDIT WAYPOINT");
                    }

                    i++;
                }

                response.sendRedirect("/account/editridesuccess.jsp");
            } else {
                logger.error("Could not update route");
                response.sendRedirect("/account/editride?id=" + targetRoute.getRouteID() + "&p=generalerror");
            }
        } else {
            response.sendRedirect("/account/editride?id=" + targetRoute.getRouteID() + "&p=missingdata");
        }
    }

}
