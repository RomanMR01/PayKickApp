package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ScoreCoefficient;
import com.epam.javalab13.model.game.TotalGoalsCoefficient;
import com.epam.javalab13.transformer.game.ScoreCoefficientTransformer;
import com.epam.javalab13.transformer.game.TotalGoalsCoefficientTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vikno on 9/5/2016.
 */
public class TotalGoalsCoefficientDAO {

    private static Logger logger = Logger.getLogger(TotalGoalsCoefficientDAO.class);

    /**
     * Getting TotalGoalsCoefficient list for one game
     *
     * @param game the Game object
     * @return TotalGoalsCoefficient for one game
     * @throws SQLException
     */
    public TotalGoalsCoefficient getTotalGoalsCoefficientByGame(Game game) throws SQLException {
        final String SQL = "SELECT * FROM total_goals_coefficient tgc WHERE tgc.game_id=?";

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;

        TotalGoalsCoefficient totalGoalsCoefficient = null;
        TotalGoalsCoefficientTransformer totalGoalsCoefficientTransformer = new TotalGoalsCoefficientTransformer();

        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,game.getId());
            ResultSet rs = st.executeQuery();


            totalGoalsCoefficient = totalGoalsCoefficientTransformer.getOne(rs);

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

        return totalGoalsCoefficient;
    }

    /**
     * Add new TotalGoalsCoefficient into database
     *
     * @param totalGoalsCoefficient the TotalGoalsCoefficient object
     * @throws SQLException
     */
    public void addTotalGoalsCoefficient(TotalGoalsCoefficient totalGoalsCoefficient) throws SQLException {
        final String SQL = "INSERT INTO total_goals_coefficient(game_id, goal_coefficient) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,totalGoalsCoefficient.getGame().getId());
            st.setDouble(2,totalGoalsCoefficient.getGoalCoefficient());

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
