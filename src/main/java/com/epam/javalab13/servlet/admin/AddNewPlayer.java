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
public class AddNewPlayer extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamId = req.getParameter("teamID");
        String playerName = req.getParameter("playerName");
        String playerAge = req.getParameter("age");

        System.out.println("t: " + teamId);
        System.out.println("n: " + playerName);
        System.out.println("a: " + playerAge);

        if(teamId!=null && playerName!=null && playerAge!=null && teamId.length()>0 && playerName.length()>0  && playerAge.length()>0){

            System.out.println("one");
            PlayerService playerService = new PlayerService();
            List<Player> existingPlayers = playerService.getAllPlayers();

            for(Player player:existingPlayers){
                if(playerName.equals(player.getFulName())){
                    System.out.println("player exist");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player already exists!\"}");
                    return;
                }
            }

            System.out.println("two");

            TeamService teamService = new TeamService();
            Team team = teamService.getTeamById(Integer.parseInt(teamId));
            List<Player> playersInTeam = playerService.getPlayersByTeam(team);

            System.out.println("after team" + team);
            System.out.println("players in team" + playersInTeam);

            for(Player player:playersInTeam){
                if(playerName.equals(player.getFulName())){
                    System.out.println("player in team");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player already is in this team!\"}");
                    return;
                }
            }

            System.out.println("before team!=null" + playerName);

            if(team!=null){
                Player player = new Player();
                player.setFulName(playerName);
                player.setAge(Integer.parseInt(playerAge));
                playerService.addPlayer(player);

                playerService.updatePlayerTeam(playerName,Integer.parseInt(teamId));

                System.out.println("player added");

                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Player added!\"}");
                return;
            }else{
                System.out.println("empty team");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such player or game not exist!\"}");
                return;
            }

        }else{
            System.out.println("wrong data");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
            return;
        }

    }
}
