package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.servlet.admin.UpdateUserServlet;
import com.epam.javalab13.util.PasswordHash;
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
public class ChangePasswordServlet  extends HttpServlet{

    private static Logger logger = Logger.getLogger(ChangePasswordServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String userId = req.getParameter("userID");
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");

        UserService service = new UserService();
        User user = service.getUserById(Integer.parseInt(userId));


        if(user==null){
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
            return;
        }

        String userPassword = user.getPassword();
        if(userPassword.equals(PasswordHash.SHA_256(currentPassword))){
            String newHashedPassword = PasswordHash.SHA_256(newPassword);
            user.setPassword(newHashedPassword);
            service.updateUserPassword(user);


            logger.info("User " + userId + " has changed his password!");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Password changed!\"}");
            return;
        }else{
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Wrong current password!\"}");
            return;
        }


    }
}
