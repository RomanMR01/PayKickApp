package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.bet.*;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.GoalDAO;
import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.*;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Goal;
import com.epam.javalab13.model.game.Player;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vikno on 9/8/2016.
 */
public class GameService {

    private static Logger logger = Logger.getLogger(GameService.class);

    /**
     * Setting results for game by game id and score
     * Usage: setResult(12,2,3) game by id 12, score 2:3
     * @param gameId the Game id
     * @param firstGoals the goals of first team
     * @param secondGoals the goals of second team
     * @throws SQLException
     */
    private void setResults(int gameId, int firstGoals, int secondGoals) throws SQLException {

        logger.info("Admin set result score " + firstGoals + ":" + secondGoals + " for game:" + gameId);
        GameDAO gameDAO = new GameDAO();

        //1. Get game by id
        Game game = gameDAO.getGamesById(gameId);

        //2. Update game goals
        game.setFirstGoals(firstGoals);
        game.setSecondGoals(secondGoals);
        gameDAO.updateGameByType(game, GameDAO.UpdateGameType.GAME_FINISHED);

        //3.Gets all players from game by teams and update them total_games count
        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.updatePlayersTotalGamesByTeams(game.getFirstTeam(), game.getSecondTeam());

        //4.Get all single bets by game id
        SingleBetDAO singleBetDAO = new SingleBetDAO();
        List<SingleBet> singleBets = singleBetDAO.getSingleBetsByGame(game);

        //Results!
        //1.GoalsCount
        int goalsCount = firstGoals + secondGoals;
        //2.Score:
        int first = firstGoals;
        int second = secondGoals;
        //3.Result:
        Result gameResult = null;
        if (first > second) {
            gameResult = Result.C1;
        }
        if (second > first) {
            gameResult = Result.C2;
        }
        if (first == second) {
            gameResult = Result.CX;
        }
        //4.Player
        GoalDAO goalDAO = new GoalDAO();
        List<Goal> goals = goalDAO.getAllGoalsByGame(game);
        List<Player> playersByGoals = new ArrayList<>();
        for (Goal goal : goals) {
            playersByGoals.add(goal.getPlayer());
        }
        //END Results!

        //5.Update teams wins etc.
        TeamDAO teamDAO = new TeamDAO();
        switch (gameResult){
            case C1:
                teamDAO.updateTeamsByType(game.getFirstTeam(), TeamDAO.UpdateTeamType.WINS);
                teamDAO.updateTeamsByType(game.getSecondTeam(), TeamDAO.UpdateTeamType.LOSES);
                break;
            case C2:
                teamDAO.updateTeamsByType(game.getFirstTeam(), TeamDAO.UpdateTeamType.LOSES);
                teamDAO.updateTeamsByType(game.getSecondTeam(), TeamDAO.UpdateTeamType.WINS);
                break;
            case CX:
                teamDAO.updateTeamsByType(game.getFirstTeam(), TeamDAO.UpdateTeamType.DRAWS);
                teamDAO.updateTeamsByType(game.getSecondTeam(), TeamDAO.UpdateTeamType.DRAWS);
                break;
        }

        //6.Go by every single bet and watch category of bet and update single bet status
        BetPlayerDAO betPlayerDAO = new BetPlayerDAO();
        BetResultDAO betResultDAO = new BetResultDAO();
        BetScoreDAO betScoreDAO = new BetScoreDAO();
        BetTotalGoalsDAO betTotalGoalsDAO = new BetTotalGoalsDAO();

        for (SingleBet singleBet : singleBets) {
            Status status = null;
            switch (singleBet.getCategory()) {
                case PLAYER:
                    BetPlayer betPlayer = betPlayerDAO.getBetPlayer(singleBet);
                    Player player = betPlayer.getPlayer();
                    if (playersByGoals.contains(player)) {
                        status = Status.WON;
                    } else {
                        status = Status.LOST;
                    }
                    break;
                case RESULT:
                    BetResult betResult = betResultDAO.getBetResult(singleBet);
                    Result result = betResult.getResult();
                    switch (result) {
                        case C1:
                        case C2:
                        case CX:
                            if (result == gameResult) {
                                status = Status.WON;
                            } else {
                                status = Status.LOST;
                            }
                            break;
                        case C1X:
                            if ((gameResult == Result.C1) || (gameResult == Result.CX)) {
                                status = Status.WON;
                            } else {
                                status = Status.LOST;
                            }
                            break;
                        case CX2:
                            if ((gameResult == Result.C2) || (gameResult == Result.CX)) {
                                status = Status.WON;
                            } else {
                                status = Status.LOST;
                            }
                            break;
                        case C12:
                            if (gameResult != Result.CX) {
                                status = Status.WON;
                            } else {
                                status = Status.LOST;
                            }
                            break;
                    }
                    break;
                case SCORE:
                    BetScore betScore = betScoreDAO.getBetScore(singleBet);
                    int firstTeamScore = betScore.getFirstTeamScore();
                    int secondTeamScore = betScore.getSecondTeamScore();

                    if ((firstTeamScore == first) && (secondTeamScore == second)) {
                        status = Status.WON;
                    }else{
                        status = Status.LOST;
                    }
                    break;
                case GOALS:
                    BetTotalGoals betTotalGoals = betTotalGoalsDAO.getBetTotalGoals(singleBet);
                    int totalGoals = betTotalGoals.getTotalGoal();
                    if (totalGoals == goalsCount) {
                        status = Status.WON;
                    } else {
                        status = Status.LOST;
                    }
                    break;
            }

            if (status != null) {
                singleBet.setStatus(status);
            }
        }

        //7.Updating status for all single bets
        for (SingleBet singleBet : singleBets) {
            singleBetDAO.updateSingleBetStatus(singleBet);
        }

        //8.TOTAL BET

        //8.1 Group single bets by total bet id
        //Unique total bet id in single bets
        Set<Integer> totalBetIdList = new HashSet<>();

        //8.2 Get unique totalBetsIDs
        for(SingleBet singleBet:singleBets){
            totalBetIdList.add(singleBet.getTotalBet().getId());
        }

        //8.3 Gets total bets by unique ids
        TotalBetDAO totalBetDAO = new TotalBetDAO();
        List<TotalBet> totalBets = new ArrayList<>();
        for(Integer totalBetId:totalBetIdList){
            TotalBet totalBet = totalBetDAO.getTotalBetById(totalBetId);
            totalBets.add(totalBet);
        }

        //8.4 Updating status of total bets
        for(TotalBet totalBet:totalBets){
            Type type = totalBet.getType();

            switch (type){
                case SINGLE:
                    for(SingleBet singleBet:singleBets){
                        if(singleBet.getTotalBet().getId() == totalBet.getId()){
                            Status status = singleBet.getStatus();
                            totalBet.setStatus(status);
                            break;
                        }
                    }
                    break;
                case MULTIPLE:
                    Status totalBetStatus = Status.WON;
                    for(SingleBet singleBet:singleBets){
                        if(singleBet.getTotalBet().getId() == totalBet.getId()){
                            Status status = singleBet.getStatus();
                            //If one from multiple bet has status LOST, then TotalBet will have status LOST
                            if(status == Status.LOST){
                                totalBetStatus = Status.LOST;
                                break;
                            }
                        }
                    }
                    totalBet.setStatus(totalBetStatus);
                    break;
            }


        }

        //8.5 Update DB
        for(TotalBet totalBet:totalBets){
            totalBetDAO.updateTotalBetStatus(totalBet);
        }

        //9.MONEY
        //Updating balances for users
        double profit = 0;

        UserDAO userDAO = new UserDAO();

        //TODO add email sending
        //Updating balances of users
        for(TotalBet totalBet:totalBets){
            int amount = totalBet.getAmount();
            double award = totalBet.getAward();
            Status status = totalBet.getStatus();
            User user = totalBet.getUser();
            double userBalance = user.getBalance();

            if(status == Status.WON){
                profit -=(award-amount);
                userBalance+=award;
                user.setBalance(userBalance);
                userDAO.updateUser(user, UserDAO.UpdateUserType.BALANCE);
                //TODO send email tha user won
            }
            if(status == Status.LOST){
                profit +=amount;
                //TODO send email that user lost
            }
        }


        game.setProfit(profit);
        gameDAO.updateGameByType(game, GameDAO.UpdateGameType.GAME_PROFIT);
    }

    public boolean setGameScore(int gameId,int firstTeamScore, int secondTeamScore){
        try {
            setResults(gameId,firstTeamScore,secondTeamScore);
            return true;
        } catch (SQLException e) {
            logger.error("Exception while updating game " + gameId +" result with score " + firstTeamScore + ":" + secondTeamScore + " !",e);
            return false;
        }
    }
}
