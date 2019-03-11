/*
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
        urlPatterns = {"/search"}
)

public class SearchUsers extends HttpServlet {

    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        GenericDao dao = new GenericDao(User.class);
        UserDao udao = new UserDao();

        if (req.getParameter("submit").equals("search")) {
//            req.setAttribute("users", udao.getUsersByUsername(req.getParameter("search")));
            req.setAttribute("users", udao.getUsersByUsername("testing"));
        } else {
            req.setAttribute("users", dao.getAll());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/testingdb.jsp");
        dispatcher.forward(req, res);
    }

}
*/


package controller;

import entity.User;
import persistence.GenericDao;
import persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao = new GenericDao(User.class);
        UserDao udao = new UserDao();

        String search = req.getParameter("search");
        String searchType = req.getParameter("searchType");

        req.setAttribute("users", udao.getUsersByUsername("testing"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/testingdb.jsp");
        dispatcher.forward(req, resp);
    }
}