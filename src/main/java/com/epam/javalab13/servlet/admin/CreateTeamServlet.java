package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.model.game.Team;
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
public class CreateTeamServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamName = req.getParameter("teamName");
        String teamLocation = req.getParameter("teamLocation");

        if(teamName!=null && teamLocation!=null && teamName.length()>0 && teamLocation.length()>0){
            TeamService teamService = new TeamService();
            List<Team> teamList = teamService.getAllTeams();
            for(Team team:teamList){
                if(teamName.equals(team.getName())){
                    System.out.println("team exist");

                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Such team exist!\"}");
                    return;
                }
            }
            teamService.addTeam(teamName,teamLocation,null);
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Team created!\"}");

        }else{
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
        }
    }

}
