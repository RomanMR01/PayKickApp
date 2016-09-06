package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.model.Language;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 05.09.2016.
 */
public class UserService {
    UserDAO userDAO=new UserDAO();

    public void addUser(User user){
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByLogin(String login){
        User u = new User();
        u.setLogin(login);

        User user = null;

        try {
            user = userDAO.getUser(u, UserDAO.GetOneUserType.LOGIN);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public String getUserLanguage(String login){
        User u = new User();
        u.setLogin(login);

        String lang = "en_EN";

        User user = getUserByLogin(login);
            if(user!=null && user.getLanguage()!=null){
                lang = user.getLanguage().toString();
            }

        return lang;
    }

    public void changeUserLanguage(String login,String lang){

        User u = getUserByLogin(login);
        u.setLanguage(Language.valueOf(lang));
        try {
            userDAO.updateUser(u, UserDAO.UpdateUserType.LANGUAGE);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void setUserBan(int userId){
        User user = new User();
        user.setId(userId);
        user.setBanned(true);

        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.BAN);

        } catch (SQLException e) {
         e.getStackTrace();
        }
    }


    public void resetUserBan(int userId){
        User user = new User();
        user.setId(userId);
        user.setBanned(false);

        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.BAN);

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }


    public void changeUserRole(int userId,String role){
        User user = new User();
        user.setId(userId);
        user.setRole(Role.valueOf(role));
        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.ROLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllEmails(){
        List<String> emails = new ArrayList<>();

        List<User> allUsers = null;
        try {
             allUsers = userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        for(User user:allUsers){
            emails.add(user.getEmail());
        }

        return emails;
    }
}