package com.epam.javalab13.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.service.PaginationService;

/**
 * Created by Vikno on 9/7/2016.
 */
public class MatchesDispatcher extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PaginationService paginationService=new PaginationService();
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String itemsOnPage=request.getParameter("itemsOnPage");
		int pages = 0;
		List<Game>games = new ArrayList<>();
		type=type==null?"ACTIVE":type;
		page=page==null?"1":page;
		itemsOnPage=itemsOnPage==null?"10":itemsOnPage;
		pages=paginationService.getPagesForGames(type,page,itemsOnPage,games);
		request.setAttribute("pages", pages);
		request.setAttribute("games", games);
		request.setAttribute("type", type);
		request.setAttribute("page", page);
		request.setAttribute("itemsOnPage", itemsOnPage);
    	request.getRequestDispatcher("/WEB-INF/view/admin/matches.jsp").forward(request,response);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
