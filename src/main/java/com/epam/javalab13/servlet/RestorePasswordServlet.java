package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.common.RestorePassword;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.util.MailSender;
import com.epam.javalab13.util.PasswordHash;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vikno on 9/7/2016.
 */
public class RestorePasswordServlet extends HttpServlet{
    private Map<String,RestorePassword> restorePasswordMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("restore",restorePasswordMap);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("restore password servlet");
        req.getRequestDispatcher("restore_password.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        if(email!=null) {
            System.out.println("rest pass:" + email);

            resp.setContentType("text/html;charset=UTF-8");

            UserService service = new UserService();
            User user = service.getUserByEmail(email);

            if (user != null) {
                Map<String, RestorePassword> restorePasswordMap = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");
                Date current = new Date();
                Date end = new Date(current.getTime() + (1000 * 20));//For one day!

                String uid = "abc12";//TODO generate

                restorePasswordMap.put(uid, new RestorePassword(current, end, user.getLogin()));
                getServletContext().setAttribute("restore", restorePasswordMap);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MailSender sender = new MailSender("paykick.team@gmail.com", "paykickteam5");//server parameters
                        String restorePasswordMessage = "For restoring your password follow <a href='http://localhost:8080/PayKick/newPassword?uid=" + uid + "'>this</a> link!";
                        sender.sendEmail("Restoring password.", restorePasswordMessage, user.getEmail());
                    }
                }).start();

                resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Email are sent! Check your mailbox, please!\"}");

            } else {
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"No such email address in db!\"}");
            }
        }else{
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Something going wrong! Try again!\"}");
        }
    }


}
