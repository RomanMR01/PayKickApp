package com.epam.javalab13.dao;

import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.PlayerTransformer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class PlayerDAO {
    private static Logger logger = Logger.getLogger(PlayerDAO.class);

    public void addPlayer(Player player) throws SQLException {
        logger.info("DAO addPlayer entry");
        final String SQL = "INSERT INTO player(full_name, age, total_games, team_id) VALUES(?,?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setString(1, player.getFulName());
            st.setInt(2, player.getAge());
            st.setInt(3, player.getTotalGames());
            st.setInt(4, player.getTeam().getId());

            st.executeUpdate();
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
    }

    public void updatePlayer(Player player, String type) throws SQLException {
        logger.info("DAO updatePlayer entry");
        final String SQL_NAME = "UPDATE player u SET u.full_name = ? WHERE u.id=?";
        final String SQL_TOTAL_GAMES = "UPDATE player u SET u.total_games = ? WHERE u.id=?";
        final String SQL_TEAM = "UPDATE player u SET u.team_id = ? WHERE u.id=?";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case "name":
                    st = conn.prepareStatement(SQL_NAME);
                    st.setString(1, player.getFulName());
                    st.setInt(2, player.getId());
                    break;
                case "games":
                    st = conn.prepareStatement(SQL_TOTAL_GAMES);

                    st.setInt(1, player.getTotalGames());
                    st.setInt(2, player.getId());

                    break;
                case "team":
                    st = conn.prepareStatement(SQL_TEAM);

                    st.setInt(1, player.getTeam().getId());
                    st.setInt(2, player.getId());

                    break;
            }

            st.executeUpdate();
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
    }

    public Player getPlayer(Player player, String type) throws SQLException {
        final String SQL_ID = "SELECT * FROM player u WHERE u.id = ?";
        final String SQL_NAME = "SELECT * FROM player u WHERE u.full_name LIKE ?";

        Player returnUser = null;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        PlayerTransformer playerTransformer=new PlayerTransformer();

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case "id":
                    st = conn.prepareStatement(SQL_ID);
                    st.setInt(1, player.getId());
                    rs = st.executeQuery();

                    returnUser = playerTransformer.getOne(rs);
                    break;
                case "name":
                    st = conn.prepareStatement(SQL_NAME);
                    st.setString(1, player.getFulName());
                    rs = st.executeQuery();

                    returnUser = playerTransformer.getOne(rs);
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

        return returnUser;
    }

    public List<Player> getPlayersByTeam(Team team)throws SQLException {
        final String SQL_TEAM = "SELECT * FROM player u WHERE u.team_id = ?";
        List<Player> players = null;
        PlayerTransformer playerTransformer = new PlayerTransformer();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL_TEAM);
            st.setInt(1, team.getId());
            rs = st.executeQuery();

            players = playerTransformer.getAll(rs);

        }finally {
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

        return players;
    }

    public List<Player> getAllPlayers() throws SQLException {
        final String SQL = "SELECT * FROM player";

        List<Player> players = null;
        PlayerTransformer playerTransformer = new PlayerTransformer();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL);

            players = playerTransformer.getAll(rs);

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

        return players;
    }
}
