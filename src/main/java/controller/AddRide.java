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
        urlPatterns = {"/account/addroute/submit"}
)

public class AddRide extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routeTitle = request.getParameter("routename");
        String routeDescription = request.getParameter("routedesc");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

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

            int checksum = 0;
            GenericDao dao = null;
            dao = new GenericDao(User.class);
            List<User> targetUsers = dao.getByPropertyLike("username", request.getRemoteUser());

            dao = new GenericDao(Route.class);
            Route newRoute = new Route(routeTitle, routeDescription, targetUsers.get(0));
            checksum = dao.insert(newRoute);

            if (checksum > 0) {
                Route targetRoute = (Route)dao.getById(checksum);
                logger.debug("NEW ROUTE: " + targetRoute.getTitle() + " FOR USER: " + targetUsers.get(0).getUserID());

                dao = new GenericDao(Waypoint.class);

                for (String waypointName : waypoints) {
                    Waypoint newWaypoint = new Waypoint(waypointName, targetRoute);
                    checksum = dao.insert(newWaypoint);

                    if (checksum > 0) {
                        logger.debug("NEW WAYPOINT ADDED: " + waypointName);
                    } else {
                        logger.error("FAILED TO ADD WAYPOINT");
                    }
                }

                response.sendRedirect("/account/addridesuccess.jsp");
            } else {
                logger.error("Could not add new route");
                response.sendRedirect("/account/addride.jsp?p=generalerror");
            }
        } else {
            response.sendRedirect("/account/addride.jsp?p=missingdata");
        }
    }

}
