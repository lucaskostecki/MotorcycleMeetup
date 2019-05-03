package controller;

import entity.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String waypoint1 = request.getParameter("waypoint1");
        String waypoint2 = request.getParameter("waypoint2");
        String waypoint3 = request.getParameter("waypoint3");
        String waypoint4 = request.getParameter("waypoint4");
        String waypoint5 = request.getParameter("waypoint5");
        String waypoint6 = request.getParameter("waypoint6");
        String waypoint7 = request.getParameter("waypoint7");
        String waypoint8 = request.getParameter("waypoint8");
        String waypoint9 = request.getParameter("waypoint9");
        String waypoint10 = request.getParameter("waypoint10");



        if (routeTitle != null &&
            start != null &&
            end != null) {

            int checksum = 0;

            GenericDao dao = new GenericDao(Route.class);
            Route newRoute = new Route();
            checksum = dao.insert(newRoute);

            if (checksum > 0) {

            } else {
                logger.error("Could not add new route");
                response.sendRedirect("/account/addride.jsp");
            }
        }
    }

}
