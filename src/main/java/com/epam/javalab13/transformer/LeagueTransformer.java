package com.epam.javalab13.transformer;

import com.epam.javalab13.model.game.League;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class LeagueTransformer implements Transformer<League> {

    @Override
    public League getOne(ResultSet rs) throws SQLException {
        League league=null;
        while (rs.next()) {
            league = new League(rs.getInt("id"),
                    rs.getString("name"));
        }
        return league;
    }

    @Override
    public List<League> getAll(ResultSet rs) throws SQLException {
        List<League> leagues = new ArrayList<>();
        League league;

        while (rs.next()) {
            league = new League(rs.getInt("id"),
                    rs.getString("name"));

            leagues.add(league);
        }
        return leagues;
    }
}