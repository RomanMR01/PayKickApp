package com.epam.javalab13.dao;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Goal;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class GoalDAO {
    private static Logger logger = Logger.getLogger(GoalDAO.class);

    public void addGoal(Goal goal) throws SQLException {
        logger.info("DAO addGoal entry");
        final String SQL = "INSERT INTO goal(player_id, team_id, game_id, minute) VALUES(?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, goal.getPlayer().getId());
            preparedStatement.setInt(2, goal.getTeam().getId());
            preparedStatement.setInt(3, goal.getGame().getId());
            preparedStatement.setInt(4, goal.getMinute());

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

    public List<Goal> getAllGoals() throws SQLException {
        final String SQL = "SELECT * FROM goal";

        List<Goal> goalList = new ArrayList<>();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL);

            while (rs.next()) {
                Player player=new Player();
                Team team=new Team();
                Game game=new Game();
                //todo add queries for inner objects

                player.setId(rs.getInt("player_id"));
                team.setId(rs.getInt("team_id"));
                game.setId(rs.getInt("game_id"));

                Goal goal = new Goal(rs.getInt("id"),player,team,game,rs.getInt("minute"));
                System.out.println(goal);
                goalList.add(goal);
            }

            return goalList;

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