package com.epam.javalab13.servlet.client;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.game.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vikno on 9/19/2016.
 */
public class WithdrawMoneyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/client/withdraw.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String)session.getAttribute("login");

        if(login!=null ) {
            UserService service = new UserService();
            User user = service.getUserByLogin(login);

            if(user!=null){
                //Getting amount from page
                String amountSum = req.getParameter("amountSum");
                double amount = Double.parseDouble(amountSum);

                if(amount<1 || amount>1_000_000){
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Error, wrong data!\"}");
                    return;
                }
                //Updating user balance
                double userBalance = user.getBalance();

                //Check if user have enough money
                if((userBalance-amount)<0){
                    resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"You haven't enough money!\"}");
                    return;
                }
                userBalance-=amount;
                service.updateUserBalance(user.getId(),userBalance);

                session.setAttribute("balance", userBalance);

                resp.getWriter().write("{ \"status\": \"OK\",\"message\":\"Withdraw success!\"}");
                return;
            }else{
                resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Error, please try again later!\"}");
                return;
            }
        }else{
            resp.getWriter().write("{ \"status\": \"FAIL\",\"message\":\"Sorry, something going wrong, try again later!\"}");
            return;
        }
    }
}
