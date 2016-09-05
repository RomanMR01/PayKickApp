package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.game.GoalDAO;
import com.epam.javalab13.model.game.Goal;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class GoalService {
    GoalDAO goalDAO = new GoalDAO();

    public void addGoal(Goal goal) throws SQLException {
        goalDAO.addGoal(goal);
    }

    public List<Goal> getAllGoals() throws SQLException {
        return goalDAO.getAllGoals();
    }
}
