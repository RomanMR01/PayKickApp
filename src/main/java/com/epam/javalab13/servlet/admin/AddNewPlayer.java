package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.game.PlayerService;
import com.epam.javalab13.service.game.TeamService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vikno on 9/11/2016.
 */
public class AddNewPlayer extends HttpServlet{

    private static Logger logger = Logger.getLogger(AddNewPlayer.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamId = req.getParameter("teamID");
        String playerName = req.getParameter("playerName");
        String playerAge = req.getParameter("age");

        resp.setCharacterEncoding("UTF-8");
        if(teamId!=null && playerName!=null && playerAge!=null && teamId.length()>0 && playerName.length()>0  && playerAge.length()>0){

            PlayerService playerService = new PlayerService();
            List<Player> existingPlayers = playerService.getAllPlayers();

            //Search player on existing
            for(Player player:existingPlayers){
                if(playerName.equals(player.getFulName())){
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player already exists!\"}");
                    return;
                }
            }

            TeamService teamService = new TeamService();
            Team team = teamService.getTeamById(Integer.parseInt(teamId));
            List<Player> playersInTeam = playerService.getPlayersByTeam(team);


            //Search player in team
            for(Player player:playersInTeam){
                if(playerName.equals(player.getFulName())){
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player already is in this team!\"}");
                    return;
                }
            }

            //If team are not null (exists)
            //We add new player to db and add him to team
            if(team!=null){
                Player player = new Player();
                player.setFulName(playerName);
                player.setAge(Integer.parseInt(playerAge));
                playerService.addPlayer(player);

                playerService.updatePlayerTeam(playerName,Integer.parseInt(teamId));


                logger.info("Admin add new player: " + playerName + " to team "+ teamId);

                resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Player added!\"}");
                return;
            }else{
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player or game not exist!\"}");
                return;
            }

        }else{
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
            return;
        }

    }
}
