package com.epam.javalab13.transformer;

import com.epam.javalab13.model.Gender;
import com.epam.javalab13.model.Language;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTransformer implements Transformer<User> {
    @Override
    public User getOne(ResultSet rs) throws SQLException {
        User user = null;
        while (rs.next()) {
            boolean bannStatus = false;

            if(1 == rs.getInt("is_banned")){
                bannStatus = true;
            }
            user = new User(rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    Gender.valueOf(rs.getString("gender")),
                    rs.getString("email"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getDouble("balance"),
                    rs.getString("avatar_url"),
                    Role.valueOf(rs.getString("role")),
                    Language.valueOf(rs.getString("language")),
                    bannStatus
            );
        }

        return user;
    }

    @Override
    public List<User> getAll(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        User user = null;

        while (rs.next()) {

            boolean bannStatus = false;

            if(1 == rs.getInt("is_banned")){
                bannStatus = true;
            }

            user = new User(rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    Gender.valueOf(rs.getString("gender")),
                    rs.getString("email"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getDouble("balance"),
                    rs.getString("avatar_url"),
                    Role.valueOf(rs.getString("role")),
                    Language.valueOf(rs.getString("language")),
                    bannStatus
            );

            users.add(user);
        }

        return users;
    }
}
