package com.epam.javalab13.servlet;

import com.epam.javalab13.model.Gender;
import com.epam.javalab13.model.Role;
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
import java.sql.SQLException;

/**
 * Created by Olga on 05.09.2016.
 */
public class RegisterServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegisterServlet.class);
    private UserService userService;
    private String name = "";
    private String surName = "";
    private String gender = "";
    private String login = "";
    private String password = "";
    private String email = "";
    private int age;


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("RegisterServlet doPost");

        name = request.getParameter("name");
        surName = request.getParameter("surname");
        email = request.getParameter("email");
        login = request.getParameter("name");
        password = request.getParameter("password");
        gender = request.getParameter("sex");
        age = Integer.valueOf(request.getParameter("age"));

        response.setContentType("text/html;charset=UTF-8");

        try {
            //todo check unique login value, need to finish User Service before
            /*if (UserService.findUserByLogin(login)) {
                //login already exist
                logger.info("The login is already used");
                response.getWriter().write("The login is already used");
                return;
            }*/

        } catch (Exception e) {
            logger.warn("RegisterServlet error: ", e);
            response.getWriter().write("Failed to register");
        }

        try {
            String encryptedPassword = "";
            if (password != null) {
                encryptedPassword = PasswordHash.SHA_256(password);
            }
            User user = new User();
            user.setLogin(login);
            user.setFullName(name+" "+surName);
            user.setAge(age);
            user.setPassword(encryptedPassword);
            user.setEmail(email);
            user.setRole(Role.CLIENT);
            if (gender.toLowerCase().equals("male")){
                user.setGender(Gender.MALE);
            }else {
                user.setGender(Gender.FEMALE);
            }

            userService=new UserService();
            userService.addUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", user.getRole());
            response.getWriter().write("True");

        } catch (SQLException e) {
            logger.warn("Exception while inserting user: ", e);
            response.getWriter().write("Failed to register");
        }
    }
}