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
import com.epam.javalab13.service.game.GameService;
import com.epam.javalab13.service.game.PlayerService;
import com.epam.javalab13.service.game.TeamService;
import com.epam.javalab13.service.game.UserService;
import org.apache.log4j.Logger;

/**
 * Created by Vikno on 9/7/2016.
 */
public class MatchesDispatcher extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PaginationService paginationService = new PaginationService();

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
        } catch (Exception e) {
            response.sendRedirect(getServletContext().getContextPath() + "/admin/matches");
            return;
        }

        //END pagination

        List<Team> teams = new TeamService().getAllTeams();
        List<Game> activeGames = new GameService().getActiveGames();
        PlayerService playerService = new PlayerService();

        for (Game game : activeGames) {
            Team team1 = game.getFirstTeam();
            Team team2 = game.getSecondTeam();

            List<Player> first = playerService.getPlayersByTeam(team1);
            List<Player> second = playerService.getPlayersByTeam(team2);

            team1.setPlayers(first);
            team2.setPlayers(second);
        }


        UserService userService = new UserService();
        List<User> bookmakers = userService.getAllUsersByType(UserDAO.GetType.BOOKMAKER);

        request.setAttribute("games2", activeGames);
        request.setAttribute("teams", teams);
        request.setAttribute("bookmakers", bookmakers);

        request.getRequestDispatcher("/WEB-INF/view/admin/matches.jsp").forward(request, response);
    }
}