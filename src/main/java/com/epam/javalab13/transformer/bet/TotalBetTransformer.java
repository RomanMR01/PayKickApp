package com.epam.javalab13.transformer.bet;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.bet.Status;
import com.epam.javalab13.model.bet.TotalBet;
import com.epam.javalab13.model.bet.Type;
import com.epam.javalab13.transformer.Transformer;

import com.epam.javalab13.dao.bet.SingleBetDAO;
import com.epam.javalab13.model.bet.SingleBet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TotalBetTransformer implements Transformer<TotalBet> {
    @Override
    public TotalBet getOne(ResultSet rs) throws SQLException {
        TotalBet totalBet = null;

        while (rs.next()) {
            int u_id = rs.getInt("user_id");
            User u = new User();
            u.setId(u_id);

            User user = new UserDAO().getUser(u, UserDAO.GetOneUserType.ID);

            totalBet = new TotalBet(rs.getInt("id"),
                    user,
                    Type.valueOf(rs.getString("type")),
                    rs.getInt("amount"),
                    rs.getTimestamp("date"),
                    rs.getDouble("award"),
                    Status.valueOf(rs.getString("status"))
            );
        }

        return totalBet;
    }

    @Override
    public List<TotalBet> getAll(ResultSet rs) throws SQLException {
        List<TotalBet> totalBets = new ArrayList<>();
        TotalBet totalBet = null;

        while (rs.next()) {

            int u_id = rs.getInt("user_id");
            User u = new User();
            u.setId(u_id);

            User user = new UserDAO().getUser(u, UserDAO.GetOneUserType.ID);
            totalBet = new TotalBet(rs.getInt("id"),
                    user,
                    Type.valueOf(rs.getString("type")),
                    rs.getInt("amount"),
                    rs.getTimestamp("date"),
                    rs.getDouble("award"),
                    Status.valueOf(rs.getString("status"))
            );

            totalBets.add(totalBet);
        }

        return totalBets;
    }

    public List<TotalBet> getAllWithSingleBets(ResultSet rs) throws SQLException {
        List<TotalBet> totalBets = new ArrayList<>();
        TotalBet totalBet = null;
        SingleBetDAO singleBetDAO=new SingleBetDAO();
        while (rs.next()) {

            int u_id = rs.getInt("user_id");
            User u = new User();
            u.setId(u_id);

            User user = new UserDAO().getUser(u, UserDAO.GetOneUserType.ID);
            totalBet = new TotalBet(rs.getInt("id"),
                    user,
                    Type.valueOf(rs.getString("type")),
                    rs.getInt("amount"),
                    rs.getTimestamp("date"),
                    rs.getDouble("award"),
                    Status.valueOf(rs.getString("status"))
            );
            List<SingleBet> singleBets = singleBetDAO.getFullyInitializedSingleBetsByTotalBet(totalBet);
            totalBet.setSingleBets(singleBets);
            totalBets.add(totalBet);
        }

        return totalBets;
    }
}
