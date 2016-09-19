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
import com.epam.javalab13.util.MailSender;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

import com.epam.javalab13.model.game.Team;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Vikno on 9/8/2016.
 */
public class GameService {

    //TODO ADD PROPERTIES FOR EMAIL
    private static Logger logger = Logger.getLogger(GameService.class);
    private MailSender sender = new MailSender("paykick.team@gmail.com", "paykickteam01");
    private GameDAO gameDAO = new GameDAO();

    /**
     * Updating game status to CANCELED by gameId
     *
     * @param gameId the unique game id
     * @return true if game canceled successfully, otherwise false if are throwing
     * some exception or game not found bu id (go to logs for more details)
     */
    public boolean cancelGame(int gameId) {
        try {
            return resetGame(gameId);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Exception while canceled game " + gameId + " !", e);
            return false;
        }
    }

    /**
     * Setting results for game by game id and score
     *
     * @param gameId
     * @param firstTeamScore
     * @param secondTeamScore
     * @return true if everything okay
     */
    public boolean setGameScore(int gameId, int firstTeamScore, int secondTeamScore) {
        try {
            setResults(gameId, firstTeamScore, secondTeamScore);
            return true;
        } catch (SQLException e) {
            logger.error("Exception while updating game " + gameId + " result with score " + firstTeamScore + ":" + secondTeamScore + " !", e);
            return false;
        }
    }

    /**
     * Getting game by id
     *
     * @param gameId
     * @return Game object if game exist, otherwise null
     */
    public Game getGameById(int gameId) {
        try {
            return gameDAO.getGamesById(gameId);
        } catch (SQLException e) {
            logger.error("Can't get game by id " + gameId, e);
        }

        return null;
    }

    /**
     * ! PRIVATE ! For cancelGame() only
     * Updating game status to CANCELED by gameId
     *
     * @param gameId the unique game id
     * @return true if game canceled successfully, otherwise false if are throwing
     * some exception or game not found bu id (go to logs for more details)
     */
    private boolean resetGame(int gameId) throws SQLException {
        logger.info("Admin set game status CANCELED for game: " + gameId);

        Game game = gameDAO.getGamesById(gameId);

        //If game exist
        if (game != null) {
            //Update game status to canceled
            gameDAO.updateGameByType(game, GameDAO.UpdateGameType.GAME_CANCELED);

            //Gets all single bets by game
            SingleBetDAO singleBetDAO = new SingleBetDAO();
            List<SingleBet> singleBets = singleBetDAO.getSingleBetsByGame(game);

            //Update single bets status to canceled
            for (SingleBet singleBet : singleBets) {
                singleBet.setStatus(Status.CANCELED);
                singleBetDAO.updateSingleBetStatus(singleBet);
            }

            //Group single bets by total bet id
            //Unique total bet id in single bets
            Set<Integer> totalBetIdList = new HashSet<>();

            //Get unique totalBetsIDs
            for (SingleBet singleBet : singleBets) {
                totalBetIdList.add(singleBet.getTotalBet().getId());
            }

            //Gets all total bets by single bets
            TotalBetDAO totalBetDAO = new TotalBetDAO();
            List<TotalBet> totalBets = new ArrayList<>();
            for (Integer totalBetId : totalBetIdList) {
                TotalBet totalBet = totalBetDAO.getTotalBetById(totalBetId);
                totalBets.add(totalBet);
            }

            List<User> usersInTotalBet = new ArrayList<>();
            //Update total bets status to canceled and return money to users
            UserDAO userDAO = new UserDAO();
            for (TotalBet totalBet : totalBets) {

                //If total bet already have status lost or canceled we skip cycle
                //Because money already are returned to game profit(if LOST) or to client(if canceled)
                if(totalBet.getStatus()==Status.LOST || totalBet.getStatus()==Status.CANCELED ){
                    continue;
                }
                totalBet.setStatus(Status.CANCELED);
                usersInTotalBet.add(totalBet.getUser());
                totalBetDAO.updateTotalBetStatus(totalBet);

                //Updating user balance (return amount)
                User user = totalBet.getUser();
                double balance = user.getBalance() + totalBet.getAmount();
                user.setBalance(balance);

                userDAO.updateUser(user, UserDAO.UpdateUserType.BALANCE);
            }
            //Sending emails
            ArrayList<String> usersUA = new ArrayList<>();
            ArrayList<String> usersEN = new ArrayList<>();

            for (User user : usersInTotalBet) {
                switch (user.getLanguage()) {
                    case ua_UA:
                        usersUA.add(user.getEmail());
                        break;
                    case en_EN:
                        usersEN.add(user.getEmail());
                        break;
                }
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (usersEN.size() > 0) {
                        sender.sendEmailsBatch("Game canceled", "game canceled", usersEN);
                    }
                    if (usersUA.size() > 0) {
                        sender.sendEmailsBatch("Гру відмінено", "Відмінено", usersUA);
                    }
                }
            }).start();


            return true;
        } else {
            return false;
        }
    }

    /**
     * ! PRIVATE ! For setGameScore() only
     * Setting results for game by game id and score
     * Usage: setResult(12,2,3) game by id 12, score 2:3
     * !! DO NOT TOUCH ANYTHING HERE (very bad code)!!
     * @param gameId      the Game id
     * @param firstGoals  the goals of first team
     * @param secondGoals the goals of second team
     * @throws SQLException
     */
    private void setResults(int gameId, int firstGoals, int secondGoals) throws SQLException {

        logger.info("Admin set result score " + firstGoals + ":" + secondGoals + " for game:" + gameId);

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
        switch (gameResult) {
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
            System.out.println("SB: " + singleBet);
            Status status = null;
            switch (singleBet.getCategory()) {
                case PLAYER:
                    BetPlayer betPlayer = betPlayerDAO.getBetPlayer(singleBet);
                    Player player = betPlayer.getPlayer();
                    int playerId = player.getId();

                    status = Status.LOST;//Temp status if cant find player
                    for(Player p:playersByGoals){
                        if(playerId==p.getId()){
                            status = Status.WON;
                            break;
                        }
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
                    } else {
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
        for (SingleBet singleBet : singleBets) {
            totalBetIdList.add(singleBet.getTotalBet().getId());
        }

        //8.3 Gets total bets by unique ids
        TotalBetDAO totalBetDAO = new TotalBetDAO();
        List<TotalBet> totalBets = new ArrayList<>();
        for (Integer totalBetId : totalBetIdList) {
            TotalBet totalBet = totalBetDAO.getTotalBetById(totalBetId);
            totalBets.add(totalBet);
        }

        //8.4 Updating status of total bets
        for (TotalBet totalBet : totalBets) {
            Type type = totalBet.getType();

            switch (type) {
                case SINGLE:
                    for (SingleBet singleBet : singleBets) {
                        if (singleBet.getTotalBet().getId() == totalBet.getId()) {
                            Status status = singleBet.getStatus();
                            totalBet.setStatus(status);
                            break;
                        }
                    }
                    break;
                case MULTIPLE:
                    Status totalBetStatus = Status.WON;
                    for (SingleBet singleBet : singleBets) {
                        if (singleBet.getTotalBet().getId() == totalBet.getId()) {
                            //If total bet already canceled we skip the cycle
                            //And will not return many to client or to game profit
                            if (totalBet.getStatus() == Status.CANCELED || totalBet.getStatus() == Status.LOST) {
                                totalBetStatus = Status.CANCELED;
                                break;
                            }
                            Status status = singleBet.getStatus();
                            //If one from multiple bet has status LOST, then TotalBet will have status LOST
                            if (status == Status.LOST) {
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
        for (TotalBet totalBet : totalBets) {
            totalBetDAO.updateTotalBetStatus(totalBet);
        }

        //9.MONEY
        //Updating balances for users
        double profit = 0;

        UserDAO userDAO = new UserDAO();

        //Lists for users by result status
        List<User> wonUsers = new ArrayList<>();
        List<User> lostUsers = new ArrayList<>();

        //Updating balances of users for single bet type
        for (TotalBet totalBet : totalBets) {
            if (totalBet.getType() == Type.SINGLE) {
                int amount = totalBet.getAmount();
                double award = totalBet.getAward();
                Status status = totalBet.getStatus();
                User user = totalBet.getUser();
                double userBalance = user.getBalance();

                if (status == Status.WON) {
                    profit -= (award - amount);
                    userBalance += award;
                    user.setBalance(userBalance);
                    userDAO.updateUser(user, UserDAO.UpdateUserType.BALANCE);

                    wonUsers.add(user);
                }
                if (status == Status.LOST) {
                    profit += amount;
                    lostUsers.add(user);
                }
            }
        }

        //Updating money for multiple bet type if all single bets are status WON
        for (TotalBet totalBet : totalBets) {
            if (totalBet.getType() == Type.MULTIPLE) {

                List<SingleBet> singleBetList = singleBetDAO.getSingleBetsByTotal(totalBet);
                boolean isAllWon = true;//If all single bets are with status WON

                Set<Status> wonOrLost = new HashSet<>();//Mast contains only WON or LOST statuses

                //Check if all single bets are with WON status
                for (SingleBet singleBet : singleBetList) {
                    wonOrLost.add(singleBet.getStatus());
                    if (singleBet.getStatus() != Status.WON) {
                        System.out.println(singleBet.getStatus());
                        isAllWon = false;
                        break;
                    }
                }

                //If all single bets are with won status we update user balance and profit for game
                if (isAllWon) {
                    double amount = totalBet.getAmount();
                    double award = totalBet.getAward();

                    User user = totalBet.getUser();

                    System.out.println("usr:" + user);
                    double userBalance = user.getBalance();
                    userBalance += award;
                    user.setBalance(userBalance);
                    userDAO.updateUser(user, UserDAO.UpdateUserType.BALANCE);
                    wonUsers.add(user);

                    profit -= (award - amount);
                }

                //If some of single bets are not WON, we check if it has only LOST and WON
                //And the update game profit
                if (!isAllWon) {
                    boolean containsOnly = true;

                    for (Status status : wonOrLost) {
                        if (status == Status.CANCELED || status == Status.ACTIVE) {
                            containsOnly = false;
                            break;
                        }
                    }

                    //If one of single bets already have status LOST, then client money are in game profit
                    //And we do not have do this again
                    for(SingleBet singleBet:singleBetList){
                        if(singleBet.getGame().getId()!=gameId && singleBet.getStatus()==Status.LOST){
                            containsOnly=false;
                            break;
                        }
                    }
                    if (containsOnly) {
                        profit += totalBet.getAmount();
                        lostUsers.add(totalBet.getUser());
                        totalBet.setStatus(Status.LOST);
                        totalBetDAO.updateTotalBetStatus(totalBet);
                    }
                }
            }
        }
        //Updating game profit
        game.setProfit(profit);
        gameDAO.updateGameByType(game, GameDAO.UpdateGameType.GAME_PROFIT);

        //List for users by result status and languages for EMAIL SENDING
        ArrayList<String> wonUsersUA = new ArrayList<>();
        ArrayList<String> wonUsersEN = new ArrayList<>();
        ArrayList<String> lostUsersUA = new ArrayList<>();
        ArrayList<String> lostUsersEN = new ArrayList<>();

        //Initialization all won users by them language
        for (User user : wonUsers) {
            switch (user.getLanguage()) {
                case ua_UA:
                    wonUsersUA.add(user.getEmail());
                    break;
                case en_EN:
                    wonUsersEN.add(user.getEmail());
                    break;
            }
        }

        //Initialization all lost users by them language
        for (User user : lostUsers) {
            switch (user.getLanguage()) {
                case en_EN:
                    lostUsersEN.add(user.getEmail());
                    break;
                case ua_UA:
                    lostUsersUA.add(user.getEmail());
                    break;
            }
        }

        System.out.println("w_UA" + wonUsersUA);
        System.out.println("l_UA" + lostUsersUA);
        System.out.println("w_EN" + wonUsersEN);
        System.out.println("l_EN" + lostUsersEN);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lostUsersEN.size() > 0) {
                    sender.sendEmailsBatch("You lost", "Some money", lostUsersEN);
                }
                if (lostUsersUA.size() > 0) {
                    sender.sendEmailsBatch("Ви програли гроші", "Програли", lostUsersUA);
                }
                if (wonUsersEN.size() > 0) {
                    sender.sendEmailsBatch("You won", "Won", wonUsersEN);
                }
                if (wonUsersUA.size() > 0) {
                    sender.sendEmailsBatch("Ви виграли", "Виграли", wonUsersUA);
                }
            }
        }).start();
    }

    /**
     * Creates new instance of Game inserts it into database and returns it with
     * teams and generated id;
     *
     * @param title
     * @param location
     * @param stringDate
     * @param firstTeamName
     * @param secondTeamName
     * @return Game game with generated id
     */
    public Game addNewGame(String title, String location, String stringDate, String firstTeamName,
                           String secondTeamName, String bookmakerName) {

        TeamDAO teamDao = new TeamDAO();
        Team team = new Team();
        Team firstTeam = null;
        Team secondTeam = null;
        try {
            team.setName(firstTeamName);
            firstTeam = teamDao.getTeam(team, "name");
            team.setName(secondTeamName);
            secondTeam = teamDao.getTeam(team, "name");
        } catch (Exception e) {
            logger.error("failed to instantiate team by name from database " + team, e);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = sdf.parse(stringDate);
        } catch (Exception e) {
            logger.error("failed to parse date from request " + stringDate, e);
        }

        if (date != null) {
            Date current = new Date();

            long added = date.getTime();
            long curr = current.getTime();

            if ((added - curr) < (86400_000)) {//less than 1 day
                date = null;
            }
            if ((added - curr) > (5_356_800_000L)) {//more than 2 month
                date = null;
            }
        }
        Game game = new Game();
        game.setTitle(title);
        game.setLocation(location);
        game.setDate(date);
        game.setFirstTeam(firstTeam);
        game.setSecondTeam(secondTeam);

        UserDAO userDAO = new UserDAO();
        User u = new User();
        u.setFullName(bookmakerName);

        User user = null;
        try {
            user = userDAO.getUser(u, UserDAO.GetOneUserType.NAME);
        } catch (Exception e) {
            logger.error("can't get find user by name " + bookmakerName, e);
        }
        game.setBookmaker(user);


        try {
            gameDAO.addGame(game);
        } catch (Exception e) {
            logger.error("failed to create add new game to database " + game, e);
        }

        return game;
    }

    public List<Game> getActiveGames(){
        GameDAO gameDAO = new GameDAO();
        List<Game> activeGames = null;
        try {
            activeGames = gameDAO.getGamesByStatus(GameDAO.GetGamesType.ACTIVE);
        } catch (SQLException e) {
           logger.error("Some error while getting active games!",e);
        }

        return activeGames;
    }
}
