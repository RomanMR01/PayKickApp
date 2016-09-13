package com.epam.javalab13.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.PaginationService;
import com.epam.javalab13.service.game.TeamService;

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

		//Pagination
		try {
			String type = request.getParameter("type");
			String page = request.getParameter("page");
			String itemsOnPage = request.getParameter("itemsOnPage");
			int pages = 0;
			List<Game> games = new ArrayList<>();
			type = type == null ? "ACTIVE" : type;
			page = page == null ? "1" : page;
			itemsOnPage = itemsOnPage == null ? "10" : itemsOnPage;
			pages = paginationService.getPagesForGames(type, page, itemsOnPage, games);
			int intPage = Integer.valueOf(page);
			intPage = intPage > pages ? pages : Integer.valueOf(page);
			request.setAttribute("pages", pages);
			request.setAttribute("games", games);
			request.setAttribute("type", type);
			request.setAttribute("page", intPage);
			request.setAttribute("itemsOnPage", itemsOnPage);
		}catch (Exception e){
			response.sendRedirect(getServletContext().getContextPath() + "/admin/matches");
			return;
		}

		//END pagination


		//TODO add services instead DAOs
		TeamService teamService = new TeamService();
		List<Team> teams =  teamService.getAllTeams();

		GameDAO gameDAO = new GameDAO();
		List<Game> games1 = null;
		try {
			games1 = gameDAO.getGamesByStatus(GameDAO.GetGamesType.ACTIVE);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		PlayerDAO playerDAO = new PlayerDAO();

		for(Game game:games1){
			Team team1 = game.getFirstTeam();
			Team team2 = game.getSecondTeam();

			List<Player> first = null;
			List<Player> second = null;

			try {
				first = playerDAO.getPlayersByTeam(team1);
				second = playerDAO.getPlayersByTeam(team2);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			team1.setPlayers(first);
			team2.setPlayers(second);
		}

		List<User> bookmakers = new ArrayList<>();
		UserDAO userDAO = new UserDAO();

		try {
			bookmakers = userDAO.getAllUsersByType(UserDAO.GetType.BOOKMAKER);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("games2",games1);
		request.setAttribute("teams",teams);
		request.setAttribute("bookmakers",bookmakers);

		request.getRequestDispatcher("/WEB-INF/view/admin/matches.jsp").forward(request,response);
	}
}