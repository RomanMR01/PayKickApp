package com.epam.javalab13.service;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.UserDAO.GetType;
import com.epam.javalab13.dao.bet.TotalBetDAO;
import com.epam.javalab13.dao.bet.TotalBetDAO.GetTotalBetsType;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.GameDAO.Type;
import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.dao.game.TeamDAO;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import org.apache.log4j.Logger;

import java.util.List;

public class PaginationService {

    private UserDAO userDao;
    private GameDAO gameDao;
    private TeamDAO teamDao;

    private static final Logger logger = Logger.getLogger(PaginationService.class);
    private static final int DEFAULT_ITEMS_ON_PAGE = 10;


    public int getPagesForUsers(String type, String page,String itemsOnPage, List<User> users) {
        List<User> allUsers =null;
        int pages=1;
        try {
            int pageNumber=page==null?1:Integer.valueOf(page);
            int items=itemsOnPage==null?DEFAULT_ITEMS_ON_PAGE:Integer.valueOf(itemsOnPage);
            GetType enumType=type==null?GetType.ALL:GetType.valueOf(type);
            userDao= new UserDAO();
            allUsers = userDao.getAllUsersByType(enumType);
            pages=1+(allUsers.size()-1)/items;
            int start =allUsers.size()<items*(pageNumber-1)?(allUsers.size()-allUsers.size()/items):items*(pageNumber-1);
            int end = allUsers.size()<items*pageNumber?allUsers.size():items*pageNumber;
            users.addAll(allUsers.subList(start, end));
        } catch (Exception e) {
            logger.error("failed to find users by type = " + type, e);
            return -1;
        }
        return pages;
    }

    public int getPagesForGames(String type, String page,String itemsOnPage, List<Game> games) {
        List<Game> allGames =null;
        int pages=1;
        try {
            int pageNumber=page==null||page.equals("")?1:Integer.valueOf(page);
            int items=itemsOnPage==null||itemsOnPage.equals("")?DEFAULT_ITEMS_ON_PAGE:Integer.valueOf(itemsOnPage);
            Type enumType=type==null||type.equals("")?Type.ACTIVE:Type.valueOf(type);
            gameDao=new GameDAO();
            allGames = gameDao.getGamesByType(enumType);

            pages=1+(allGames.size()-1)/items;
            int start =allGames.size()<items*(pageNumber-1)?(allGames.size()-allGames.size()/items):items*(pageNumber-1);
            int end = allGames.size()<items*pageNumber?allGames.size():items*pageNumber;
            games.addAll(allGames.subList(start, end));
        } catch (Exception e) {
            logger.error("failed to find games by type = " + type, e);
            return -1;
        }
        return pages;
    }

    public int getPagesForTeams(String page,String itemsOnPage, List<Team> teams) {
        List<Team> allTeams =null;
        int pages=1;
        try {
            int pageNumber=page==null||page.equals("")?1:Integer.valueOf(page);
            int items=itemsOnPage==null||itemsOnPage.equals("")?DEFAULT_ITEMS_ON_PAGE:Integer.valueOf(itemsOnPage);
            teamDao=new TeamDAO();
            allTeams = teamDao.getAllTeamsWithPlayers();

            pages=1+(allTeams.size()-1)/items;
            int start =allTeams.size()<items*(pageNumber-1)?(allTeams.size()-allTeams.size()/items):items*(pageNumber-1);
            int end = allTeams.size()<items*pageNumber?allTeams.size():items*pageNumber;
            teams.addAll(allTeams.subList(start, end));
        } catch (Exception e) {
            logger.error("failed to find all teams with players", e);
            return -1;
        }
        return pages;
    }

    public int getPagesForGamesByBookmaker(String type, String page, String itemsOnPage, List<Game> games, User bookmaker) {
        List<Game> allGames = null;
        int pages = 1;
        try {
            int pageNumber = page == null || page.equals("") ? 1 : Integer.valueOf(page);
            int items = itemsOnPage == null || itemsOnPage.equals("") ? DEFAULT_ITEMS_ON_PAGE
                    : Integer.valueOf(itemsOnPage);
            Type enumType = type == null || type.equals("") ? Type.ACTIVE : Type.valueOf(type);
            gameDao = new GameDAO();
            if(bookmaker.getRole().equals(Role.BOOKMAKER)){
                allGames = gameDao.getGamesByBookmakerAndStatus(bookmaker, enumType);
            }
            pages = 1 + (allGames.size() - 1) / items;
            int start = allGames.size() < items * (pageNumber - 1) ? (allGames.size() - allGames.size() / items)
                    : items * (pageNumber - 1);
            int end = allGames.size() < items * pageNumber ? allGames.size() : items * pageNumber;
            games.addAll(allGames.subList(start, end));
        } catch (Exception e) {
            logger.error("failed to find games by type = " + type, e);
            return -1;
        }
        return pages;
    }

    //NEW

    public int getPagesForClientGames(String page, String itemsOnPage, List<Game> games) {
        List<Game> allGames = null;
        int pages = 1;
        Type enumType = Type.ACTIVE;
        try {
            int pageNumber = page == null || page.equals("") ? 1 : Integer.valueOf(page);
            int items = itemsOnPage == null || itemsOnPage.equals("") ? DEFAULT_ITEMS_ON_PAGE
                    : Integer.valueOf(itemsOnPage);

            gameDao = new GameDAO();

            allGames = gameDao.getGamesWithCoefficientsByType(enumType);

            pages = 1 + (allGames.size() - 1) / items;
            int start = allGames.size() < items * (pageNumber - 1) ? (allGames.size() - allGames.size() / items)
                    : items * (pageNumber - 1);
            int end = allGames.size() < items * pageNumber ? allGames.size() : items * pageNumber;
            games.addAll(allGames.subList(start, end));
            return pages;
        } catch (Exception e) {
            logger.error("failed to find games for client", e);
            return -1;
        }
    }

    public int getPagesForTotalBetsByClient(String type, String page, String itemsOnPage, List<TotalBet> totalBets,User client) {
        List<TotalBet> allTotalBets=null;
        GetTotalBetsType enumType = null;
        int pages=1;
        try {
            int pageNumber = page == null || page.equals("") ? 1 : Integer.valueOf(page);
            int items = itemsOnPage == null || itemsOnPage.equals("") ? DEFAULT_ITEMS_ON_PAGE
                    : Integer.valueOf(itemsOnPage);

            enumType=GetTotalBetsType.valueOf(type);

            TotalBetDAO totalBetDAO = new TotalBetDAO();
            allTotalBets = totalBetDAO.getTotalBetsWithSingeBetsForUser(enumType, client);
            pages = 1 + (allTotalBets.size() - 1) / items;
            int start = allTotalBets.size() < items * (pageNumber - 1) ? (allTotalBets.size() - allTotalBets.size() / items)
                    : items * (pageNumber - 1);
            int end = allTotalBets.size() < items * pageNumber ? allTotalBets.size() : items * pageNumber;
            totalBets.addAll(allTotalBets.subList(start, end));
            return pages;
        } catch (Exception e) {
            logger.error("failed to find games for client", e);
            return -1;
        }
    }

    public int getPaagesForTeams(String page, String itemsOnPage, List<Team> teams) {
        List<Team> allTeams;
        int pages=1;
        try {
            int pageNumber = page == null || page.equals("") ? 1 : Integer.valueOf(page);
            int items = itemsOnPage == null || itemsOnPage.equals("") ? DEFAULT_ITEMS_ON_PAGE
                    : Integer.valueOf(itemsOnPage);
            TeamDAO teamDao = new TeamDAO();
            allTeams = teamDao.getAllTeams();
            pages = 1 + (allTeams.size() - 1) / items;
            int start = allTeams.size() < items * (pageNumber - 1) ? (allTeams.size() - allTeams.size() / items)
                    : items * (pageNumber - 1);
            int end = allTeams.size() < items * pageNumber ? allTeams.size() : items * pageNumber;
            teams.addAll(allTeams.subList(start, end));
            return pages;
        } catch (Exception e) {
            logger.error("Can't get pages for teams:", e);
            return -1;
        }
    }

    public int getPagesForPlayers(String page, String itemsOnPage, List<Player> players) {
        List<Player> allPlayers;
        int pages=1;
        try {
            int pageNumber = page == null || page.equals("") ? 1 : Integer.valueOf(page);
            int items = itemsOnPage == null || itemsOnPage.equals("") ? DEFAULT_ITEMS_ON_PAGE
                    : Integer.valueOf(itemsOnPage);
            PlayerDAO playerDao = new PlayerDAO();
            allPlayers = playerDao.getAllPlayers();
            pages = 1 + (allPlayers.size() - 1) / items;
            int start = allPlayers.size() < items * (pageNumber - 1) ? (allPlayers.size() - allPlayers.size() / items)
                    : items * (pageNumber - 1);
            int end = allPlayers.size() < items * pageNumber ? allPlayers.size() : items * pageNumber;
            players.addAll(allPlayers.subList(start, end));
            return pages;
        } catch (Exception e) {
            logger.error("Can't get pages for players:", e);
            return -1;
        }
    }


}
