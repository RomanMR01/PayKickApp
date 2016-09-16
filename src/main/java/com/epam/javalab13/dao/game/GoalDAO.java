package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
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

    public int countAllGoals(Player player){
        final String SQL="SELECT COUNT(id) as total FROM goal where player_id=?";
        Connection conn = ConnectionPool.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1,player.getId());
            ResultSet resultSet = statement.executeQuery();
            int result=0;
            while(resultSet.next()){
                result = resultSet.getInt("total");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public List<Goal> getAllGoalsByGame(Game game) throws SQLException {
        final String SQL = "SELECT * FROM goal g WHERE g.game_id=?";

        List<Goal> goals = new ArrayList<>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setInt(1,game.getId());

            rs = st.executeQuery();

            while (rs.next()){
                Goal goal = new Goal();

                Player p = new Player();
                p.setId(rs.getInt("player_id"));
                Player player = new PlayerDAO().getPlayer(p,"id");

                Team t = new Team();
                t.setId(rs.getInt("team_id"));
                Team team = new TeamDAO().getTeam(t,"id");


                Game g = new GameDAO().getGamesById(game.getId());

                goal.setId(rs.getInt("id"));
                goal.setPlayer(player);
                goal.setTeam(team);
                goal.setGame(g);
                goal.setMinute(rs.getInt("minute"));

                goals.add(goal);
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

        return goals;
    }
}