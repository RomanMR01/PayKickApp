package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.service.game.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vikno on 9/6/2016.
 */
public class AdminDispatcher extends HttpServlet{

	private static final long serialVersionUID = -5921562331762724105L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String)session.getAttribute("login");
        String currentLang = req.getParameter("language");

        if(login!=null && currentLang!=null) {
            UserService service = new UserService();
            service.changeUserLanguage(login,currentLang);
            session.setAttribute("language",currentLang);
        }
		resp.sendRedirect(getServletContext().getContextPath() + "/admin/statistics");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req,resp);
    }
}
