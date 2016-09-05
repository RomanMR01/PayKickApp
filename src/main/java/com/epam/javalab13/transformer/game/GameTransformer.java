package com.epam.javalab13.transformer.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.model.Gender;
import com.epam.javalab13.model.Language;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Status;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.Transformer;

public class GameTransformer implements Transformer<Game> {

    private static final Logger logger = Logger.getLogger(GameTransformer.class);

    @Override
    public Game getOne(ResultSet rs) {
        Game game = null;
        try {
            if (rs.next()) {
                game = createGame(rs);
            }
        } catch (SQLException e) {
            logger.error("failed to instantiate game from ResultSet", e);
        }
        return game;
    }

    /**
     * Returns instance of Game from this ResultSet rs if method rs.next was called before.
     * @param rs
     * @return Game
     * @throws SQLException
     */
    public Game createGame(ResultSet rs) throws SQLException {
        Game game = null;
        game = new Game();
        game.setId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setLocation(rs.getString("location"));
        game.setDate(rs.getDate("date"));
        game.setFirstGoals(rs.getInt("first_goals"));
        game.setSecondGoals(rs.getInt("second_goals"));
        game.setStatus(Status.valueOf(rs.getString("status")));
        game.setProfit(rs.getDouble("profit"));
        game.setFirstTeam(getTeam(rs, 1));
        game.setSecondTeam(getTeam(rs, 2));
        game.setBookmaker(getBookmaker(rs));
        return game;
    }

    /**
     * Instantiating user from resultset. Note that Field List
     * <TotalBet> totalBets will be declared as null;
     *
     * @param rs
     * @return User
     * @throws SQLException
     */
    private User getBookmaker(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("u_id"));
        user.setFullName(rs.getString("full_name"));
        user.setAge(rs.getInt("age"));
        user.setGender(Gender.valueOf(rs.getString("gender")));
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setBalance(rs.getDouble("balance"));
        user.setAvatarUrl(rs.getString("avatar_url"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setLanguage(Language.valueOf(rs.getString("language")));
        user.setBanned(rs.getBoolean("is_banned"));
        return user;
    }

    private Team getTeam(ResultSet rs, int i) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("t" + i + "_id"));
        team.setName(rs.getString("t" + i + "_name"));
        team.setLocation(rs.getString("t" + i + "_location"));
        team.setEmblemUrl(rs.getString("t" + i + "_emblem_url"));
        team.setTotalDraws(rs.getInt("t" + i + "_total_draws"));
        team.setTotalLoses(rs.getInt("t" + i + "_total_loses"));
        team.setTotalWins(rs.getInt("t" + i + "_total_wins"));
        return team;
    }

    @Override
    public List<Game> getAll(ResultSet rs) {
        List<Game> games = new ArrayList<>();
        try {
            while(rs.next()){
                games.add(createGame(rs));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("failed to get list of games",e);
        }
        return games;
    }

}