package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.transformer.game.PlayerCoefficientTransformer;
import com.epam.javalab13.transformer.game.ResultCoefficientTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikno on 9/5/2016.
 */
public class PlayerCoefficientDAO {

    private static Logger logger = Logger.getLogger(PlayerCoefficientDAO.class);

    /**
     * Getting PlayerCoefficient list for one game
     *
     * @param game the Game object
     * @return list of all PlayerCoefficient
     * @throws SQLException
     */
    public List<PlayerCoefficient> getPlayerCoefficientByGame(Game game) throws SQLException {
        final String SQL = "SELECT * FROM player_coefficient pc WHERE pc.game_id=?";


        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;


        PlayerCoefficientTransformer playerCoefficientTransformer = new PlayerCoefficientTransformer();
        List<PlayerCoefficient> playerCoefficients = null;


        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,game.getId());

            ResultSet rs = st.executeQuery();

            playerCoefficients = playerCoefficientTransformer.getAll(rs);

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

        return playerCoefficients;
    }

    /**
     * Add new PlayerCoefficient into database
     *
     * @param playerCoefficient the PlayerCoefficient object
     * @throws SQLException
     */
    public void addPlayerCoefficient(PlayerCoefficient playerCoefficient) throws SQLException {
        final String SQL = "INSERT INTO player_coefficient(game_id, player_id, coefficient)VALUES(?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,playerCoefficient.getGame().getId());
            st.setInt(2,playerCoefficient.getPlayer().getId());
            st.setDouble(3,playerCoefficient.getCoefficient());

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

    public void updateByGameAndPlayer(PlayerCoefficient playerCoefficient) throws SQLException {
        final String SQL = "UPDATE player_coefficient pc SET pc.coefficient=? WHERE pc.game_id=? AND pc.player_id=?";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setDouble(1,playerCoefficient.getCoefficient());
            st.setInt(2,playerCoefficient.getGame().getId());
            st.setInt(3,playerCoefficient.getPlayer().getId());

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
}
