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
        System.out.println(uri);

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if (login!= null) {
            UserService service = new UserService();
            User user = service.getUserByLogin(login);

            switch (user.getRole()){
                case CLIENT:
                    if("/PayKick/client".equals(uri)) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect("/PayKick/home");
                    }
                    break;
                case BOOKMAKER:
                    if("/PayKick/bookmaker".equals(uri)) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect("/PayKick/home");
                    }
                    break;
                case ADMIN:
                    if("/PayKick/admin".equals(uri)) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect("/PayKick/home");
                    }
                    break;
                case BOSS:
                    if("/PayKick/boss".equals(uri)) {
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect("/PayKick/home");
                    }
                    break;
                default:
                    response.sendRedirect("/PayKick/home");
                    break;
            }
        }else {
            response.sendRedirect("/PayKick/home");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
