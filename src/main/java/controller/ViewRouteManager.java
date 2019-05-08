package controller;

import entity.Route;
import entity.User;
import entity.Waypoint;
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
    urlPatterns = {"/viewroute"}
)
public class ViewRouteManager extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GenericDao dao;
        dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("route", targetRoute);

        request.getSession().setAttribute("waypoints", targetRoute.getWaypoints());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewride.jsp");
        dispatcher.forward(request, response);

    }

}
