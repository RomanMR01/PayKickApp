package com.epam.javalab13.service;

import com.epam.javalab13.dao.bet.*;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.*;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * For Client bets
 */
public class ClientBetsService {

    /**
     * Main method for adding client bet to DB
     * @param jsonArray string representation of data received from client
     * @param betsCount total count of single bets
     * @param amount sum client deposit
     * @param award sum, that client can won
     * @param userID id of client
     * @return
     */
    public boolean makeBet(final String jsonArray, final int betsCount, final int amount, double award, int userID){
        String json = jsonArray;

        /*
        Deserialization of json
         */
        Type type = new TypeToken<List<OneGameBet>>(){}.getType();
        List<OneGameBet> oneGameBets = new Gson().fromJson(json,type);

        /*
        Creating new total bet
         */
        TotalBetDAO totalBetDAO = new TotalBetDAO();
        TotalBet totalBet = new TotalBet();

        User user = new User();
        user.setId(userID);
        totalBet.setUser(user);

        /*
        Set type of total bet (if betsCount>1 then will be MULTIPLE)
         */
        if(betsCount==1){
            totalBet.setType(com.epam.javalab13.model.bet.Type.SINGLE);
        }else{
            totalBet.setType(com.epam.javalab13.model.bet.Type.MULTIPLE);
        }

        totalBet.setAmount(amount);
        totalBet.setAward(award);
        totalBet.setDate(new Date());

        //Adding new TotalBet to DB
        try {
            totalBetDAO.addTotalBet(totalBet);
        } catch (SQLException e) {
            //TODO add logger
            e.printStackTrace();
        }
        System.out.println(totalBet);

        SingleBetDAO singleBetDAO = new SingleBetDAO();

        BetResultDAO betResultDAO = new BetResultDAO();
        BetScoreDAO betScoreDAO = new BetScoreDAO();
        BetTotalGoalsDAO betTotalGoalsDAO = new BetTotalGoalsDAO();
        BetPlayerDAO betPlayerDAO = new BetPlayerDAO();

        /*
        Adding single bets to DB by them type
         */
        for(OneGameBet oneGameBet:oneGameBets){
            int gameId = oneGameBet.getGameId();

            //Bet on result
            if(oneGameBet.getBetResult()!=null){
                //Data to add
                Result result = Result.valueOf(oneGameBet.getBetResult().getBetResultType().toString());
                double coefficient = oneGameBet.getBetResult().getCoefficient();

                //Single bet
                SingleBet singleBet = new SingleBet();

                Game game = new Game();
                game.setId(gameId);

                singleBet.setTotalBet(totalBet);
                singleBet.setGame(game);
                singleBet.setCategory(Category.RESULT);
                singleBet.setCoefficient(coefficient);

                try {
                    singleBetDAO.addSingleBet(singleBet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                //Bet on result
                BetResult betResult = new BetResult();
                betResult.setSingleBet(singleBet);
                betResult.setResult(result);

                try {
                    betResultDAO.addBetResult(betResult);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            //Bet on game score
            if(oneGameBet.getBetScore()!=null){
                double startCoefficient = oneGameBet.getBetScore().getStartCoefficient();
                int firstTeamScore = oneGameBet.getBetScore().getFirstTeamScore();
                int secondTeamScore = oneGameBet.getBetScore().getSecondTeamScore();

                //Single bet
                SingleBet singleBet = new SingleBet();

                Game game = new Game();
                game.setId(gameId);

                singleBet.setTotalBet(totalBet);
                singleBet.setGame(game);
                singleBet.setCategory(Category.SCORE);
                singleBet.setCoefficient(startCoefficient);

                try {
                    singleBetDAO.addSingleBet(singleBet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //Bet on game score
                BetScore betScore = new BetScore();
                betScore.setSingleBet(singleBet);
                betScore.setFirstTeamScore(firstTeamScore);
                betScore.setSecondTeamScore(secondTeamScore);

                try {
                    betScoreDAO.addBetScore(betScore);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            //Bet on total goals sum
            if(oneGameBet.getBetTotalGoals()!=null){
                int totalGoalsValue = oneGameBet.getBetTotalGoals().getTotalGoalsValue();
                double totalGoalsCoefficient = oneGameBet.getBetTotalGoals().getTotalGoalsCoefficient();

                //Single bet
                SingleBet singleBet = new SingleBet();

                Game game = new Game();
                game.setId(gameId);

                singleBet.setTotalBet(totalBet);
                singleBet.setGame(game);
                singleBet.setCategory(Category.GOALS);
                singleBet.setCoefficient(totalGoalsCoefficient);

                try {
                    singleBetDAO.addSingleBet(singleBet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                //Bet on total goals
                BetTotalGoals betTotalGoals = new BetTotalGoals();
                betTotalGoals.setSingleBet(singleBet);
                betTotalGoals.setTotalGoal(totalGoalsValue);
                try {
                    betTotalGoalsDAO.addBetTotalGoals(betTotalGoals);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //Bets on players
            if(oneGameBet.getBetPlayer()!=null || oneGameBet.getBetPlayer().size()!=0){
                //All players, that user choose
                List<PlayerData> players = oneGameBet.getBetPlayer();

                for(PlayerData playerData:players){
                    int playerId = playerData.getPlayerId();
                    double playerCoefficient = playerData.getPlayerCoefficient();

                    //Single bet
                    SingleBet singleBet = new SingleBet();

                    Game game = new Game();
                    game.setId(gameId);

                    singleBet.setTotalBet(totalBet);
                    singleBet.setGame(game);
                    singleBet.setCategory(Category.PLAYER);
                    singleBet.setCoefficient(playerCoefficient);

                    try {
                        singleBetDAO.addSingleBet(singleBet);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    //Bet on one player
                    BetPlayer betPlayer = new BetPlayer();

                    Player player = new Player();
                    player.setId(playerId);

                    betPlayer.setPlayer(player);
                    betPlayer.setSingleBet(singleBet);

                    try {
                        betPlayerDAO.addBetPlayer(betPlayer);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    public List<Game> getActiveGames(){
        List<Game> activeGames = new ArrayList<>();
        try {
            activeGames = new GameDAO().getGamesWithCoefficientsByType(GameDAO.Type.ACTIVE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activeGames;
    }

    public String getAllGamesIDs(){
        List<Game> activeGames = getActiveGames();
        return getIDsForAllGames(activeGames);
    }

    public String getFirstTeamsIDs(){
        List<Game> activeGames = getActiveGames();
        return getIDsForTeams(activeGames,TeamNumber.FIRST);
    }

    public String getSecondTeamsIDs(){
        List<Game> activeGames = getActiveGames();
        return getIDsForTeams(activeGames,TeamNumber.SECOND);
    }


    public String getFirstTeamsPlayersIDs(){
        List<Game> activeGames = getActiveGames();
        return getIDsForPlayers(activeGames,TeamNumber.FIRST);
    }

    public String getSecondTeamsPlayersIDs(){
        List<Game> activeGames = getActiveGames();
        return getIDsForPlayers(activeGames,TeamNumber.SECOND);
    }

    /**
     * For choosing first or second team
     */
    private enum TeamNumber{
        FIRST,SECOND
    }

    /**
     * For getting ids of every player at team, for example:
     * [[68,72],[69,80,81]] - 68,72 players at first(or second) team in game with index [0]
     * 69,80,81 - players at first(or second) team in in game with index [1] ...
     * WARNING - order is important: if we have games with ids [1,5] then players for first(or second) team
     * for game with id 1 - 68,72, for game with id 5 - 69,80,81 and so on
     * @param games the active games
     * @param teamNumber the number of team (first or second)
     * @return json array if players
     */
    private static String getIDsForPlayers(List<Game> games, TeamNumber teamNumber){
        StringBuilder stringBuilder = new StringBuilder("[");// [ - start element
        for(Game game:games){
            StringBuilder playersBuilder = new StringBuilder("[");
            Team team;
            switch (teamNumber){
                case FIRST:
                    team = game.getFirstTeam();
                    for(Player player:team.getPlayers()){
                        playersBuilder.append(player.getId()+",");
                    }
                    playersBuilder.append("]");
                    playersBuilder.replace(playersBuilder.length()-2,playersBuilder.length()-1,"");
                    break;
                case SECOND:
                    team = game.getSecondTeam();
                    for(Player player:team.getPlayers()){
                        playersBuilder.append(player.getId()+",");
                    }
                    playersBuilder.append("]");
                    playersBuilder.replace(playersBuilder.length()-2,playersBuilder.length()-1,"");
                    break;
            }
            stringBuilder.append(playersBuilder+",");//append players ids array to main array
        }
        stringBuilder.append("]");// ] end element
        stringBuilder.replace(stringBuilder.length()-2,stringBuilder.length()-1,"");//deleting last comma
        return stringBuilder.toString();
    }

    /**
     * For getting ids for all active games
     * @param games the active games
     * @return json array : [1,5,8] - teams with ids 1, 5, 8
     */
    private static String getIDsForAllGames(List<Game> games){
        StringBuilder stringBuilder = new StringBuilder("[");
        for(Game game:games){
            stringBuilder.append(game.getId()+",");
        }
        stringBuilder.append("]");
        stringBuilder.replace(stringBuilder.length()-2,stringBuilder.length()-1,"");
        return stringBuilder.toString();
    }

    /**
     * For getting ids for teams
     * @param games the active games
     * @param teamNumber the team number: FIRST or SECOND
     * @return
     */
    private static String getIDsForTeams(List<Game> games, TeamNumber teamNumber){
        StringBuilder stringBuilder = new StringBuilder("[");
        for(Game game:games){
            switch (teamNumber){
                case FIRST:
                    stringBuilder.append(game.getFirstTeam().getId()+",");
                    break;
                case SECOND:
                    stringBuilder.append(game.getSecondTeam().getId()+",");
                    break;
            }
        }
        stringBuilder.append("]");
        stringBuilder.replace(stringBuilder.length()-2,stringBuilder.length()-1,"");
        return  stringBuilder.toString();
    }

    /**
     All next classes are using only for JSON parsing!
     DO NOT WRITE ANYTHING AFTER THEM! (and in them)
     */

    /**
     * Represents few bets on one game.
     * Here must be minimum one single bet:
     * BetOnResult OR BetOnScore OR BetOnScore (strictly on of them and only one!)
     * AND also cant be many bets on players (from 0 to total count of players)
     */
    static class OneGameBet{
        /*
        Current gama ID
         */
        private int gameId;
        /*
        First team ID
         */
        private int firstTeamId;
        /*
        Second team ID
         */
        private int secondTeamId;
        /*
        Single bet on result
         */
        private BetOnResult betResult;
        /*
        Single bet on score
         */
        private BetOnScore betScore;
        /*
        Single bet on total goals sum
         */
        private BetOnTotalGoals betTotalGoals;
        /*
        Bets on players from first or second teams
        Size will be from 0 to total count of players from both teams
         */
        private List<PlayerData> betPlayer;

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public int getFirstTeamId() {
            return firstTeamId;
        }

        public void setFirstTeamId(int firstTeamId) {
            this.firstTeamId = firstTeamId;
        }

        public int getSecondTeamId() {
            return secondTeamId;
        }

        public void setSecondTeamId(int secondTeamId) {
            this.secondTeamId = secondTeamId;
        }

        public BetOnResult getBetResult() {
            return betResult;
        }

        public void setBetResult(BetOnResult betResult) {
            this.betResult = betResult;
        }

        public BetOnScore getBetScore() {
            return betScore;
        }

        public void setBetScore(BetOnScore betScore) {
            this.betScore = betScore;
        }

        public BetOnTotalGoals getBetTotalGoals() {
            return betTotalGoals;
        }

        public void setBetTotalGoals(BetOnTotalGoals betTotalGoals) {
            this.betTotalGoals = betTotalGoals;
        }

        public List<PlayerData> getBetPlayer() {
            return betPlayer;
        }

        public void setBetPlayer(List<PlayerData> betPlayer) {
            this.betPlayer = betPlayer;
        }

        @Override
        public String toString() {
            return "OneGameBet{" +
                    "gameId=" + gameId +
                    ", firstTeamId=" + firstTeamId +
                    ", secondTeamId=" + secondTeamId +
                    ", betResult=" + betResult +
                    ", betScore=" + betScore +
                    ", betTotalGoals=" + betTotalGoals +
                    ", betPlayer=" + betPlayer +
                    '}';
        }
    }

    /**
     * Represents player, using only for json parsing
     * (as other classes at this package)
     */
    static class PlayerData{
        /*
        ID of player at DB
         */
        private int playerId;
        /*
        Current coefficient on player
         */
        private double playerCoefficient;

        public int getPlayerId() {
            return playerId;
        }

        public void setPlayerId(int playerId) {
            this.playerId = playerId;
        }

        public double getPlayerCoefficient() {
            return playerCoefficient;
        }

        public void setPlayerCoefficient(double playerCoefficient) {
            this.playerCoefficient = playerCoefficient;
        }

        @Override
        public String toString() {
            return "PlayerData{" +
                    "playerId=" + playerId +
                    ", playerCoefficient=" + playerCoefficient +
                    '}';
        }
    }

    /**
     * For bet on total goals sum
     */
    static class BetOnTotalGoals{
        /*
        Total goals sum (can be 0 or more)
         */
        private int totalGoalsValue;
        /*
        Coefficient for one goal
         */
        private double totalGoalsCoefficient;

        public int getTotalGoalsValue() {
            return totalGoalsValue;
        }

        public void setTotalGoalsValue(int totalGoalsValue) {
            this.totalGoalsValue = totalGoalsValue;
        }

        public double getTotalGoalsCoefficient() {
            return totalGoalsCoefficient;
        }

        public void setTotalGoalsCoefficient(double totalGoalsCoefficient) {
            this.totalGoalsCoefficient = totalGoalsCoefficient;
        }

        @Override
        public String toString() {
            return "BetOnTotalGoals{" +
                    "totalGoalsValue=" + totalGoalsValue +
                    ", totalGoalsCoefficient=" + totalGoalsCoefficient +
                    '}';
        }
    }

    /**
     * For bet on game score
     */
    static class BetOnScore{
        /*
        Coefficient for counting award sum(not using here, but necessary at DB)
         */
        private double startCoefficient;
        /*
        First team goals count
         */
        private int firstTeamScore;
        /*
        Second team goals count
         */
        private int secondTeamScore;

        public double getStartCoefficient() {
            return startCoefficient;
        }

        public void setStartCoefficient(double startCoefficient) {
            this.startCoefficient = startCoefficient;
        }

        public int getFirstTeamScore() {
            return firstTeamScore;
        }

        public void setFirstTeamScore(int firstTeamScore) {
            this.firstTeamScore = firstTeamScore;
        }

        public int getSecondTeamScore() {
            return secondTeamScore;
        }

        public void setSecondTeamScore(int secondTeamScore) {
            this.secondTeamScore = secondTeamScore;
        }

        @Override
        public String toString() {
            return "BetOnScore{" +
                    "startCoefficient=" + startCoefficient +
                    ", firstTeamScore=" + firstTeamScore +
                    ", secondTeamScore=" + secondTeamScore +
                    '}';
        }
    }

    /**
     * For bet on result
     */
    static class BetOnResult{
        /*
        Type of result
         */
        private BetResultType betResultType;
        /*
        Current coefficient (only for DB)
         */
        private double coefficient;

        public BetResultType getBetResultType() {
            return betResultType;
        }

        public void setBetResultType(BetResultType betResultType) {
            this.betResultType = betResultType;
        }

        public double getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(double coefficient) {
            this.coefficient = coefficient;
        }

        @Override
        public String toString() {
            return "BetOnResult{" +
                    "betResultType=" + betResultType +
                    ", coefficient=" + coefficient +
                    '}';
        }

        /**
         * Represents result types
         * @see com.epam.javalab13.model.bet.Result (maybe there are some comments)
         */
        enum BetResultType{
            C1, C2, CX, C1X, CX2, C12
        }
    }

    /**
     * For one client bet (not SingleBet)
     */
    static class ClientBet{
        /*
        Count of bets (if equals 1 - will be SINGLE bet, more - MULTIPLE bet)
         */
        private int betsCount;
        /*
        For bets on one game
         */
        private List<OneGameBet> oneGameBets;

        public int getBetsCount() {
            return betsCount;
        }

        public void setBetsCount(int betsCount) {
            this.betsCount = betsCount;
        }

        public List<OneGameBet> getOneGameBets() {
            return oneGameBets;
        }

        public void setOneGameBets(List<OneGameBet> oneGameBets) {
            this.oneGameBets = oneGameBets;
        }

        @Override
        public String toString() {
            return "ClientBet{" +
                    "betsCount=" + betsCount +
                    ", oneGameBets=" + oneGameBets +
                    '}';
        }
    }
}
