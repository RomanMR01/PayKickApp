package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.game.GoalDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Goal;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class GoalService {
    GoalDAO goalDAO = new GoalDAO();

    private static Logger logger = Logger.getLogger(GoalService.class);

    public void addGoal(int gameid,int teamId,int playerId) {
        Goal goal = new Goal();
        goal.setMinute(0);

        Player player = new Player();
        player.setId(playerId);
        goal.setPlayer(player);

        Game game = new Game();
        game.setId(gameid);
        goal.setGame(game);

        Team team = new Team();
        team.setId(teamId);
        goal.setTeam(team);

        try {
            goalDAO.addGoal(goal);
        } catch (SQLException e) {
           logger.error("Can't add new goal: " + goal,e);
        }
    }

    public List<Goal> getAllGoals() throws SQLException {
        return goalDAO.getAllGoals();
    }
}
