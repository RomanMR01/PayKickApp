package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.service.game.PlayerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vikno on 9/11/2016.
 */
public class RemovePlayerFromTeamServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerID = req.getParameter("playerID");
        if(playerID!=null){
            PlayerService playerService = new PlayerService();
            playerService.removePlayerFromTeam(Integer.parseInt(playerID));
        }
    }
}
