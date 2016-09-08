package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.common.RestorePassword;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.util.PasswordHash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vikno on 9/7/2016.
 */
public class NewPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("new password servlet get:");

        Map<String,RestorePassword> restorePasswordMap = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");

        if(restorePasswordMap!=null) {
            System.out.println(restorePasswordMap.size());

            String uid = req.getParameter("uid");
            System.out.println(uid);

            if(uid!=null && uid.length()==5) {
                System.out.println("uid:" + uid);
                System.out.println("restore uid:" + restorePasswordMap.get(uid));

                RestorePassword restorePassword = restorePasswordMap.get(uid);

                Date today = new Date();

                 if(restorePassword!=null) {
                     if(today.getTime()>restorePassword.getEndTime().getTime()){
                         req.setAttribute("message","Time has passed!Try again");
                         req.getRequestDispatcher("new_password.jsp").forward(req, resp);
                     }else{
                         UserService service = new UserService();
                         User user = service.getUserByLogin(restorePassword.getUserLogin());

                         if(user!=null){
                             System.out.println(user);

                             HttpSession session = req.getSession();
                             session.setAttribute("restoreUID", uid);
                             session.setAttribute("restoreUser", user);
                             req.getRequestDispatcher("new_password.jsp").forward(req, resp);
                         }else{
                             resp.sendRedirect("home");
                         }
                     }

                }else{
                    resp.sendRedirect("home");
                }
            }else{
                resp.sendRedirect("home");
            }
        }else{
            resp.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("new password servlet post:");

        HttpSession session = req.getSession();
        if(session!=null){
            System.out.println("session: " + session);

            User user = (User) session.getAttribute("restoreUser");
            System.out.println("user" + user);

            if(user!=null){
                String newPassword = req.getParameter("newPassword");

                if(newPassword!=null){
                    System.out.println("new pass:" + newPassword);

//                    user.setPassword(PasswordHash.SHA_256(newPassword));
                    user.setPassword(newPassword);
                    new UserService().updateUserPassword(user);

                    System.out.println("new user" + user);

                    Map<String,RestorePassword> restorePasswordMap = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");

                    String restoreUID = (String) session.getAttribute("restoreUID");
                    System.out.println(restoreUID);

                    restorePasswordMap.remove(restoreUID);
                    getServletContext().setAttribute("restore", restorePasswordMap);

                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Password restored! Go to home page and log in with new password!\"}");
                }else{

                }

            }else{
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Something going wrong! Try again!\"}");
            }
        }else{
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Something going wrong! Try again!\"}");
        }

    }
}
