package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.servlet.LogoutServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vikno on 9/11/2016.
 */
public class UpdateUserServlet  extends HttpServlet{

    private static Logger logger = Logger.getLogger(UpdateUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String updateType = req.getParameter("type");

        UserService userService = new UserService();

        if(updateType!=null && updateType.length()>0){
            if(updateType.equalsIgnoreCase("ROLE")){
                String userId = req.getParameter("userID");
                String role = req.getParameter("role");

                userService.changeUserRole(Integer.parseInt(userId),role.toUpperCase());

                logger.info("Admin " + session.getAttribute("login") + " change role to " + role + " for user " + userId);
                return;

            }

            if(updateType.equalsIgnoreCase("BANN")){
                String userId = req.getParameter("userID");
                String bann = req.getParameter("bann");

                logger.info("Admin " + session.getAttribute("login") + " change bann status to " + bann + " for user " + userId);

                if(Boolean.valueOf(bann)){
                    userService.setUserBan(Integer.parseInt(userId));
                }else{
                    userService.resetUserBan(Integer.parseInt(userId));
                }
                return;
            }

            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Something going wrong! Refresh the page, please!\"}");
            return;
        }else{
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
            return;
        }
    }
}
