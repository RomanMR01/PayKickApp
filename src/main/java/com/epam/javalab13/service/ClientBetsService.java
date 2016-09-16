package com.epam.javalab13.service;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * For Client bets
 */
public class ClientBetsService {

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
}
