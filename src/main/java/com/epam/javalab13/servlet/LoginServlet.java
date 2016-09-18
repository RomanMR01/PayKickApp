package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.util.PasswordHash;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Olga on 05.09.2016.
 */
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        resp.setContentType("text/html;charset=UTF-8");

        if(login ==null || password ==null){
            logger.warn("Something going wrong: " + " login = " + login + "; password = " + password);
            resp.getWriter().write("{ \"status\": \"FAIL\", \"url\": \"fail\", \"message\":\"You are doing wrong things!\"}");
            return;
        }

        UserService service = new UserService();
        User user = service.getUserByLogin(login);

        if (user != null) {
            if(user.getPassword().equals(PasswordHash.SHA_256(password))) {
                if(user.isBanned()){
                    resp.getWriter().write("{ \"status\": \"FAIL\", \"url\": \"fail\", \"message\":\"You are banned!\"}");
                    return;
                }

                session.setAttribute("login", login);
                session.setAttribute("fullName", user.getFullName());
                session.setAttribute("role", user.getRole().toString().toLowerCase());
                session.setAttribute("balance", user.getBalance());

                if ("true".equals(rememberMe)) {
                    Cookie cookieLogin = new Cookie("userLogin", login);
                    Cookie cookiePassword = new Cookie("userPassword", password);

                    //Cookies for 31 day
                    cookieLogin.setMaxAge(60 * 60 * 24 * 31);
                    cookiePassword.setMaxAge(60 * 60 * 24 * 31);

                    resp.addCookie(cookieLogin);
                    resp.addCookie(cookiePassword);
                }

                String role = user.getRole().toString().toLowerCase();//URL for by user role
                resp.getWriter().write("{ \"status\": \"OK\", \"url\": \"" + role + "\", \"message\":\"Welcome!\"}");
            }else{
                resp.getWriter().write("{ \"status\": \"FAIL\", \"url\": \"fail\", \"message\":\"Incorrect password!\"}");
            }
        }else{
            resp.getWriter().write("{ \"status\": \"FAIL\", \"url\": \"fail\", \"message\":\"No such user!\"}");
        }
    }
}