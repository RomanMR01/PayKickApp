package com.epam.javalab13.servlet.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.service.PaginationService;
import com.epam.javalab13.service.game.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikno on 9/14/2016.
 */
public class ClientBetsDispatcher extends HttpServlet {

    private PaginationService paginationService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        String currentLang = request.getParameter("language");

        if(login!=null && currentLang!=null) {
            UserService service = new UserService();
            service.changeUserLanguage(login,currentLang);
            session.setAttribute("language",currentLang);
        }

        String type = request.getParameter("type");
        String page = request.getParameter("page");
        String itemsOnPage=request.getParameter("itemsOnPage");
        int pages = 0;
        type=type==null?"ACTIVE":type;
        page=page==null?"1":page;
        itemsOnPage=itemsOnPage==null?"10":itemsOnPage;
        List<TotalBet> totalBets = new ArrayList<>();
        User client = new UserService().getUserByLogin(login);
        paginationService=new PaginationService();
        pages=paginationService.getPagesForTotalBetsByClient(type,page,itemsOnPage,totalBets, client);

        if(pages==-1){
            resp.sendRedirect(getServletContext().getContextPath() + "/client/bets");
            return;
        }
        int intPage = Integer.valueOf(page);
        intPage=intPage>pages?pages:Integer.valueOf(page);
        request.setAttribute("pages", pages);
        request.setAttribute("totalBets", totalBets);
        request.setAttribute("type", type);
        request.setAttribute("page", intPage);
        request.setAttribute("itemsOnPage", itemsOnPage);
        request.getRequestDispatcher("/WEB-INF/view/client/bets.jsp").forward(request,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}