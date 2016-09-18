package com.epam.javalab13.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.PaginationService;
import com.epam.javalab13.service.game.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/7/2016.
 */
public class UsersDispatcher extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PaginationService paginationService = new PaginationService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		String currentLang = request.getParameter("language");

		if(login!=null && currentLang!=null) {
			UserService service = new UserService();
			service.changeUserLanguage(login,currentLang);
			session.setAttribute("language",currentLang);
		}

		String type = request.getParameter("type");
		String page = request.getParameter("page");

		String itemsOnPage=request.getParameter("itemsOnPage");
		int pages = 0;
		List<User>users = new ArrayList<>();
		type=type==null?"ALL":type;
		page=page==null?"1":page;
		itemsOnPage=itemsOnPage==null?"10":itemsOnPage;
		pages=paginationService.getPagesForUsers(type,page,itemsOnPage,users);

		int intPage = 0;
		try {
			intPage = Integer.valueOf(page);
			intPage = intPage > pages ? pages : Integer.valueOf(page);
		}catch (Exception e){
			response.sendRedirect(getServletContext().getContextPath() + "/admin/users");
			return;
		}

		if(pages==-1){
			response.sendRedirect(getServletContext().getContextPath() + "/admin/users");
			return;
		}else {
			request.setAttribute("pages", pages);
			request.setAttribute("users", users);
			request.setAttribute("type", type);
			request.setAttribute("page", intPage);
			request.setAttribute("itemsOnPage", itemsOnPage);

			request.getRequestDispatcher("/WEB-INF/view/admin/users.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}