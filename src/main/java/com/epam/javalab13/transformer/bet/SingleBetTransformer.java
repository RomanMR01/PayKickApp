package com.epam.javalab13.transformer.bet;

import com.epam.javalab13.dao.bet.TotalBetDAO;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.bet.*;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.transformer.Transformer;

import com.epam.javalab13.dao.bet.BetPlayerDAO;
import com.epam.javalab13.dao.bet.BetResultDAO;
import com.epam.javalab13.dao.bet.BetScoreDAO;
import com.epam.javalab13.dao.bet.BetTotalGoalsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SingleBetTransformer implements Transformer<SingleBet> {
    @Override
    public SingleBet getOne(ResultSet rs) throws SQLException {
        SingleBet singleBet = null;

        while (rs.next()) {

            TotalBetDAO totalBetDAO = new TotalBetDAO();
            TotalBet totalBet = totalBetDAO.getTotalBetById(rs.getInt("total_bet_id"));

            singleBet = new SingleBet();
            singleBet.setId(rs.getInt("id"));
            singleBet.setTotalBet(totalBet);
            Game game = new GameDAO().getGamesById(rs.getInt("game_id"));
            singleBet.setGame(game);
            singleBet.setCategory(Category.valueOf(rs.getString("category")));
            singleBet.setCoefficient(rs.getDouble("coefficient"));
            singleBet.setStatus(Status.valueOf(rs.getString("status")));

        }

        return singleBet;
    }

    @Override
    public List<SingleBet> getAll(ResultSet rs) throws SQLException {
        List<SingleBet> singleBets = new ArrayList<>();
        SingleBet singleBet = null;

        while (rs.next()) {


            TotalBetDAO totalBetDAO = new TotalBetDAO();
            TotalBet totalBet = totalBetDAO.getTotalBetById(rs.getInt("total_bet_id"));

            singleBet = new SingleBet();
            singleBet.setId(rs.getInt("id"));
            singleBet.setTotalBet(totalBet);
            Game game = new GameDAO().getGamesById(rs.getInt("game_id"));
            singleBet.setGame(game);
            singleBet.setCategory(Category.valueOf(rs.getString("category")));
            singleBet.setCoefficient(rs.getDouble("coefficient"));
            singleBet.setStatus(Status.valueOf(rs.getString("status")));

            singleBets.add(singleBet);
        }

        return singleBets;
    }

    /**
     * Gets fully initialized SingleBet from ResultSet rs. Depending on Category
     * of SingleBet sets one of these BetPlayer, BetResult, BetScore or
     * BetTotalGoal to SingleBet
     *
     * @return List of SingleBet
     * @throws SQLException
     */
    public List<SingleBet> getAllWithConcreteBet(ResultSet rs) throws SQLException {
        List<SingleBet> singleBets = new ArrayList<>();
        SingleBet singleBet = null;

        while (rs.next()) {

            TotalBetDAO totalBetDAO = new TotalBetDAO();
            TotalBet totalBet = totalBetDAO.getTotalBetById(rs.getInt("total_bet_id"));

            singleBet = new SingleBet();
            singleBet.setId(rs.getInt("id"));
            singleBet.setTotalBet(totalBet);
            Game game = new GameDAO().getGamesById(rs.getInt("game_id"));
            singleBet.setGame(game);
            singleBet.setCategory(Category.valueOf(rs.getString("category")));
            singleBet.setCoefficient(rs.getDouble("coefficient"));
            singleBet.setStatus(Status.valueOf(rs.getString("status")));

            switch (singleBet.getCategory()) {
                case GOALS:
                    BetTotalGoalsDAO betTotalGoalsDAO = new BetTotalGoalsDAO();
                    BetTotalGoals betTotalGoals = betTotalGoalsDAO.getBetTotalGoals(singleBet);
                    singleBet.setBetTotalGoals(betTotalGoals);
                    break;

                case PLAYER:
                    BetPlayerDAO betPlayerDao = new BetPlayerDAO();
                    BetPlayer betPlayer = betPlayerDao.getBetPlayer(singleBet);
                    singleBet.setBetPlayer(betPlayer);
                    break;

                case RESULT:
                    BetResultDAO betResultDAO = new BetResultDAO();
                    BetResult betResult = betResultDAO.getBetResult(singleBet);
                    singleBet.setBetResult(betResult);
                    break;

                case SCORE:
                    BetScoreDAO betScoreDAO = new BetScoreDAO();
                    BetScore betScore = betScoreDAO.getBetScore(singleBet);
                    singleBet.setBetScore(betScore);
                    break;
            }
            singleBets.add(singleBet);
        }
        return singleBets;
    }
}
