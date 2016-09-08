package com.epam.javalab13.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.PaginationService;

/**
 * Servlet implementation class UsersPaginationServlet
 */
public class UsersPaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PaginationService paginationService = new PaginationService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersPaginationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String itemsOnPage=request.getParameter("itemsOnPage");
		int pages = 0;
		List<User>users = new ArrayList<>();
		pages=paginationService.getPages(type,page,itemsOnPage,users);
		
		request.setAttribute("pages", pages);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/view/admin/users.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
