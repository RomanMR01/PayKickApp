package com.epam.javalab13.transformer.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.game.PlayerCoefficientDAO;
import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.dao.game.ResultCoefficientDAO;
import com.epam.javalab13.dao.game.ScoreCoefficientDAO;
import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.model.game.Status;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.Transformer;

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

    public List<Game> getAllWithCoefficients(ResultSet rs) throws SQLException {
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

            Team first = teamDAO.getTeam(firstTeam, "id");

            PlayerDAO playerDao = new PlayerDAO();
            List<Player> players = playerDao.getPlayersByTeam(first);
            first.setPlayers(players);
            Team second = teamDAO.getTeam(secondTeam, "id");
            players = playerDao.getPlayersByTeam(second);
            second.setPlayers(players);
            game.setFirstTeam(first);
            game.setSecondTeam(second);

            game.setFirstGoals(rs.getInt("first_goals"));
            game.setSecondGoals(rs.getInt("second_goals"));

            game.setStatus(Status.valueOf(rs.getString("status")));
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setId(rs.getInt("bookmaker_id"));

            User u = userDAO.getUser(user, UserDAO.GetOneUserType.ID);
            game.setBookmaker(u);
            game.setProfit(rs.getDouble("profit"));
//seting all coefficients for active game
            if (Status.ACTIVE.equals(game.getStatus())) {
                ResultCoefficientDAO resCoefDao = new ResultCoefficientDAO();
                List<ResultCoefficient> resultCoefficients = resCoefDao.getResultCoefficientsByGame(game);
                if (resultCoefficients != null && resultCoefficients.size() != 0) {
                    resultCoefficients.sort(new Comparator<ResultCoefficient>() {

                        @Override
                        public int compare(ResultCoefficient o1, ResultCoefficient o2) {
                            return o1.getResult().compareTo(o2.getResult());
                        }

                    });
                    game.setResultCoefficients(resultCoefficients);
                }
                ScoreCoefficientDAO scoreCoefDao = new ScoreCoefficientDAO();
                game.setScoreCoefficient(scoreCoefDao.getScoreCoefficientByGame(game));

                // get all playerCoefficients by game
                PlayerCoefficientDAO playerCoefficientDao = new PlayerCoefficientDAO();
                List<PlayerCoefficient> playerCoefficientList = playerCoefficientDao.getPlayerCoefficientByGame(game);

                // filter for first and second teams
                List<PlayerCoefficient> playerCoefficientList1 = playerCoefficientList.stream()
                        .filter(playerCoef -> playerCoef.getPlayer().getTeam() != null
                                && first.getId() == playerCoef.getPlayer().getTeam().getId())
                        .collect(Collectors.toList());
                List<PlayerCoefficient> playerCoefficientList2 = playerCoefficientList.stream()
                        .filter(playerCoef -> playerCoef.getPlayer().getTeam() != null
                                && second.getId() == playerCoef.getPlayer().getTeam().getId())
                        .collect(Collectors.toList());
                game.setFirstTeamPlayerCoefficients(playerCoefficientList1);
                game.setSecondTeamPlayerCoefficients(playerCoefficientList2);
            }
            games.add(game);
        }

        return games;
    }
}
