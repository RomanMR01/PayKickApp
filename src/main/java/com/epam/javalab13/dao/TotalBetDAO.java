package com.epam.javalab13.dao;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.statistics.Award;
import com.epam.javalab13.transformer.TotalBetTransformer;
import com.epam.javalab13.transformer.UserTransformer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for total_bet table
 */
public class TotalBetDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    public enum GetTotalBetsType {
        ALL,
        ACTIVE,
        WON,
        LOST,
        CANCELED
    }

    /**
     * Add new TotalBet into database
     *
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

            st.setInt(1, totalBet.getUser().getId());
            st.setString(2, totalBet.getType().toString());
            st.setInt(3, totalBet.getAmount());
            st.setTimestamp(4, totalBet.getDate());
            st.setDouble(5, totalBet.getAward());

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

    /**
     * Update TotalBet status
     *
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

            st.setString(1, totalBet.getStatus().toString());
            st.setInt(2, totalBet.getId());

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

    /**
     * Getting all total bets or by them status type
     *
     * @param type the type of for selecting total bets
     * @return list of all total bets
     * @throws SQLException
     */
    public List<TotalBet> getAllTotalBets(GetTotalBetsType type) throws SQLException {
        final String SQL_ALL = "SELECT * FROM total_bet";
        final String SQL_ACTIVE = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'ACTIVE'";
        final String SQL_WON = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'WON'";
        final String SQL_LOST = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'LOST'";
        final String SQL_CANCELED = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'CANCELED'";

        List<TotalBet> totalBets = null;
        TotalBetTransformer totalBetTransformer = new TotalBetTransformer();

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
                case WON:
                    st = conn.prepareStatement(SQL_WON);
                    break;
                case LOST:
                    st = conn.prepareStatement(SQL_LOST);
                    break;
                case CANCELED:
                    st = conn.prepareStatement(SQL_CANCELED);
                    break;
            }

            rs = st.executeQuery();
            totalBets = totalBetTransformer.getAll(rs);

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

        return totalBets;
    }

    /**
     * Getting all total bets for one user
     *
     * @param type the type of for selecting total bets
     * @return list of all total bets
     * @throws SQLException
     */
    public List<TotalBet> getTotalBetsForUser(GetTotalBetsType type, User user) throws SQLException {
        final String SQL_ALL = "SELECT * FROM total_bet WHERE user_id = ?";
        final String SQL_ACTIVE = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'ACTIVE' AND user_id = ?";
        final String SQL_WON = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'WON' AND user_id = ?";
        final String SQL_LOST = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'LOST' AND user_id = ?";
        final String SQL_CANCELED = "SELECT * FROM total_bet tb WHERE tb.status LIKE 'CANCELED' AND user_id = ?";

        List<TotalBet> totalBets = null;
        TotalBetTransformer totalBetTransformer = new TotalBetTransformer();

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            switch (type) {
                case ALL:
                    st = conn.prepareStatement(SQL_ALL);
                    st.setInt(1, user.getId());
                    break;
                case ACTIVE:
                    st = conn.prepareStatement(SQL_ACTIVE);
                    st.setInt(1, user.getId());
                    break;
                case WON:
                    st = conn.prepareStatement(SQL_WON);
                    st.setInt(1, user.getId());
                    break;
                case LOST:
                    st = conn.prepareStatement(SQL_LOST);
                    st.setInt(1, user.getId());
                    break;
                case CANCELED:
                    st = conn.prepareStatement(SQL_CANCELED);
                    st.setInt(1, user.getId());
                    break;
            }

            rs = st.executeQuery();
            totalBets = totalBetTransformer.getAll(rs);

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

        return totalBets;
    }

    /**
     * Getting top users bets by award sum for statistics
     * @return list of top users total bets
     * @throws SQLException
     */
    public List<Award> getTopUsersBets() throws SQLException {
        final String SQL = "SELECT tb.user_id,SUM(tb.award),COUNT(tb.user_id)" +
                           "FROM totalizator.total_bet tb WHERE tb.status LIKE" +
                           " 'WON' GROUP BY tb.user_id ORDER BY SUM(tb.award) DESC";

        List<Award> awards = new ArrayList<>();

        Connection conn = ConnectionPool.getConnection();
        Statement st = null;
        ResultSet rs = null;
        Award award = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL);

            while (rs.next()){
                int userId = rs.getInt("user_id");
                User u = new User();
                u.setId(userId);
                User user = new UserDAO().getUser(u, UserDAO.GetOneUserType.ID);

                award = new Award(user,rs.getDouble(2),rs.getInt(3));
                awards.add(award);
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

        return awards;
    }

}
