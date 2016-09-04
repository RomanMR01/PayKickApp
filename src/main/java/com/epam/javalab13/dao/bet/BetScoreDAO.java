package com.epam.javalab13.dao.bet;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.model.bet.BetScore;
import com.epam.javalab13.model.bet.BetTotalGoals;
import com.epam.javalab13.model.bet.SingleBet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 CRUD operations for bet_score table
 */
public class BetScoreDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    /**
     * Add new BetScore into database
     * @param betScore the BetScore object
     * @throws SQLException
     */
    public void addBetScore(BetScore betScore) throws SQLException {
        final String SQL = "INSERT INTO bet_score(single_bet_id, first_team_score, second_team_score) VALUES(?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,betScore.getSingleBet().getId());
            st.setInt(2,betScore.getFirstTeamScore());
            st.setInt(3,betScore.getSecondTeamScore());

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
     * Getting BetScore by SingleBet
     * @param singleBet the SingleBet object
     * @throws SQLException
     */
    public BetScore getBetScore(SingleBet singleBet) throws SQLException {
        final String SQL = "SELECT * FROM bet_score bs WHERE bs.single_bet_id = ?";

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        BetScore betScore = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,singleBet.getId());

            rs = st.executeQuery();

            while (rs.next()){
                SingleBet sb = new SingleBetDAO().getSingleBetById(singleBet.getId());
                betScore = new BetScore(sb, rs.getInt("first_team_score"),rs.getInt("second_team_score"));
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

        return betScore;
    }
}
