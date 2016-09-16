package com.epam.javalab13.servlet.client;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.service.ClientBetsService;
import com.epam.javalab13.service.game.GameService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/14/2016.
 */
public class ClientMatchesDispatcher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientBetsService clientBetsService = new ClientBetsService();
        List<Game> activeGames = clientBetsService.getActiveGames();

        req.setAttribute("games",activeGames);
        req.setAttribute("allGamesIDs",clientBetsService.getAllGamesIDs());
        req.setAttribute("firstTeamsIDs",clientBetsService.getFirstTeamsIDs());
        req.setAttribute("secondTeamsIDs",clientBetsService.getSecondTeamsIDs());
        req.setAttribute("firstTeamsPlayers",clientBetsService.getFirstTeamsPlayersIDs());
        req.setAttribute("secondTeamsPlayers",clientBetsService.getSecondTeamsPlayersIDs());

        req.getRequestDispatcher("/WEB-INF/view/client/matches.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
