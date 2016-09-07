package com.epam.javalab13.transformer.game;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.TotalGoalsCoefficient;
import com.epam.javalab13.transformer.Transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/5/2016.
 */
public class TotalGoalsCoefficientTransformer implements Transformer<TotalGoalsCoefficient>{
    @Override
    public TotalGoalsCoefficient getOne(ResultSet rs) throws SQLException {
        TotalGoalsCoefficient totalGoalsCoefficient = new TotalGoalsCoefficient();

        if(rs.next()){
            totalGoalsCoefficient.setId(rs.getInt("id"));

            GameDAO gameDAO = new GameDAO();
            Game game = gameDAO.getGamesById(rs.getInt("game_id"));
            totalGoalsCoefficient.setGame(game);

            totalGoalsCoefficient.setGoalCoefficient(rs.getDouble("goal_coefficient"));
        }
        return totalGoalsCoefficient;
    }

    @Override
    public List<TotalGoalsCoefficient> getAll(ResultSet rs) throws SQLException {
        List<TotalGoalsCoefficient> totalGoalsCoefficients = new ArrayList<>();

        while (rs.next()){
            TotalGoalsCoefficient totalGoalsCoefficient = new TotalGoalsCoefficient();
            totalGoalsCoefficient.setId(rs.getInt("id"));

            GameDAO gameDAO = new GameDAO();
            Game game = gameDAO.getGamesById(rs.getInt("game_id"));
            totalGoalsCoefficient.setGame(game);

            totalGoalsCoefficient.setGoalCoefficient(rs.getDouble("goal_coefficient"));

            totalGoalsCoefficients.add(totalGoalsCoefficient);
        }
        return totalGoalsCoefficients;
    }
}
