package com.epam.javalab13.servlet.bookmaker;

import com.epam.javalab13.service.game.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookmakerServlet
 */
public class BookmakerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		String currentLang = request.getParameter("language");

		if(login!=null && currentLang!=null) {
			UserService service = new UserService();
			service.changeUserLanguage(login,currentLang);
			session.setAttribute("language",currentLang);
		}
		response.sendRedirect("bookmaker/matches");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
