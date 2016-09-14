package com.epam.javalab13.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.PaginationService;
import com.epam.javalab13.service.game.PlayerService;

/**
 * Created by Vikno on 9/7/2016.
 */
public class TeamsDispatcher extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PaginationService paginationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //START PAGINATION
        String page = request.getParameter("page");
        String itemsOnPage = request.getParameter("itemsOnPage");
        int pages = 0;
        List<Team> teams = new ArrayList<>();
        page = page == null ? "1" : page;
        itemsOnPage = itemsOnPage == null ? "10" : itemsOnPage;
        paginationService = new PaginationService();
        pages = paginationService.getPagesForTeams(page, itemsOnPage, teams);

        int intPage = 0;
        try {
            intPage = Integer.valueOf(page);
            intPage = intPage > pages ? pages : Integer.valueOf(page);
        }catch (Exception e){
            response.sendRedirect(getServletContext().getContextPath() + "/admin/teams");
            return;
        }


        if(pages==-1){
            response.sendRedirect(getServletContext().getContextPath() + "/admin/teams");
            return;
        }else {
            request.setAttribute("pages", pages);
            request.setAttribute("teams", teams);
            request.setAttribute("page", intPage);
            request.setAttribute("itemsOnPage", itemsOnPage);
            //END PAGINATION

            PlayerService playerService = new PlayerService();
            List<Player> allPlayers = playerService.getAllPlayers();
            request.setAttribute("allPlayers", allPlayers);

            request.getRequestDispatcher("/WEB-INF/view/admin/teams.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
