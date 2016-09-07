package com.epam.javalab13.service.statistics;

import com.epam.javalab13.dao.bet.TotalBetDAO;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.ResultCoefficientDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.model.statistics.UpcomingMatch;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Service for getting first ten games in JSON format
 */
public class UpcomingMatchesService {


    private GameDAO gameDAO = new GameDAO();
    private ResultCoefficientDAO resultCoefficientDAO = new ResultCoefficientDAO();
    private static Logger logger = Logger.getLogger(UpcomingMatchesService.class);

    /**
     * Gets top ten games by them date int JSON format
     * @return json the String represents of JSON array
     */
    public String getFirstTenMatches(){


        /*
        Getting all games with status ACTIVE
         */
        List<Game> activeGames = null;
        try {
            activeGames =gameDAO.getGamesByStatus(GameDAO.GetGamesType.ACTIVE);
        } catch (SQLException e) {
            logger.warn("Exception while getting all active matches:",e);
            return "";
        }

        /*
        Sorting active games by date in descending order
         */
        Collections.sort(activeGames, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });


        /*
        Getting first ten games
         */
        List<Game> firstTenGames = new ArrayList<>();
        if(activeGames.size()>10){
            firstTenGames = activeGames.subList(0,10);
        }else{
            firstTenGames = activeGames;
        }

        /*
        Upcoming matches represents game title, location, date
        and coefficients on result.
         */
        List<UpcomingMatch> upcomingMatches = new ArrayList<>();

        /*
        Initialization of upcomingMatches list
         */
        for(Game game:firstTenGames){

            /*
            Getting all result coefficients for one game
             */
            List<ResultCoefficient> resultCoefficients = null;
            try {
                resultCoefficients = resultCoefficientDAO.getResultCoefficientsByGame(game);
            } catch (SQLException e) {
                logger.warn("Exception while getting result coefficients for game " + game.getId() + ":",e);
                return "";
            }

            /*
            Initializing upcoming match
             */
            UpcomingMatch upcomingMatch = new UpcomingMatch();
            upcomingMatch.setTitle(game.getTitle());
            upcomingMatch.setFirstTeam(game.getFirstTeam().getName());
            upcomingMatch.setSecondTeam(game.getSecondTeam().getName());

            /*
            Get coefficients for every result type
             */
            for(ResultCoefficient resultCoefficient:resultCoefficients){
                switch (resultCoefficient.getResult()){
                    case C1:
                        upcomingMatch.setC1(resultCoefficient.getCoefficient());
                        break;
                    case C2:
                        upcomingMatch.setC2(resultCoefficient.getCoefficient());
                        break;
                    case CX:
                        upcomingMatch.setCX(resultCoefficient.getCoefficient());
                        break;
                    case C1X:
                        upcomingMatch.setC1X(resultCoefficient.getCoefficient());
                        break;
                    case CX2:
                        upcomingMatch.setCX2(resultCoefficient.getCoefficient());
                        break;
                    case C12:
                        upcomingMatch.setC12(resultCoefficient.getCoefficient());
                        break;
                }
            }
            upcomingMatches.add(upcomingMatch);
        }

        /*
        Converting UpcomingMatches list to JSON array
         */
        Gson gson = new Gson();
        String json = gson.toJson(upcomingMatches);

        return json;
    }
}
