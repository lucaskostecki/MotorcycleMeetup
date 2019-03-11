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
import java.util.List;

@WebServlet(
        urlPatterns = {"/search"}
)

public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao udao = new UserDao();

        req.setAttribute("users", udao.getUsersByUsername("testing"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/testingdb.jsp");
        dispatcher.forward(req, resp);
    }

}
