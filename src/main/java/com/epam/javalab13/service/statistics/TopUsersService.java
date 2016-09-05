package com.epam.javalab13.service.statistics;

import com.epam.javalab13.dao.bet.TotalBetDAO;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.statistics.TopUser;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

/**
 * Service for getting top users by them awards in JSON format
 */
public class TopUsersService {

    private TotalBetDAO totalBetDAO = new TotalBetDAO();
    private static Logger logger = Logger.getLogger(TopUsersService.class);

    /**
     * Gets top five users by them awards int JSON format
     * @return json the String represents of JSON array
     */
    public String getTopTenUsers(){

        /*
        Getting all total bets
         */
        List<TotalBet> allBets;
        try {
            allBets = totalBetDAO.getAllTotalBets(TotalBetDAO.GetTotalBetsType.ALL);
        } catch (SQLException e) {
            logger.warn("Exception while getting all TotalBets:",e);
            return "";
        }


        System.out.println(allBets.size());
        /*
        Getting only clients
         */
        List<TotalBet> onlyClients = new ArrayList<>();

        for(TotalBet totalBet:allBets){
            if(Role.CLIENT == totalBet.getUser().getRole()){
                onlyClients.add(totalBet);
            }
        }

        allBets = onlyClients;

        System.out.println(allBets.size());


        /*
        Getting all unique users
         */
        Set<User> uniqueUsers = new HashSet<>();
        for(TotalBet totalBet:allBets){
            uniqueUsers.add(totalBet.getUser());
        }

        /*
        Initializing top users by user login (nickname)
         */
        List<TopUser> topUsers = new ArrayList<>();
        for(User user:uniqueUsers){
            TopUser topUser = new TopUser();
            topUser.setLogin(user.getLogin());
            topUsers.add(topUser);
        }

        /*
        Calculation all awards for every unique user.
         */
        for(User user:uniqueUsers){
            for(TotalBet totalBet:allBets){
                /*
                If user are in total bet then we search him in TopUser by
                login( login is unique) and calculate him award:
                if bet status is WON then we add AWARD to total sum;
                if bet status is LOST then we remove AMOUNT from total sum.
                PS:AMOUNT - user deposit for game; AWARD - possible sum that user can win.
                 */
                if(totalBet.getUser().equals(user)){
                    for(TopUser topUser:topUsers){
                        if(topUser.getLogin().equals(user.getLogin())){
                            double awardSum = topUser.getAwardSum();
                            switch (totalBet.getStatus()){
                                case WON:
                                    awardSum+=totalBet.getAward();
                                    break;
                                case LOST:
                                    awardSum-=totalBet.getAmount();
                                    break;
                            }
                            topUser.setAwardSum(awardSum);
                        }
                    }
                }
            }
        }

        /*
        Sorting all bets by DATE in descending order
         */
        Collections.sort(allBets, new Comparator<TotalBet>() {
            @Override
            public int compare(TotalBet o1, TotalBet o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        /*
        List of user last bets by date
         */
        List<TotalBet> lastBets = new ArrayList<>();

        /*
        Initializing lastBets from allBets.
        If we see the first time such user, we add him to lastBets list
        and break at that iteration and go to another user.
         */
        for(User user:uniqueUsers){
            for(TotalBet totalBet:allBets){
                if(totalBet.getUser().equals(user)){
                    lastBets.add(totalBet);
                    break;
                }
            }
        }


        /*
        Sorting last bets by DATE in descending order
         */
        Collections.sort(lastBets, new Comparator<TotalBet>() {
            @Override
            public int compare(TotalBet o1, TotalBet o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        /*
        Final filling of topUsers list.
         */
        for(TopUser topUser:topUsers){
            for(TotalBet bet:lastBets){
                if(topUser.getLogin().equals(bet.getUser().getLogin())){
                    switch (bet.getStatus()){
                        case WON:
                            topUser.setLastWin(bet.getAward());
                            break;
                        case LOST:
                            topUser.setLastWin(-bet.getAmount());
                            break;
                    }
                }
            }
        }


        /*
        Sorting top users by award sum in descending order
         */
        Collections.sort(topUsers, new Comparator<TopUser>() {
            @Override
            public int compare(TopUser o1, TopUser o2) {
                return (int)(o2.getAwardSum()-o1.getAwardSum());
            }
        });


        /*
        Getting only first ten top users if in topUsers are more
         */
        if(topUsers.size()>10){
            topUsers = topUsers.subList(0,10);
        }

        /*
        Converting topUsers list to JSON array
         */
        Gson gson = new Gson();
        String json = gson.toJson(topUsers);


        return json;
    }
}
