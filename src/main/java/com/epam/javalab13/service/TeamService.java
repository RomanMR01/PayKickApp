package com.epam.javalab13.service;

import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.game.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga on 02.09.2016.
 */
public class TeamService {
    TeamDAO teamDAO=new TeamDAO();

    public void addTeam(Team team) throws SQLException {
        teamDAO.addTeam(team);
    }

    public Team getTeamById(Team team) throws SQLException {
        return teamDAO.getTeam(team,"id");
    }

    public Team getTeamByName(Team team) throws SQLException {
        return teamDAO.getTeam(team,"name");
    }

    public List<Team> getAllTeams() throws SQLException {
        return teamDAO.getAllTeams();
    }
}
