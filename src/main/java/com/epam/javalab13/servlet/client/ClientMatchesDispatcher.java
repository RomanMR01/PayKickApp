package com.epam.javalab13.servlet.client;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.service.ClientBetsService;
import com.epam.javalab13.service.game.GameService;
import com.epam.javalab13.service.game.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        req.setAttribute("games", activeGames);
        req.setAttribute("allGamesIDs", clientBetsService.getAllGamesIDs());
        req.setAttribute("firstTeamsIDs", clientBetsService.getFirstTeamsIDs());
        req.setAttribute("secondTeamsIDs", clientBetsService.getSecondTeamsIDs());
        req.setAttribute("firstTeamsPlayers", clientBetsService.getFirstTeamsPlayersIDs());
        req.setAttribute("secondTeamsPlayers", clientBetsService.getSecondTeamsPlayersIDs());

        req.getRequestDispatcher("/WEB-INF/view/client/matches.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String userLogin = (String) session.getAttribute("login");
        if (userLogin == null) {
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Sorry, something going wrong, try again later!\"}");
            return;
        }

        int userID = new UserService().getUserByLogin(userLogin).getId();

        String jsonArray = req.getParameter("requestJsonArray");
        String betsCount = req.getParameter("betsCount");
        String amountSum = req.getParameter("amount");
        String awardSum = req.getParameter("award");

        if (jsonArray == null || betsCount == null || amountSum == null || awardSum == null) {
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Sorry, found empty fields!\"}");
            return;
        }

        try {
            int betsSize = Integer.parseInt(betsCount);
            int amount = Integer.parseInt(amountSum);
            double award = Double.parseDouble(awardSum);

            UserService service = new UserService();
            User user = service.getUserByLogin(userLogin);
            double balance = user.getBalance();


            //Check if user have enough money
            if (balance < amount) {
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"You haven't enough money!\"}");
                return;
            } else {
                balance -= amount;
                session.setAttribute("balance", balance);
                user.setBalance(balance);
                service.updateUserBalance(userID,balance);
            }


            ClientBetsService clientBetsService = new ClientBetsService();
            if (clientBetsService.makeBet(jsonArray, betsSize, amount, award, userID)) {
                resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Bet is done successfully!\"}");
                return;
            } else {
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Error, please try again later!\"}");
                return;
            }
        } catch (Exception e) {
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Can't parse input data!\"}");
            return;
        }

    }
}
