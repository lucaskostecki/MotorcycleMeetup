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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Edit ride manager.
 */
@WebServlet(
    urlPatterns = {"/account/editride", "/account/editride/submit"}
)
public class EditRideManager extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RouteManager rm = new RouteManager();

        int routeID = Integer.parseInt(request.getParameter("id"));
        String username = request.getRemoteUser();

        GenericDao dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(routeID);

        dao = new GenericDao(User.class);
        User targetUser = (User) dao.getByPropertyLike("username", username).get(0);

        if (rm.getUserPrivileges(routeID, username)) {
            for (Route route : targetUser.getRoutes()) {
                if (targetRoute.getRouteID() == route.getRouteID()) {
                    request.getSession().setAttribute("route", targetRoute);
                    request.getSession().setAttribute("waypoints", targetRoute.getWaypoints());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String startDateString = sdf.format(targetRoute.getStartDate());
                    request.getSession().setAttribute("startDateString", startDateString);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/account/editride.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/account/editride.jsp?id=" + targetRoute.getRouteID() + "&p=norights");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int currentRouteID = Integer.parseInt(request.getParameter("current-routeID"));
        String routeTitle = request.getParameter("routename");
        String routeDescription = request.getParameter("routedesc");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        boolean publicRide = false;

        // Get the public value
        try {
            publicRide = request.getParameter("public").equals("on");
        } catch (NullPointerException e) {
            publicRide = false;
        }

        // Try to get the zip code
        String zipCode = "";
        try {
            zipCode = request.getParameter("zip-code");
        } catch (NullPointerException e) {
            zipCode = "";
        }

        // Format the date
        String strStartDate = request.getParameter("start-date");
        Date startDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = sdf.parse(strStartDate);
        } catch (ParseException e) {
            logger.error("COULD NOT CONVERT DATE: " + e);
        }

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
        }

        if (request.getParameter("waypoint2") != null) {
            waypoints.add(request.getParameter("waypoint2"));
        }

        if (request.getParameter("waypoint3") != null) {
            waypoints.add(request.getParameter("waypoint3"));
        }

        if (request.getParameter("waypoint4") != null) {
            waypoints.add(request.getParameter("waypoint4"));
        }

        if (request.getParameter("waypoint5") != null) {
            waypoints.add(request.getParameter("waypoint5"));
        }

        if (request.getParameter("waypoint6") != null) {
            waypoints.add(request.getParameter("waypoint6"));
        }

        if (request.getParameter("waypoint7") != null) {
            waypoints.add(request.getParameter("waypoint7"));
        }

        if (request.getParameter("waypoint8") != null) {
            waypoints.add(request.getParameter("waypoint8"));
        }

        if (request.getParameter("waypoint9") != null) {
            waypoints.add(request.getParameter("waypoint9"));
        }

        if (request.getParameter("waypoint10") != null) {
            waypoints.add(request.getParameter("waypoint10"));
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
            targetRoute.setPublicRide(publicRide);
            targetRoute.setZipCode(zipCode);

            dao.saveOrUpdate(targetRoute);

            if (currentRouteID == targetRoute.getRouteID()) {
                logger.debug("EDITED ROUTE: " + targetRoute.getTitle() + " FOR USER: " + targetUsers.get(0).getUserID());

                dao = new GenericDao(Waypoint.class);

                List<Waypoint> routeWaypoints = new ArrayList(targetRoute.getWaypoints());

                int checksum;

                if (routeWaypoints.size() > waypoints.size()) {
                    for (int i = (waypoints.size() - 1); i < routeWaypoints.size(); i++) {
                        routeWaypoints.remove(i);
                        dao.delete(routeWaypoints.get(i));
                        logger.debug("REMOVING EXTRA WAYPOINTS: " + routeWaypoints.get(i));
                    }
                } else {
                    for (int i = 0; i < waypoints.size(); i++) {
                        logger.debug("WAYPOINT FROM ARRAY: " + waypoints.get(i));
                        try {
                            routeWaypoints.get(i);
                            logger.debug("ADDING WAYPOINT");


                            // Check if the waypoint names are the same, if not update them
                            if (!routeWaypoints.get(i).getWaypointName().toLowerCase().equals(waypoints.get(i).toLowerCase())) {
                                routeWaypoints.get(i).setWaypointName(waypoints.get(i));
                                dao.saveOrUpdate(routeWaypoints.get(i));
                            }
                            // If the array of existing points is out of index then we know we have to add a new waypoint
                        } catch (IndexOutOfBoundsException e) {
                            logger.debug("ADDING WAYPOINT");
                            Waypoint newWaypoint = new Waypoint(waypoints.get(i), targetRoute);
                            checksum = dao.insert(newWaypoint);

                            if (checksum > 0)
                                logger.debug("ADDED NEW WAYPOINT: " + newWaypoint.getWaypointName());
                        }
                    }
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
