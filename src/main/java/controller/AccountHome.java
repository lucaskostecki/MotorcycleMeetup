package controller;

import entity.User;
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
    urlPatterns = {"/account"}
)
public class AccountHome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GenericDao dao = new GenericDao(User.class);
        User targetUser = (User)dao.getByPropertyLike("username", request.getRemoteUser()).get(0);
        List routeList = new ArrayList(targetUser.getRoutes());
        request.getSession().setAttribute("routesList", routeList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/index.jsp");
        dispatcher.forward(request, response);

    }

}
