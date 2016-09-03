package com.epam.javalab13.dao;

import com.epam.javalab13.model.bet.SingleBet;
import com.epam.javalab13.model.bet.TotalBet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 CRUD operations for single_bet table
 */
public class SingleBetDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    /**
     * Add new SingleBet into database
     * @param singleBet the SingleBet object
     * @throws SQLException
     */
    public void addSingleBet(SingleBet singleBet) throws SQLException {
        final String SQL = "INSERT INTO single_bet(total_bet_id, game_id, category, coefficient) VALUES(?,?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,singleBet.getTotalBet().getId());
            st.setInt(2,singleBet.getGame().getId());
            st.setString(3,singleBet.getCategory().toString());
            st.setDouble(4,singleBet.getCoefficient());

            st.executeUpdate();
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:",e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:",e);
            }
        }
    }

    /**
     * Update SingleBet status
     * @param singleBet the SingleBet object
     * @throws SQLException
     */
    public void updateSingleBetStatus(SingleBet singleBet) throws SQLException {
        final String SQL = "UPDATE single_bet sb SET sb.status=? WHERE sb.id =?";

        Connection conn = null;
        PreparedStatement st = null;
        conn = ConnectionPool.getConnection();

        try {

            st = conn.prepareStatement(SQL);

            st.setString(1,singleBet.getStatus().toString());
            st.setInt(2,singleBet.getId());

            st.executeUpdate();
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:",e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:",e);
            }
        }
    }
}
