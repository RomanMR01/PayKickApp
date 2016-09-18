package com.epam.javalab13.servlet.bookmaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.service.PaginationService;
import com.epam.javalab13.service.game.UserService;

/**
 * Servlet implementation class BookmakerMatchesDispatcherServlet
 */
public class BookmakerMatchesDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaginationService paginationService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String itemsOnPage=request.getParameter("itemsOnPage");
		int pages = 0;
		List<Game>games = new ArrayList<>();
		type=type==null?"ACTIVE":type;
		page=page==null?"1":page;
		itemsOnPage=itemsOnPage==null?"10":itemsOnPage;
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		User user = new UserService().getUserByLogin(login);
		paginationService=new PaginationService();
		pages=paginationService.getPagesForGamesByBookmaker(type,page,itemsOnPage,games,user);
		if(pages==-1){
			response.sendRedirect(getServletContext().getContextPath() + "/bookmaker/matches");
			return;
		}
		int intPage = Integer.valueOf(page);
		intPage=intPage>pages?pages:Integer.valueOf(page);
		request.setAttribute("pages", pages);
		request.setAttribute("games", games);
		request.setAttribute("type", type);
		request.setAttribute("page", intPage);
		request.setAttribute("itemsOnPage", itemsOnPage);
		request.getRequestDispatcher("/WEB-INF/view/bookmaker/matches.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
