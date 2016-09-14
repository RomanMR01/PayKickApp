package com.epam.javalab13.servlet;

import com.epam.javalab13.dao.bet.TotalBetDAO;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.game.TeamService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga on 02.09.2016.
 */
public class ChartServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ChartServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("chart.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TeamService teamService=new TeamService();
        TotalBetDAO totalBetDAO = new TotalBetDAO();

        String chartType=req.getParameter("type");
        Gson gson = new Gson();
        String json="";
        List<Game> games;

        if (chartType!=null){
            switch (chartType){
                case "user_bat":
                    List<TotalBet> allBets;
                    try {
                        User user=new User();
                        if (req.getParameter("id")!=null){
                            user.setId(Integer.valueOf(req.getParameter("id")));
                        }

                        allBets = totalBetDAO.getTotalBetsForUser(TotalBetDAO.GetTotalBetsType.ALL, user);
                        json = gson.toJson(allBets);
                    } catch (SQLException e) {
                        logger.warn("Exception while getting getTotalBetsForUser:",e);
                    }
                    break;
                case "team_win":
                    Team team=new Team();
                    team.setName("Р”РёРЅР°РјРѕ");
                    Team resultTeam=null;

                        resultTeam=teamService.getTeamByName(team);

                    json = gson.toJson(resultTeam);
                    break;
                case "all_bats":
                    List<TotalBet> all;
                    try {
                        all = totalBetDAO.getAllTotalBets(TotalBetDAO.GetTotalBetsType.ALL);
                        Collections.sort(all);
                        json = gson.toJson(all);
                    } catch (SQLException e) {
                        logger.warn("Exception while getting all TotalBets:",e);
                    }
                    break;
                case "bookmaker_games":
                    try {
                        GameDAO gameDAO=new GameDAO();
                        int id=0;
                        if (req.getParameter("id")!=null){
                            id=Integer.valueOf(req.getParameter("id"));
                        }
                        games = gameDAO.getFinishedGamesByBookmaker(id);
                        Game fakeGame=new Game();
                        fakeGame.setId(-1);
                        System.out.println("here");
                        int allProfit=gameDAO.countAllProfit();
                        System.out.println("allProfit"+allProfit);
                        fakeGame.setProfit(gameDAO.countAllProfit());
                        fakeGame.setDate(new Date());
                        games.add(fakeGame);
                        Collections.sort(games);
                        System.out.println("here1");
                        json = gson.toJson(games);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        logger.warn("Exception while getting all TotalBets:",e);
                    }
                    break;
                case "all_games":
                    try {
                        GameDAO gameDAO=new GameDAO();
                        games = gameDAO.getGamesByStatus(GameDAO.GetGamesType.FINISHED);
                        Collections.sort(games);
                        json = gson.toJson(games);
                        System.out.println("json"+json);
                    } catch (SQLException e) {
                        logger.warn("Exception while getting all TotalBets:",e);
                    }
                    break;
                case "bookmaker_bet":
                    User user=new User();
                    if (req.getParameter("id")!=null){
                        user.setId(Integer.valueOf(req.getParameter("id")));
                    }
                    GameDAO gameDAO=new GameDAO();
                    try {
                        games = gameDAO.getGamesByBookmaker(user);
                        json = gson.toJson(games);
                    } catch (SQLException e) {
                        logger.warn("Exception while getting getGamesByBookmaker:",e);
                    }
                    break;
            }
        }
        resp.setContentType("application/json");// set content to json
        resp.getWriter().write(json);
    }
}