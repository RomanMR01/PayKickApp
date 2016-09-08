package com.epam.javalab13.dao.bet;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.bet.BetPlayer;
import com.epam.javalab13.model.bet.SingleBet;
import com.epam.javalab13.model.game.Player;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 CRUD operations for bet_player table
 */
public class BetPlayerDAO {
    private static Logger logger = Logger.getLogger(BetPlayerDAO.class);

    /**
     * Add new BetPlayer into database
     * @param betPlayer the BetPlayer object
     * @throws SQLException
     */
    public void addBetPlayer(BetPlayer betPlayer) throws SQLException {
        final String SQL = "INSERT INTO bet_player(single_bet_id, player_id) VALUES(?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,betPlayer.getSingleBet().getId());
            st.setInt(2,betPlayer.getPlayer().getId());


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
     * Getting BetPlayer by SingleBet
     * @param singleBet the SingleBet object
     * @throws SQLException
     */
    public BetPlayer getBetPlayer(SingleBet singleBet) throws SQLException {
        final String SQL = "SELECT * FROM bet_player bp WHERE bp.single_bet_id = ?";

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        BetPlayer betPlayer = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);
            st.setInt(1,singleBet.getId());

            rs = st.executeQuery();

            while (rs.next()){
                SingleBet sb = new SingleBetDAO().getSingleBetById(singleBet.getId());
                PlayerDAO playerDAO = new PlayerDAO();
                Player p = new Player();
                p.setId(rs.getInt("player_id"));
                Player player = playerDAO.getPlayer(p,"id");
                betPlayer = new BetPlayer(sb,player);
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

        return betPlayer;
    }
}
