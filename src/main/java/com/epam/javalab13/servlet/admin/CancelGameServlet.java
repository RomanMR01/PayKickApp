package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.service.game.GameService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vikno on 9/10/2016.
 */
public class CancelGameServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(CancelGameServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String adminLogin = (String) session.getAttribute("login");

        String gameId = req.getParameter("gameID");
        GameService gameService = new GameService();
        if(gameService.cancelGame(Integer.parseInt(gameId))){
            if(adminLogin!=null) {
                logger.info("Admin " + adminLogin + " has canceled game " + gameId + "!");
            }
        }else{
            if(adminLogin!=null) {
                logger.info("Admin " + adminLogin + " try canceled game " + gameId + "! But can't!");
            }
        }

    }
}
