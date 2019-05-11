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
    urlPatterns = {"/viewroute"}
)
public class ViewRouteManager extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GenericDao dao;
        dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("route", targetRoute);

        request.getSession().setAttribute("waypoints", targetRoute.getWaypoints());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = sdf.format(targetRoute.getStartDate());
        request.getSession().setAttribute("startDateString", startDateString);

        sdf = new SimpleDateFormat("MM-dd-yyyy");
        String readableDate = sdf.format(targetRoute.getStartDate());
        request.getSession().setAttribute("readableDate", readableDate);

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm aa");

            Date convertedDate = inputFormat.parse(targetRoute.getStartTime());
            String readableTime = outputFormat.format(convertedDate);
            request.getSession().setAttribute("readableTime", readableTime);
        } catch (ParseException e) {
            logger.error("FAILED TO CONVERT TIME: " + e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewride.jsp");
        dispatcher.forward(request, response);

    }

}
