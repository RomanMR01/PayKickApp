package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.League;
import com.epam.javalab13.transformer.LeagueTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Olga on 04.09.2016.
 */
public class LeagueDAO {
    private static Logger logger = Logger.getLogger(LeagueDAO.class);

    public void addLeague(League league) throws SQLException {
        logger.info("DAO addLeague entry");
        final String SQL = "INSERT INTO league(id, name) VALUES(?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, league.getId());
            preparedStatement.setString(2, league.getName());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (connection != null) try {
                connection.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }
        }
    }

    public League getLeague(League league, String type) throws SQLException {
        final String SQL_ID = "SELECT * FROM league l WHERE l.id = ?";
        final String SQL_NAME = "SELECT * FROM league l WHERE l.name LIKE ?";

        League returnLeague = null;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        LeagueTransformer transformer=new LeagueTransformer();

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case "id":
                    st = conn.prepareStatement(SQL_ID);
                    st.setInt(1, league.getId());
                    rs = st.executeQuery();

                    returnLeague = transformer.getOne(rs);
                    break;
                case "name":
                    st = conn.prepareStatement(SQL_NAME);
                    st.setString(1, league.getName());
                    rs = st.executeQuery();

                    returnLeague = transformer.getOne(rs);
                    break;
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

        return returnLeague;
    }
}
