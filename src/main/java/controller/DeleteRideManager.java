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

@WebServlet(
    urlPatterns = {"/account/deleteride", "/account/deleteride/submit"}
)
public class DeleteRideManager extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(Integer.parseInt(request.getParameter("id")));

        request.getSession().setAttribute("route", targetRoute);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/deleteride.jsp?id=" + targetRoute.getRouteID());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RouteManager rm = new RouteManager();

        int routeID = Integer.parseInt(request.getParameter("routeID"));
        String username = request.getRemoteUser();

        GenericDao dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(routeID);

        dao = new GenericDao(User.class);
        User targetUser = (User) dao.getByPropertyLike("username", username).get(0);

        if (rm.getUserPrivileges(routeID, username)) {
            for (Route route : targetUser.getRoutes()) {
                if (targetRoute.getRouteID() == route.getRouteID()) {
                    dao = new GenericDao(Route.class);
                    dao.delete(targetRoute);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/account/deleteridesuccess.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/account/deleteride.jsp?id=" + targetRoute.getRouteID() + "&p=norights");
            dispatcher.forward(request, response);
        }

    }

}
