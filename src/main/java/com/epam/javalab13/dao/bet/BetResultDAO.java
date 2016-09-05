package com.epam.javalab13.dao.bet;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.bet.BetResult;
import com.epam.javalab13.model.bet.Result;
import com.epam.javalab13.model.bet.SingleBet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 CRUD operations for bet_result table
 */
public class BetResultDAO {
    private static Logger logger = Logger.getLogger(BetResultDAO.class);

    /**
     * Add new BetResult into database
     * @param betResult the betResult object
     * @throws SQLException
     */
    public void addBetResult(BetResult betResult) throws SQLException {
        final String SQL = "INSERT INTO bet_result(single_bet_id, result) VALUES (?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,betResult.getSingleBet().getId());
            st.setString(2,betResult.getResult().toString());

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
     * Getting BetResult by SingleBet
     * @param singleBet the SingleBet object
     * @throws SQLException
     */
    public BetResult getBetResult(SingleBet singleBet) throws SQLException {
        final String SQL = "SELECT * FROM bet_result br WHERE br.single_bet_id = ?";

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        BetResult betResult = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,singleBet.getId());

            rs = st.executeQuery();

            while (rs.next()){
                SingleBet sb = new SingleBetDAO().getSingleBetById(singleBet.getId());
                betResult = new BetResult(sb, Result.valueOf(rs.getString("result")));
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

        return betResult;
    }
}
