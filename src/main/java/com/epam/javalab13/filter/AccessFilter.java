package com.epam.javalab13.filter;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.game.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for control access to pages
 */
public class AccessFilter implements Filter {

    /*
    Are three types of access:
    admin:can manipulate races,jockeys,users and view all statistics;
    bookmaker:can set coefficients for races and take difference bets from users;
    client:can bet on different races
    and
    BOSS
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String path = request.getServletContext().getContextPath();


        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if (login!= null) {
            UserService service = new UserService();
            User user = service.getUserByLogin(login);

            switch (user.getRole()){
                case CLIENT:
                    if(uri.contains("client")) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect(path + "/home");
                    }
                    break;
                case BOOKMAKER:
                    if(uri.contains("bookmaker")) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect(path + "/home");
                    }
                    break;
                case ADMIN:
                    if(uri.contains("admin")) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect(path + "/home");
                    }
                    break;
                case BOSS:
                    if(uri.contains("boss")) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect(path + "/home");
                    }
                    break;
                default:
                    response.sendRedirect(path + "/home");
                    break;
            }
        }else {
            response.sendRedirect(path + "/home");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
