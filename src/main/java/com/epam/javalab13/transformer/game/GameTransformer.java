package com.epam.javalab13.transformer.game;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
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
public class GameTransformer implements Transformer<Game>{
    @Override
    public Game getOne(ResultSet rs) throws SQLException {
        Game game = null;
        while (rs.next()) {
            game = new Game();
            game.setId(rs.getInt("id"));
            game.setTitle(rs.getString("title"));
            game.setLocation(rs.getString("location"));
            game.setDate(rs.getTimestamp("date"));

            Team firstTeam = new Team();
            firstTeam.setId(rs.getInt("first_team_id"));

            Team secondTeam = new Team();
            secondTeam.setId(rs.getInt("second_team_id"));

            TeamDAO teamDAO = new TeamDAO();

            Team first = teamDAO.getTeam(firstTeam,"id");
            Team second = teamDAO.getTeam(secondTeam,"id");
            game.setFirstTeam(first);
            game.setSecondTeam(second);

            game.setFirstGoals(rs.getInt("first_goals"));
            game.setSecondGoals(rs.getInt("second_goals"));

            game.setStatus(Status.valueOf(rs.getString("status")));
            UserDAO userDAO = new UserDAO();
            User user =  new User();
            user.setId(rs.getInt("bookmaker_id"));

            User u = userDAO.getUser(user, UserDAO.GetOneUserType.ID);
            game.setBookmaker(u);
            game.setProfit(rs.getDouble("profit"));
        }

        return game;
    }

    @Override
    public List<Game> getAll(ResultSet rs) throws SQLException {
        List<Game> games = new ArrayList<>();


        while (rs.next()) {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setTitle(rs.getString("title"));
            game.setLocation(rs.getString("location"));
            game.setDate(rs.getTimestamp("date"));

            Team firstTeam = new Team();
            firstTeam.setId(rs.getInt("first_team_id"));

            Team secondTeam = new Team();
            secondTeam.setId(rs.getInt("second_team_id"));

            TeamDAO teamDAO = new TeamDAO();

            Team first = teamDAO.getTeam(firstTeam,"id");
            Team second = teamDAO.getTeam(secondTeam,"id");
            game.setFirstTeam(first);
            game.setSecondTeam(second);

            game.setFirstGoals(rs.getInt("first_goals"));
            game.setSecondGoals(rs.getInt("second_goals"));

            game.setStatus(Status.valueOf(rs.getString("status")));
            UserDAO userDAO = new UserDAO();
            User user =  new User();
            user.setId(rs.getInt("bookmaker_id"));

            User u = userDAO.getUser(user, UserDAO.GetOneUserType.ID);
            game.setBookmaker(u);
            game.setProfit(rs.getDouble("profit"));

            games.add(game);

        }

        return games;
    }
}
