package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.game.TeamTransformer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 02.09.2016.
 */
public class TeamDAO {
    private static Logger logger = Logger.getLogger(TeamDAO.class);

    public void addTeam(Team team) throws SQLException {
        logger.info("DAO addTeam entry");
        final String SQL = "INSERT INTO team(name, location, emblem_url, total_wins, total_loses, total_draws) VALUES(?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getLocation());
            preparedStatement.setString(3, team.getEmblemUrl());
            preparedStatement.setInt(4, team.getTotalWins());
            preparedStatement.setInt(5, team.getTotalLoses());
            preparedStatement.setInt(6, team.getTotalDraws());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (connection != null) try {
                connection.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }
        }
    }

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        final String SQL = "SELECT * FROM team";

        TeamTransformer transformer = new TeamTransformer();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL);

            teams = transformer.getAll(rs);

        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }

        }

        return teams;
    }
    
    public List<Team> getAllTeamsWithPlayers() throws SQLException {
        List<Team> teams = new ArrayList<>();
        final String SQL = "SELECT * FROM team";

        TeamTransformer transformer = new TeamTransformer();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL);

            teams = transformer.getAllTeamsWithPlayers(rs);

        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }

        }

        return teams;
    }

    public Team getTeam(Team team, String type) throws SQLException {
        final String SQL_ID = "SELECT * FROM team t WHERE t.id = ?";
        final String SQL_NAME = "SELECT * FROM team t WHERE t.name LIKE ?";

        Team returnTeam = null;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        TeamTransformer transformer=new TeamTransformer();

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case "id":
                    st = conn.prepareStatement(SQL_ID);
                    st.setInt(1, team.getId());
                    rs = st.executeQuery();

                    returnTeam = transformer.getOne(rs);
                    break;
                case "name":
                    st = conn.prepareStatement(SQL_NAME);
                    st.setString(1, team.getName());
                    rs = st.executeQuery();

                    returnTeam = transformer.getOne(rs);
                    break;
            }
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }
        }

        return returnTeam;
    }
}
