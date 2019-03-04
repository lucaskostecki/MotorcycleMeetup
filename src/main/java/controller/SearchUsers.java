package controller;

import entity.User;
import persistence.GenericDao;
import persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUsers extends HttpServlet {

    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        GenericDao dao = new GenericDao(User.class);
        UserDao udao = new UserDao();

        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("users", udao.getUsersByUsername(req.getParameter("search")));
        } else {
            req.setAttribute("users", dao.getAll());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, res);
    }

}
