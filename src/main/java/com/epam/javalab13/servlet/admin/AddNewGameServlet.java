package com.epam.javalab13.servlet.admin;

import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.game.GameService;
import com.epam.javalab13.service.game.TeamService;
import com.epam.javalab13.service.game.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vikno on 9/10/2016.
 */
public class AddNewGameServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AddNewGameServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameService gameService = new GameService();
        String title = req.getParameter("title");
        String location = req.getParameter("location");
        String stringDate = req.getParameter("date");
        String firstTeamName = req.getParameter("firstTeamName");
        String secondTeamName = req.getParameter("secondTeamName");
        String bookmakerName = req.getParameter("bookmaker");

        resp.setCharacterEncoding("UTF-8");

        if (title == null || location == null || stringDate == null ||
                firstTeamName == null || secondTeamName == null || bookmakerName == null) {
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Some fields are empty!\"}");
        }

        UserService userService = new UserService();
        List<User> userList = userService.getAllUsers();

        //Check if bookmaker exists
        boolean userExists = false;
        for (User user : userList) {
            if (bookmakerName.equals(user.getFullName())) {
                if (user.getRole() != Role.BOOKMAKER) {

                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Not bookmaker!\"}");
                    return;
                } else {
                    userExists = true;
                    break;
                }
            }
        }

        //Searching teams
        boolean firstTeamExist = false;
        boolean secondTeamExist = false;
        List<Team> allTeams = new TeamService().getAllTeams();
        for (Team team : allTeams) {
            if (!firstTeamExist || !secondTeamExist) {
                if (firstTeamName.equals(team.getName())) {
                    firstTeamExist = true;
                } else if (secondTeamName.equals(team.getName())) {
                    secondTeamExist = true;
                }
            } else {
                break;
            }
        }

        if (firstTeamExist && secondTeamExist) {
            if (userExists) {
                Game game = gameService.addNewGame(title, location, stringDate, firstTeamName, secondTeamName, bookmakerName);

                //If game will be null or have id 0 than something going wong at game service (likely date is wrong)
                if (game == null || game.getId() == 0) {
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Incorrect date! Try again!\"}");
                    return;
                } else {
                    logger.info("Admin add new game with id:" + game.getId());
                    resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Game added!\"}");
                    return;
                }
            } else {
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"No such user\"}");
                return;
            }
        } else {
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"No such team or teams\"}");
            return;
        }
    }
}
