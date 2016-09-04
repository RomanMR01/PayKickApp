package com.epam.javalab13.service;

import com.epam.javalab13.dao.LeagueDAO;
import com.epam.javalab13.model.game.League;

import java.sql.SQLException;

/**
 * Created by Olga on 04.09.2016.
 */
public class LeagueService {
    LeagueDAO leagueDAO=new LeagueDAO();

    public void addLeague(League league) throws SQLException{
        leagueDAO.addLeague(league);
    }

    public League getLeagueById(League league) throws SQLException {
        return leagueDAO.getLeague(league,"id");
    }

    public League getLeagueByName(League league) throws SQLException {
        return leagueDAO.getLeague(league,"name");
    }
}
