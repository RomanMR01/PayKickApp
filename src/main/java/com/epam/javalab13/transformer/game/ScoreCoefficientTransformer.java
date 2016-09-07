package com.epam.javalab13.transformer.game;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ScoreCoefficient;
import com.epam.javalab13.transformer.Transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/5/2016.
 */
public class ScoreCoefficientTransformer implements Transformer<ScoreCoefficient>{
    @Override
    public ScoreCoefficient getOne(ResultSet rs) throws SQLException {
        ScoreCoefficient scoreCoefficient = new ScoreCoefficient();

        if(rs.next()){
            scoreCoefficient.setId(rs.getInt("id"));

            GameDAO gameDAO = new GameDAO();
            Game game = gameDAO.getGamesById(rs.getInt("game_id"));

            scoreCoefficient.setGame(game);

            scoreCoefficient.setStartCoefficient(rs.getDouble("start_coefficient"));
            scoreCoefficient.setFirstTeamCoefficient(rs.getDouble("first_team_coefficient"));
            scoreCoefficient.setSecondTeamCoefficient(rs.getDouble("second_team_coefficient"));
        }
        return scoreCoefficient;
    }

    @Override
    public List<ScoreCoefficient> getAll(ResultSet rs) throws SQLException {
        List<ScoreCoefficient> scoreCoefficients = new ArrayList<>();

        while (rs.next()){
            ScoreCoefficient scoreCoefficient = new ScoreCoefficient();
            scoreCoefficient.setId(rs.getInt("id"));

            GameDAO gameDAO = new GameDAO();
            Game game = gameDAO.getGamesById(rs.getInt("game_id"));

            scoreCoefficient.setGame(game);

            scoreCoefficient.setSecondTeamCoefficient(rs.getDouble("start_coefficient"));
            scoreCoefficient.setFirstTeamCoefficient(rs.getDouble("first_team_coefficient"));
            scoreCoefficient.setSecondTeamCoefficient(rs.getDouble("second_team_coefficient"));

            scoreCoefficients.add(scoreCoefficient);
        }
        return scoreCoefficients;
    }
}
