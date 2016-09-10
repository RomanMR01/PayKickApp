package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.service.game.GameService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vikno on 9/10/2016.
 */
public class AddNewGameServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameService gameService = new GameService();
        String title = req.getParameter("title");
        String location = req.getParameter("location");
        String stringDate = req.getParameter("date");
        String firstTeamName = req.getParameter("firstTeamName");
        String secondTeamName = req.getParameter("secondTeamName");
        String bookmakerName = req.getParameter("bookmaker");

        System.out.println(title);
        System.out.println(location);
        System.out.println(stringDate);
        System.out.println(firstTeamName);
        System.out.println(secondTeamName);
        System.out.println(bookmakerName);

        Game game = gameService.addNewGame(title, location, stringDate, firstTeamName, secondTeamName,bookmakerName);
        System.out.println("game:"  + game);

        if(game==null || game.getId()==0){
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect data! Try again!\"}");
        }else {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Game added!\"}");
        }
    }
}
