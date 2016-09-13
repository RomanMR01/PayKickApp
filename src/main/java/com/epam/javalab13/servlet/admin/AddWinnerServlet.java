package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Goal;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
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

        resp.setCharacterEncoding("UTF-8");

        if((gameId==null) || (firstTeamId==null) || (secondTeamId==null) || (firstTeamScore==null) || (secondTeamScore==null)){
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Some data is empty!\"}");
            return;
        }else{
            int gameID = Integer.parseInt(gameId);
            int firstTeamID = Integer.parseInt(firstTeamId);
            int secondTeamID = Integer.parseInt(secondTeamId);

            int scoreOne = Integer.parseInt(firstTeamScore);
            int scoreTwo = Integer.parseInt(secondTeamScore);


            PlayerService playerService = new PlayerService();

            Team one = new Team();
            one.setId(firstTeamID);
            List<Player> allFirstTeamPlayers = playerService.getPlayersByTeam(one);//All players at first team

            Team two = new Team();
            two.setId(secondTeamID);
            List<Player> allSecondTeamPlayers = playerService.getPlayersByTeam(two);//All players at second team

            List<Player> firstTeamGoals = new ArrayList<>();//Players that score goals for first team
            List<Player> secondTeamGoals = new ArrayList<>();//Players that score goals for second team

            if(0!=scoreOne){
                String [] oneTeam = firstTeamPlayers.split(";");//All players that score goals at first team

                //Search if such player exist in system(db) and then (if exists) search this player in first team
                for(String playerName:oneTeam){
                    Player player = playerService.getPlayerByName(playerName);
                    if(player!=null) {
                        boolean isThisPlayerAtTeam = false;
                        for(Player p:allFirstTeamPlayers){
                            if(p.getId()==player.getId()){
                                isThisPlayerAtTeam = true;
                                break;
                            }
                        }
                        if(isThisPlayerAtTeam) {
                            firstTeamGoals.add(player);
                        }else{
                            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"" + playerName + " is not a member of first team!\"}");
                            return;
                        }
                    }else{
                        resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect player name or player not exists! Try again!\"}");
                        return;
                    }
                }
            }

            if(0!=scoreTwo){
                String [] twoTeam = secondTeamPlayers.split(";");//All players that score goals at second team
                //Search if such player exist in system(db) and then (if exists) search this player in second team
                for(String playerName:twoTeam){
                    Player player = playerService.getPlayerByName(playerName);
                    if(player!=null) {
                        boolean isThisPlayerAtTeam = false;
                        for(Player p:allSecondTeamPlayers){
                            if(p.getId()==player.getId()){
                                isThisPlayerAtTeam = true;
                                break;
                            }
                        }
                        if(isThisPlayerAtTeam) {
                            secondTeamGoals.add(player);
                        }else{
                            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"" + playerName + " is not a member of first team!!\"}");
                            return;
                        }
                    }else{
                        resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect player name or player not exists! Try again!\"}");
                        return;
                    }
                }
            }

            GoalService goalService = new GoalService();

            for(Player player:firstTeamGoals){
                goalService.addGoal(gameID,firstTeamID,player.getId());
            }

            for(Player player:secondTeamGoals){
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
