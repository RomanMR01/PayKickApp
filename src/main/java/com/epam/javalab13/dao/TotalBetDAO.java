package com.epam.javalab13.dao;

import com.epam.javalab13.model.bet.TotalBet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 CRUD operations for total_bet table
 */
public class TotalBetDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    /**
     * Add new TotalBet into database
     * @param totalBet the TotalBet object
     * @throws SQLException
     */
    public void addTotalBet(TotalBet totalBet) throws SQLException {
        final String SQL = "INSERT INTO total_bet(user_id, type, amount, date, award) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,totalBet.getUser().getId());
            st.setString(2,totalBet.getType().toString());
            st.setInt(3,totalBet.getAmount());

            Timestamp timestamp = new Timestamp(totalBet.getDate().getTime());
            st.setTimestamp(4,timestamp);

            st.setDouble(5,totalBet.getAward());

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
     * Update TotalBet status
     * @param totalBet the TotalBet object
     * @throws SQLException
     */
    public void updateTotalBetStatus(TotalBet totalBet) throws SQLException {
        final String SQL = "UPDATE total_bet tb SET tb.status=? WHERE tb.id =?";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setString(1,totalBet.getStatus().toString());
            st.setInt(2,totalBet.getId());

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
