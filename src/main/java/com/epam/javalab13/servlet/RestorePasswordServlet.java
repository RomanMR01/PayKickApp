package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.common.RestorePassword;
import com.epam.javalab13.service.game.UserService;
import com.epam.javalab13.util.MailSender;
import com.epam.javalab13.util.PasswordHash;
import com.epam.javalab13.util.UrlGenerator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Vikno on 9/7/2016.
 */
public class RestorePasswordServlet extends HttpServlet{
    private Map<String,RestorePassword> restorePasswordMap = new HashMap<>();
    private static Logger logger = Logger.getLogger(RestorePasswordServlet.class);
    private static ResourceBundle bundle = ResourceBundle.getBundle("security/config");//Here stored data for mail sending

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("restore",restorePasswordMap);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("restore_password.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");

        if(email!=null) {
            resp.setContentType("text/html;charset=UTF-8");

            UserService service = new UserService();
            User user = service.getUserByEmail(email);

            if (user != null) {
                Map<String, RestorePassword> restorePasswordMap = (Map<String, RestorePassword>) getServletContext().getAttribute("restore");

                Date current = new Date();
                //TODO return this
//                Date end = new Date(current.getTime() + (1000 * 60 * 60 * 24));//For one day!
                Date end = new Date(current.getTime() + (1000 * 60 *5));//For two minute!

                String uid = UrlGenerator.getFixedUrl(user.getLogin(),current);

                restorePasswordMap.put(uid, new RestorePassword(current, end, user.getLogin()));
                getServletContext().setAttribute("restore", restorePasswordMap);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MailSender sender = new MailSender(bundle.getString("mail.email"), bundle.getString("mail.password"));//server parameters
                        String restorePasswordMessage = "<p>We heard that you lost your PayKick password. Sorry about that!</p>\n" +
                                "\t<p>But don't worry! You can use the following link within the next day to reset your password:</p>\n" +
                                "\t<p><a href='http://localhost:8080/PayKick/newPassword?uid=" + uid + "'>" +
                                 "http://localhost:8080/PayKick/newPassword?uid=" + uid + "</a></p>" +
                                "\t<p>If you don't use this link within 24 hours, it will expire.</p>";
                        logger.info("Restore password mail are sent to user " + user.getId());
                        sender.sendEmail("[PayKick] Please reset your password!", restorePasswordMessage, user.getEmail());
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
