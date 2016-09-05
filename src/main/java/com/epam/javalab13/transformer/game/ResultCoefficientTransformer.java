package com.epam.javalab13.transformer.game;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.Result;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.model.game.Status;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.Transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/5/2016.
 */
public class ResultCoefficientTransformer implements Transformer<ResultCoefficient>{
    @Override
    public ResultCoefficient getOne(ResultSet rs) throws SQLException {
        ResultCoefficient resultCoefficient  = new ResultCoefficient();
        while (rs.next()) {
            resultCoefficient.setId(rs.getInt("id"));

            Game game = new Game();
            GameDAO gameDAO = new GameDAO();
            game = gameDAO.getGamesById(rs.getInt("game_id"));

            resultCoefficient.setGame(game);
            resultCoefficient.setResult(Result.valueOf(rs.getString("result")));
            resultCoefficient.setCoefficient(rs.getDouble("coefficient"));

        }
        return resultCoefficient;

    }

    @Override
    public List<ResultCoefficient> getAll(ResultSet rs) throws SQLException {
        List<ResultCoefficient> resultCoefficients = new ArrayList<>();


        while (rs.next()) {
            ResultCoefficient resultCoefficient = new ResultCoefficient();
            resultCoefficient.setId(rs.getInt("id"));

            Game game = new Game();
            GameDAO gameDAO = new GameDAO();
            game = gameDAO.getGamesById(rs.getInt("game_id"));

            resultCoefficient.setGame(game);
            resultCoefficient.setResult(Result.valueOf(rs.getString("result")));
            resultCoefficient.setCoefficient(rs.getDouble("coefficient"));

            resultCoefficients.add(resultCoefficient);
        }

        return resultCoefficients;
    }
}
