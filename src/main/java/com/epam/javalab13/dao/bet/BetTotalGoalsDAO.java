package com.epam.javalab13.dao.bet;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.bet.BetTotalGoals;
import com.epam.javalab13.model.bet.SingleBet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 CRUD operations for bet_total_goals table
 */
public class BetTotalGoalsDAO {

    private static Logger logger = Logger.getLogger(BetTotalGoalsDAO.class);

    /**
     * Add new BetTotalGoals into database
     * @param betTotalGoals the TotalBetObject object
     * @throws SQLException
     */
    public void addBetTotalGoals(BetTotalGoals betTotalGoals) throws SQLException {
        final String SQL = "INSERT INTO bet_total_goals(single_bet_id, goals_count) VALUES (?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,betTotalGoals.getSingleBet().getId());
            st.setInt(2,betTotalGoals.getTotalGoal());

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
     * Getting BetTotalGoals by SingleBet
     * @param singleBet the SingleBet object
     * @throws SQLException
     */
    public BetTotalGoals getBetTotalGoals(SingleBet singleBet) throws SQLException {
        final String SQL = "SELECT * FROM bet_total_goals btg WHERE btg.single_bet_id = ?";

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        BetTotalGoals betTotalGoals = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,singleBet.getId());

            rs = st.executeQuery();

            while (rs.next()){
                SingleBet sb = new SingleBetDAO().getSingleBetById(singleBet.getId());
                betTotalGoals = new BetTotalGoals(sb, rs.getInt("goals_count"));
            }
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

        return betTotalGoals;
    }
}
