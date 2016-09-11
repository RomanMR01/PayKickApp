package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.common.RestorePassword;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.util.PasswordHash;
import org.apache.log4j.Logger;

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

    private static Logger logger = Logger.getLogger(NewPasswordServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String,RestorePassword> restorePasswordMap = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");

        if(restorePasswordMap!=null) {

            String uid = req.getParameter("uid");

            if(uid!=null && uid.length()==64) {

                RestorePassword restorePassword = restorePasswordMap.get(uid);


                 if(restorePassword!=null) {
                     Date today = new Date();
                     long now = today.getTime();
                     long end = restorePassword.getEndTime().getTime();

                     if(today.getTime()>restorePassword.getEndTime().getTime()){
                         req.setAttribute("hideForm","hideForm");

                         if(session.getAttribute("restoreUID")!=null){
                             session.removeAttribute("restoreUID");
                         }
                         if(session.getAttribute("restoreUser")!=null){
                             session.removeAttribute("restoreUser");
                         }

                         restorePasswordMap.remove(uid);
                         getServletContext().setAttribute("restore", restorePasswordMap);

                         Map<String,RestorePassword> restorePasswordMap2 = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");
                         req.getRequestDispatcher("new_password.jsp").forward(req, resp);
                     }else{
                         UserService service = new UserService();
                         User user = service.getUserByLogin(restorePassword.getUserLogin());

                         if(user!=null){
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
        HttpSession session = req.getSession();

        if(session!=null){
            User user = (User) session.getAttribute("restoreUser");

            if(user!=null){
                String newPassword = req.getParameter("newPassword");

                if(newPassword!=null){
                    user.setPassword(PasswordHash.SHA_256(newPassword));
                    new UserService().updateUserPassword(user);
                    logger.info("User "+ user.getId() + " has successfully restore password!");
                    Map<String,RestorePassword> restorePasswordMap = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");

                    String restoreUID = (String) session.getAttribute("restoreUID");

                    restorePasswordMap.remove(restoreUID);
                    getServletContext().setAttribute("restore", restorePasswordMap);

                    if(session.getAttribute("restoreUID")!=null){
                        session.removeAttribute("restoreUID");
                    }
                    if(session.getAttribute("restoreUser")!=null){
                        session.removeAttribute("restoreUser");
                    }

                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Password restored!Go to home page and log in with new password!\"}");
                }else{

                }

            }else{
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Something going wrong! Go to home page and try again!\"}");
            }
        }else{
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Something going wrong! Go to home page and try again!\"}");
        }

    }
}
