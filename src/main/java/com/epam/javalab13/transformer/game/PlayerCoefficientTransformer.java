package com.epam.javalab13.transformer.game;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.bet.Result;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.transformer.Transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/5/2016.
 */
public class PlayerCoefficientTransformer implements Transformer<PlayerCoefficient>{
    @Override
    public PlayerCoefficient getOne(ResultSet rs) throws SQLException {
        PlayerCoefficient playerCoefficient = new PlayerCoefficient();
        if (rs.next()) {
            playerCoefficient.setId(rs.getInt("id"));

            Game game = new Game();
            GameDAO gameDAO = new GameDAO();
            game = gameDAO.getGamesById(rs.getInt("game_id"));

            playerCoefficient.setGame(game);

            Player p = new Player();
            p.setId(rs.getInt("player_id"));
            Player player = new PlayerDAO().getPlayer(p,"id");

            playerCoefficient.setPlayer(player);
            playerCoefficient.setCoefficient(rs.getDouble("coefficient"));

        }

        return playerCoefficient;
    }

    @Override
    public List<PlayerCoefficient> getAll(ResultSet rs) throws SQLException {
        List<PlayerCoefficient> playerCoefficients = new ArrayList<>();


        while (rs.next()) {
            PlayerCoefficient playerCoefficient = new PlayerCoefficient();
            playerCoefficient.setId(rs.getInt("id"));

            Game game = new Game();
            GameDAO gameDAO = new GameDAO();
            game = gameDAO.getGamesById(rs.getInt("game_id"));

            playerCoefficient.setGame(game);

            Player p = new Player();
            p.setId(rs.getInt("player_id"));
            Player player = new PlayerDAO().getPlayer(p,"id");

            playerCoefficient.setPlayer(player);
            playerCoefficient.setCoefficient(rs.getDouble("coefficient"));

            playerCoefficients.add(playerCoefficient);
        }

        return playerCoefficients;
    }
}
