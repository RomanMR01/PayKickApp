package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.game.PlayerService;
import com.epam.javalab13.service.game.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vikno on 9/11/2016.
 */
public class AddExistingPlayerToTeamServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamID = req.getParameter("teamID");
        String playerName = req.getParameter("playerName");

        System.out.println(teamID);
        System.out.println(playerName);

        if(teamID!=null && playerName!=null && teamID.length()>0 && playerName.length()>0){
            PlayerService playerService = new PlayerService();
            List<Player> existingPlayers = playerService.getAllPlayers();

            boolean isSuchPlayer = false;
            for(Player player:existingPlayers){
                if(playerName.equals(player.getFulName())){
                    isSuchPlayer = true;
                    break;
                }
            }

            TeamService teamService = new TeamService();
            Team team = teamService.getTeamById(Integer.parseInt(teamID));
            List<Player> playersInTeam = playerService.getPlayersByTeam(team);

            for(Player player:playersInTeam){
                if(playerName.equals(player.getFulName())){
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player already is in this team!\"}");
                    return;
                }
            }

            if(isSuchPlayer && team!=null){
                playerService.updatePlayerTeam(playerName,Integer.parseInt(teamID));

                System.out.println("player updated");

                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Player added!\"}");
                return;
            }else{
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player or game not exist!\"}");
                return;
            }

        }else{
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
            return;
        }

    }
}
