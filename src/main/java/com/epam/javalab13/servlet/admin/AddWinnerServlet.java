package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Goal;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.service.game.GameService;
import com.epam.javalab13.service.game.GoalService;
import com.epam.javalab13.service.game.PlayerService;
import org.apache.log4j.Logger;

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
 * Created by Vikno on 9/10/2016.
 */
public class AddWinnerServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(AddWinnerServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String gameId = req.getParameter("gameID");
        String firstTeamId = req.getParameter("firstTeamID");
        String secondTeamId = req.getParameter("secondTeamID");
        String firstTeamScore = req.getParameter("firstTeamScore");
        String secondTeamScore = req.getParameter("secondTeamScore");

        String firstTeamPlayers=req.getParameter("firstTeamPlayers");
        String secondTeamPlayers=req.getParameter("secondTeamPlayers");

        logger.info("Admin " + session.getAttribute("login") + " add game results for game:" + gameId);

        if((gameId==null) || (firstTeamId==null) || (secondTeamId==null) || (firstTeamScore==null) || (secondTeamScore==null)){

            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Some data is empty!\"}");
            return;
        }else{
            int gameID = Integer.parseInt(gameId);
            int firstTeamID = Integer.parseInt(firstTeamId);
            int secondTeamID = Integer.parseInt(secondTeamId);

            int scoreOne = Integer.parseInt(firstTeamScore);
            int scoreTwo = Integer.parseInt(secondTeamScore);


            List<Player> firstTeam = new ArrayList<>();
            List<Player> secondTeam = new ArrayList<>();

            PlayerService playerService = new PlayerService();


            if(0!=scoreOne){
                String [] oneTeam = firstTeamPlayers.split(";");

                for(String playerName:oneTeam){
                    Player player = playerService.getPlayerByName(playerName);
                    if(player!=null) {
                        firstTeam.add(player);
                    }else{
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect player name! Try again!\"}");
                        return;
                    }
                }
            }

            if(0!=scoreTwo){
                String [] twoTeam = secondTeamPlayers.split(";");

                for(String playerName:twoTeam){
                    Player player = playerService.getPlayerByName(playerName);
                    if(player!=null) {
                        secondTeam.add(player);
                    }else{
                        resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect player name! Try again!\"}");
                        return;
                    }
                }
            }

            GoalService goalService = new GoalService();

            for(Player player:firstTeam){
                goalService.addGoal(gameID,firstTeamID,player.getId());
            }

            for(Player player:secondTeam){
                goalService.addGoal(gameID,secondTeamID,player.getId());
            }

            GameService gameService = new GameService();
            gameService.setGameScore(gameID,scoreOne,scoreTwo);


            logger.info("Game " + gameId  + " finished with score " + scoreOne + ":" + scoreTwo);

            Game game = gameService.getGameById(gameID);

            resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Game finished! Your profit is: " + game.getProfit() + "\"}");
            return;
        }

    }
}
