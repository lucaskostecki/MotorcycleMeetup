package controller;

import entity.Role;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Sign up users
 */
@WebServlet(
        urlPatterns = {"/signup/submit"}
)

public class SignUp extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (username != null &&
            email != null &&
            firstname != null &&
            lastname != null &&
            password != null &&
            password2 != null) {

            if (password.equals(password2)) {

                GenericDao dao = new GenericDao(User.class);

                User newUser = new User(1, username, email,null, firstname, lastname, password);

                int checksum = dao.insert(newUser);

                if (checksum > 0) {
                    logger.info("Added new user");

//                    User searchedUser = (User) dao.getByPropertyLike("username", username).get(0);

                    Role newUserRole = new Role("user", newUser);
                    dao = new GenericDao(Role.class);
                    checksum = dao.insert(newUserRole);

                    if (checksum > 0) {
                        logger.info("Added new user to roles");
                        response.sendRedirect("/signin.jsp?p=signupcomplete");
                    } else {
                        logger.error("Could not add user to roles");
                        response.sendRedirect("/signup.jsp?p=error+roles");
                    }
                } else {
                    logger.error("Could not add new user");
                    response.sendRedirect("/signup.jsp?p=error+signup");
                }

            } else {
                logger.error("Passwords do not match; New user not added");
                logger.error(password + " != " + password2);
                response.sendRedirect("/signup.jsp?p=error+password");
            }
        }
    }

}
