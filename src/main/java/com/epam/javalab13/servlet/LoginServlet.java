package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.util.PasswordHash;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Olga on 05.09.2016.
 */
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    private String login;
    private String password;
    private User user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("LoginServlet homeStatistics");
        login = req.getParameter("login");
        password = req.getParameter("password");

        resp.setContentType("text/html;charset=UTF-8");
        try {
            UserService service = new UserService();
            user = service.findUserByLoginAndPassword(login, PasswordHash.SHA_256(password));
            if (user != null && user.getEmail() != null) {
                //here need to put all data that is needed about user
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                //session.setAttribute("role", user.getRole());
                resp.getWriter().write("success");
                return;
            }
        } catch (Exception e) {
            logger.warn("LoginServlet error: ", e);
        }
        resp.getWriter().write("Login failed");
    }
}