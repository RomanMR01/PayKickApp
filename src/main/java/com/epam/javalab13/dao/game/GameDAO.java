package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.transformer.game.GameTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for game table
 */
public class GameDAO {
    private static Logger logger = Logger.getLogger(GameDAO.class);

    public enum GetGamesType {
        ALL,
        ACTIVE,
        CANCELED,
        NEW,
        FINISHED
    }

    public enum UpdateGameType {
        GAME_ACTIVE,
        GAME_CANCELED,
        GAME_FINISHED,
        GAME_PROFIT
    }

    /**
     * Add new Game into database
     * <p>
     * Updated by Petro: set generated primary key id to @param game.
     *
     * @param game the Game object
     * @throws SQLException
     */
    public void addGame(Game game) throws SQLException {
        final String SQL = "INSERT INTO game(title, location, date, first_team_id, second_team_id) VALUES(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        ResultSet rs;
        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, game.getTitle());
            st.setString(2, game.getLocation());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
            String date = simpleDateFormat.format(game.getDate());
            st.setString(3, date);

            st.setInt(4, game.getFirstTeam().getId());
            st.setInt(5, game.getSecondTeam().getId());
            st.executeUpdate();

            rs = st.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                game.setId(id);
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
    }

    /**
     * Getting games or by them status type
     *
     * @param type the type of Status for selecting games
     * @return list of all total bets
     * @throws SQLException
     */
    public List<Game> getGamesByStatus(GetGamesType type) throws SQLException {
        final String SQL_ALL = "SELECT * FROM game";
        final String SQL_ACTIVE = "SELECT * FROM game g WHERE g.status LIKE 'ACTIVE'";
        final String SQL_NEW = "SELECT * FROM game g WHERE g.status LIKE 'NEW'";
        final String SQL_CANCELED = "SELECT * FROM game g WHERE g.status LIKE 'CANCELED'";
        final String SQL_FINISHED = "SELECT * FROM game g WHERE g.status LIKE 'FINISHED'";

        List<Game> games = null;

        GameTransformer gameTransformer = new GameTransformer();

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            switch (type) {
                case ALL:
                    st = conn.prepareStatement(SQL_ALL);
                    break;
                case ACTIVE:
                    st = conn.prepareStatement(SQL_ACTIVE);
                    break;
                case NEW:
                    st = conn.prepareStatement(SQL_NEW);
                    break;
                case CANCELED:
                    st = conn.prepareStatement(SQL_CANCELED);
                    break;
                case FINISHED:
                    st = conn.prepareStatement(SQL_FINISHED);
                    break;
            }

            rs = st.executeQuery();
            games = gameTransformer.getAll(rs);

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

        return games;
    }

    /**
     * Getting game by id
     *
     * @param gameId the id of game
     * @return game the Game object
     * @throws SQLException
     */
    public Game getGamesById(int gameId) throws SQLException {
        final String SQL = "SELECT * FROM game g WHERE g.id=?";


        Game game = null;
        GameTransformer gameTransformer = new GameTransformer();

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;


        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1, gameId);

            ResultSet rs = st.executeQuery();

            game = gameTransformer.getOne(rs);

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

        return game;
    }

    /**
     * Getting game by bookmaker
     *
     * @param bookmaker the bookmaker
     * @return game the Game object
     * @throws SQLException
     */
    public List<Game> getGamesByBookmaker(User bookmaker) throws SQLException {
        final String SQL = "SELECT * FROM game g WHERE g.bookmaker_id=?";


        Game game = null;
        GameTransformer gameTransformer = new GameTransformer();

        List<Game> games = null;

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;


        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1, bookmaker.getId());

            ResultSet rs = st.executeQuery();

            games = gameTransformer.getAll(rs);

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

        return games;
    }

    /**
     * Update game table by type
     *
     * @param game the Game object
     * @param type the type of game update: ACTIVE, FINISHED, CANCELED
     * @throws SQLException
     */
    public void updateGameByType(Game game, UpdateGameType type) throws SQLException {
        final String SQL_ACTIVE = "UPDATE game g SET g.status='ACTIVE' WHERE g.id=?";
        final String SQL_CANCELED = "UPDATE game g SET g.status='CANCELED' WHERE g.id=?";
        final String SQL_FINISHED = "UPDATE game g SET g.status='FINISHED', g.first_goals=?,g.second_goals=? WHERE g.id=?";
        final String SQL_PROFIT = "UPDATE game g SET g.profit=? WHERE g.id=?";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            switch (type) {
                case GAME_ACTIVE:
                    st = conn.prepareStatement(SQL_ACTIVE);
                    st.setInt(1, game.getId());
                    break;
                case GAME_CANCELED:
                    st = conn.prepareStatement(SQL_CANCELED);
                    st.setInt(1, game.getId());
                    break;
                case GAME_FINISHED:
                    st = conn.prepareStatement(SQL_FINISHED);
                    st.setInt(1, game.getFirstGoals());
                    st.setInt(2, game.getSecondGoals());
                    st.setInt(3, game.getId());
                    break;
                case GAME_PROFIT:
                    st = conn.prepareStatement(SQL_PROFIT);
                    st.setDouble(1, game.getProfit());
                    st.setInt(2, game.getId());
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

    public enum Type {
        ALL,
        ACTIVE,
        PAST,
        CANCELED,
        NEW;
    }

    public List<Game> getGamesByType(Type type) throws SQLException {
        final String SQL_ALL = "SELECT * FROM game";
        final String SQL_ACTIVE = "SELECT * FROM game g WHERE g.status LIKE 'ACTIVE'";
        final String SQL_NEW = "SELECT * FROM game g WHERE g.status LIKE 'NEW'";
        final String SQL_PAST = "SELECT * FROM game g WHERE g.status LIKE 'FINISHED'";
        final String SQL_CANCELED = "SELECT * FROM game g WHERE g.status LIKE 'CANCELED'";

        List<Game> games = null;

        GameTransformer gameTransformer = new GameTransformer();

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            switch (type) {
                case ALL:
                    st = conn.prepareStatement(SQL_ALL);
                    break;
                case ACTIVE:
                    st = conn.prepareStatement(SQL_ACTIVE);
                    break;
                case NEW:
                    st = conn.prepareStatement(SQL_NEW);
                    break;
                case PAST:
                    st = conn.prepareStatement(SQL_PAST);
                    break;
                case CANCELED:
                    st = conn.prepareStatement(SQL_CANCELED);
                    break;

            }

            rs = st.executeQuery();
            games = gameTransformer.getAll(rs);

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

        return games;
    }
}
