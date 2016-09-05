package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.model.game.ScoreCoefficient;
import com.epam.javalab13.transformer.game.PlayerCoefficientTransformer;
import com.epam.javalab13.transformer.game.ScoreCoefficientTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikno on 9/5/2016.
 */
public class ScoreCoefficientDAO {

    private static Logger logger = Logger.getLogger(PlayerCoefficientDAO.class);

    /**
     * Getting ScoreCoefficient list for one game
     *
     * @param game the Game object
     * @return ScoreCoefficient for one game
     * @throws SQLException
     */
    public ScoreCoefficient getScoreCoefficientByGame(Game game) throws SQLException {

        final String SQL = "SELECT * FROM score_coefficient sc WHERE sc.game_id=?";

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;

        ScoreCoefficient scoreCoefficient = null;
        ScoreCoefficientTransformer scoreCoefficientTransformer = new ScoreCoefficientTransformer();

        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,game.getId());
            ResultSet rs = st.executeQuery();

            scoreCoefficient = scoreCoefficientTransformer.getOne(rs);

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

        return scoreCoefficient;
    }

    /**
     * Add new ScoreCoefficient into database
     *
     * @param scoreCoefficient the ScoreCoefficient object
     * @throws SQLException
     */
    public void addScoreCoefficient(ScoreCoefficient scoreCoefficient) throws SQLException {
        final String SQL = "INSERT INTO score_coefficient(game_id, start_coefficient, first_team_coefficient, second_team_coefficient)VALUES(?,?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,scoreCoefficient.getGame().getId());
            st.setDouble(2,scoreCoefficient.getStartCoefficient());
            st.setDouble(3,scoreCoefficient.getFirstTeamCoefficient());
            st.setDouble(4,scoreCoefficient.getSecondTeamCoefficient());

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
