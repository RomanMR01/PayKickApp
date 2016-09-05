package com.epam.javalab13.servlet.statitstics;

import com.epam.javalab13.service.statistics.TopUsersService;
import com.epam.javalab13.service.statistics.UpcomingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for getting json for Top Users and Upcoming matches tables at home page.
 */
public class HomePageStatisticsServlet  extends HttpServlet{
    /*
    Services for get JSON of top ten users and upcoming matches
     */
    private TopUsersService topUsersService = new TopUsersService();
    private UpcomingMatchesService upcomingMatchesService = new UpcomingMatchesService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");//Can be 'topUsers' or 'newMatches'
        resp.setCharacterEncoding("UTF-8");


        /*
        Write response according to the type of request parameter
         */
        if("topUsers".equals(type)){
            resp.getWriter().write(topUsersService.getTopTenUsers());

        }else if("newMatches".equals(type)){
            resp.getWriter().write(upcomingMatchesService.getFirstTenMatches());
        }

    }
}
