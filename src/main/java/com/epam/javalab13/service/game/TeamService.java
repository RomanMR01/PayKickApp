package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.game.Team;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga on 02.09.2016.
 */
public class TeamService {
    TeamDAO teamDAO=new TeamDAO();

    private static Logger logger = Logger.getLogger(TeamService.class);

    public void addTeam(String teamName,String teamLocation,String emblemURL) {
        Team team = new Team();
        team.setName(teamName);
        team.setLocation(teamLocation);
        team.setEmblemUrl(emblemURL);
        try {
            teamDAO.addTeam(team);
        } catch (SQLException e) {
           logger.error("Can't create new team with name: " + teamName + ", location: " +
                   teamLocation + " and emblemURL:" + emblemURL + " !",e);
        }
    }

    public Team getTeamById(int teamId)  {
        Team team = new Team();
        team.setId(teamId);

        try {
            return teamDAO.getTeam(team,"id");
        } catch (SQLException e) {
            logger.error("Can't get get team by id:" + teamId);
        }

        return null;
    }

    public Team getTeamByName(String teamName){
        Team team = new Team();
        team.setName(teamName);
        try {
            return teamDAO.getTeam(team,"name");
        } catch (SQLException e) {
            logger.error("Can't get get team by name:" + team.getName());
        }

        return null;
    }

    public List<Team> getAllTeams(){
        try {
            return teamDAO.getAllTeams();
        } catch (SQLException e) {
            logger.error("Can't get all teams!",e);
        }

        return null;
    }
}
