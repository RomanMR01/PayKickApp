package com.epam.javalab13.transformer;

import com.epam.javalab13.model.game.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class TeamTransformer implements Transformer<Team> {
    @Override
    public Team getOne(ResultSet rs) throws SQLException {
        Team team = null;
        while (rs.next()) {
            team = new Team(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("emblem_url"),
                    rs.getInt("total_wins"),
                    rs.getInt("total_loses"),
                    rs.getInt("total_draws")
            );
        }
        return team;
    }

    @Override
    public List<Team> getAll(ResultSet rs) throws SQLException {
        List<Team> teams = new ArrayList<>();
        Team team;

        while (rs.next()) {
            team=new Team(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("emblem_url"),
                    rs.getInt("total_wins"),
                    rs.getInt("total_loses"),
                    rs.getInt("total_draws")
            );

            teams.add(team);
        }
        return teams;
    }
}