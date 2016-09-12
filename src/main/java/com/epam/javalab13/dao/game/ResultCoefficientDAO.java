package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.transformer.game.GameTransformer;
import com.epam.javalab13.transformer.game.ResultCoefficientTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * CRUD operations for result_coefficient table
 */
public class ResultCoefficientDAO {

    private static Logger logger = Logger.getLogger(ResultCoefficientDAO.class);

    /**
     * Getting ResultCoefficient list for one game
     *
     * @param game the Game object
     * @return list of all ResultCoefficient
     * @throws SQLException
     */
    public List<ResultCoefficient> getResultCoefficientsByGame(Game game) throws SQLException {
        final String SQL = "SELECT * FROM result_coefficient rc WHERE rc.game_id=?";

        List<ResultCoefficient> resultCoefficients = null;

        ResultCoefficientTransformer resultCoefficientTransformer = new ResultCoefficientTransformer();



        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;


        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,game.getId());
            ResultSet rs = st.executeQuery();

            resultCoefficients = resultCoefficientTransformer.getAll(rs);


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

        return resultCoefficients;
    }

    /**
     * Add new ResultCoefficient into database
     *
     * @param resultCoefficient the ResultCoefficient object
     * @throws SQLException
     */
    public void addResultCoefficient(ResultCoefficient resultCoefficient) throws SQLException {
        final String SQL = "INSERT INTO result_coefficient(game_id, result, coefficient)VALUES(?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,resultCoefficient.getGame().getId());
            st.setString(2,resultCoefficient.getResult().toString());
            st.setDouble(3,resultCoefficient.getCoefficient());

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

    public void updateByGame(ResultCoefficient resultCoefficient) throws SQLException {
        final String SQL = "UPDATE result_coefficient rc SET rc.coefficient=? WHERE rc.game_id=? AND rc.result LIKE ?";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setDouble(1,resultCoefficient.getCoefficient());
            st.setInt(2,resultCoefficient.getGame().getId());
            st.setString(3,resultCoefficient.getResult().name());

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
