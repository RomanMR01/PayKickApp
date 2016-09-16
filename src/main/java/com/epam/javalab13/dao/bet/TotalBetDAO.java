package com.epam.javalab13.dao.bet;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.statistics.TopUser;
import com.epam.javalab13.transformer.bet.TotalBetTransformer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for total_bet table
 */
public class TotalBetDAO {

    private static Logger logger = Logger.getLogger(TotalBetDAO.class);

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

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
            String date = simpleDateFormat.format(totalBet.getDate());
            st.setString(4, date);

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
     * Getting TotalBet
     * @param id the total bet id
     * @return the TotalBet object
     * @throws SQLException
     */
    public TotalBet getTotalBetById(int id) throws SQLException {
        final String SQL = "SELECT * FROM total_bet tb WHERE tb.id=?";

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        TotalBet totalBet = null;
        TotalBetTransformer totalBetTransformer = new TotalBetTransformer();


        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,id);
            rs = st.executeQuery();

            totalBet = totalBetTransformer.getOne(rs);
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

        return totalBet;
    }

    public List<TotalBet> getTotalBetsWithSingeBetsForUser(GetTotalBetsType type, User user) throws SQLException {
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
            totalBets = totalBetTransformer.getAllWithSingleBets(rs);

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

}
