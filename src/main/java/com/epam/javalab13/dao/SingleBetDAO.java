package com.epam.javalab13.dao;

import com.epam.javalab13.model.bet.SingleBet;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.transformer.SingleBetTransformer;
import com.epam.javalab13.transformer.TotalBetTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    /**
     * Getting all single bets by total bet
     * @param totalBet the TotalBet object
     * @return list of single bets
     * @throws SQLException
     */
    public List<SingleBet> getSingleBetsByTotal(TotalBet totalBet) throws SQLException {
        final String SQL = "SELECT * FROM single_bet sb WHERE sb.total_bet_id=?";

        List<SingleBet> singleBets = null;
        SingleBetTransformer transformer = new SingleBetTransformer();

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,totalBet.getId());
            rs = st.executeQuery();


            singleBets = transformer.getAll(rs);

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

        return singleBets;

    }

    /**
     * Getting SingleBet
     * @param id the single bet id
     * @return the SingleBet object
     * @throws SQLException
     */
    public SingleBet getSingleBetById(int id) throws SQLException {
        final String SQL = "SELECT * FROM single_bet sb WHERE sb.id=?";

        Connection conn = ConnectionPool.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        SingleBet singleBet = null;



        try {
            st = conn.prepareStatement(SQL);
            st.setInt(1,id);
            rs = st.executeQuery();

            SingleBetTransformer singleBetTransformer = new SingleBetTransformer();
            singleBet = singleBetTransformer.getOne(rs);

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

        return singleBet;
    }
}
