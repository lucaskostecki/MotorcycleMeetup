package controller;

import entity.Route;
import entity.User;
import entity.Waypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Add routes
 */
@WebServlet(
        urlPatterns = {"/account/editride/submit"}
)

public class EditRideSubmit extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentRouteID = Integer.parseInt(request.getParameter("current-routeID"));
        String routeTitle = request.getParameter("routename");
        String routeDescription = request.getParameter("routedesc");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String startDate = request.getParameter("start-date");
        String startTime = request.getParameter("start-time");
        boolean routeAvoidHighways = request.getParameter("avoid-highways").equals("on") ? true : false;

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
                logger.debug("NEW ROUTE: " + targetRoute.getTitle() + " FOR USER: " + targetUsers.get(0).getUserID());

                dao = new GenericDao(Waypoint.class);

                int i = 0;
                int oldWaypointID = -1;
                for (Waypoint waypoint : targetRoute.getWaypoints()) {
                    if (waypoints.get(i) == null) {
                        dao.delete(waypoint);
                    } else {
                        oldWaypointID = waypoint.getWaypointID();
                        waypoint.setWaypointName(waypoints.get(i));
                        dao.saveOrUpdate(waypoint);
                    }

                    if (oldWaypointID == waypoint.getWaypointID()) {
                        logger.debug("NEW WAYPOINT ADDED: " + waypoint.getWaypointName());
                    } else {
                        logger.error("FAILED TO ADD WAYPOINT");
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
