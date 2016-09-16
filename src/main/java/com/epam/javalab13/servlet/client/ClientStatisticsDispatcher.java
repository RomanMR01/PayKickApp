package com.epam.javalab13.servlet.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.service.PaginationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/14/2016.
 */
public class ClientStatisticsDispatcher extends HttpServlet {
    private PaginationService paginationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String type = request.getParameter("type");
        String page = request.getParameter("page");
        String itemsOnPage = request.getParameter("itemsOnPage");
        int pages = 0;
        type = type == null ? "TEAMS" : type;
        page = page == null ? "1" : page;
        itemsOnPage = itemsOnPage == null ? "10" : itemsOnPage;
        List<Team> teams = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        paginationService = new PaginationService();
        if (type.equals("TEAMS")) {
            pages = paginationService.getPaagesForTeams(page, itemsOnPage, teams);
        } else {
            pages = paginationService.getPagesForPlayers(page, itemsOnPage, players);
        }

        int intPage = Integer.valueOf(page);
        intPage = intPage > pages ? pages : Integer.valueOf(page);
        request.setAttribute("pages", pages);
        request.setAttribute("teams", teams);
        request.setAttribute("players", players);
        request.setAttribute("type", type);
        request.setAttribute("page", intPage);
        request.setAttribute("itemsOnPage", itemsOnPage);
        request.getRequestDispatcher("/WEB-INF/view/client/statistics.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}