package com.epam.javalab13.transformer;

import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class PlayerTransformer implements Transformer<Player> {
    @Override
    public Player getOne(ResultSet rs) throws SQLException {
        Player player = null;
        while (rs.next()) {
            //todo add inner query for team?
            Team team = new Team();
            team.setId(rs.getInt("team_id"));
            player = new Player(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getInt("total_games"),
                    team
            );
        }
        return player;
    }

    
    @Override
    public List<Player> getAll(ResultSet rs) throws SQLException {
        List<Player> players = new ArrayList<>();
        Player player;

        while (rs.next()) {
            Team team = new Team();
            team.setId(rs.getInt("team_id"));

            player = new Player(rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getInt("total_games"),
                    team
            );

            players.add(player);
        }
        return players;
    }
}