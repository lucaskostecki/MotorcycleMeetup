package controller;

import entity.Route;
import entity.User;
import persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/search"}
)

public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao dao;

        String search = request.getParameter("q");

        if (search != null) {
            dao = new GenericDao(User.class);
            request.setAttribute("users", dao.getByPropertyLike("username", search));

            dao = new GenericDao(Route.class);
            request.setAttribute("routes", dao.getByPropertyLike("title", search));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchresults.jsp");
        dispatcher.forward(request, response);
    }

}
