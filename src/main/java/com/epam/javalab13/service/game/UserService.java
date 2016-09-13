package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.model.Gender;
import com.epam.javalab13.model.Language;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 05.09.2016.
 */
public class UserService {
    UserDAO userDAO=new UserDAO();
    private static Logger logger = Logger.getLogger(UserService.class);

    public void addUser(User user){
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            logger.error("Can't add new user: " +user,e);
        }
    }

    public void updateUser(int userId, String fullName, int age, Gender gender){
        User user = new User();
        user.setId(userId);
        user.setFullName(fullName);
        user.setAge(age);
        user.setGender(gender);

        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.SQL_NAME_GENDER_AGE);
        } catch (SQLException e) {
            logger.error("Can't update user: " + user,e);
        }
    }

    public List<User> getAllUsers(){
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            logger.error("Can't get all users! ",e);
        }

        return null;
    }

    public List<User> getAllUsersByType(UserDAO.GetType type){
        try {
            return userDAO.getAllUsersByType(type);
        } catch (SQLException e) {
            logger.error("Can't get all users by type:" + type,e);
        }

        return null;
    }


    public User getUserById(int userId){
        User u = new User();
        u.setId(userId);

        User user = null;

        try {
            user = userDAO.getUser(u, UserDAO.GetOneUserType.ID);
        } catch (SQLException e) {
            logger.error("Can't get user by id:" + userId,e);
        }

        return user;
    }

    public User getUserByLogin(String login){
        User u = new User();
        u.setLogin(login);

        User user = null;

        try {
            user = userDAO.getUser(u, UserDAO.GetOneUserType.LOGIN);
        } catch (SQLException e) {
            logger.error("Can't get user by login: " + login,e);
        }

        return user;
    }

    public User getUserByEmail(String email){
        User u = new User();
        u.setEmail(email);

        User user = null;

        try {
            user = userDAO.getUser(u, UserDAO.GetOneUserType.EMAIL);
        } catch (SQLException e) {
            logger.error("Can't get user by email: " + email,e);
        }

        return user;
    }

    public User getUserByFullName(String fullName){
        User u = new User();
        u.setFullName(fullName);

        User user = null;

        try {
            user = userDAO.getUser(u, UserDAO.GetOneUserType.NAME);
        } catch (SQLException e) {
            logger.error("Can't get user by full name: " + fullName,e);
        }

        return user;
    }


    public void updateUserPassword(User user){
        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.PASSWORD);
        } catch (SQLException e) {
            logger.error("Can't update password for user:" + user,e);
        }
    }

    public List<User> getAllUsersInRange(int startIndex,int endIndex){
        List<User> allUsers = null;
        try {
            allUsers = userDAO.getAllUsersInRange(UserDAO.GetType.ALL,startIndex,endIndex);
        } catch (SQLException e) {
            logger.error("Can't get users by int range:" + startIndex + " - " + endIndex,e);
        }

        return allUsers;
    }

    public List<User> getBossesInRange(int startIndex,int endIndex){
        List<User> bosses = null;
        try {
            bosses = userDAO.getAllUsersInRange(UserDAO.GetType.BOSS,startIndex,endIndex);
        } catch (SQLException e) {
            logger.error("Can't get bosses by int range:" + startIndex + " - " + endIndex,e);
        }

        return bosses;
    }

    public List<User> getAdminsInRange(int startIndex,int endIndex){
        List<User> admins = null;
        try {
            admins = userDAO.getAllUsersInRange(UserDAO.GetType.ADMIN,startIndex,endIndex);
        } catch (SQLException e) {
            logger.error("Can't get admins by int range:" + startIndex + " - " + endIndex,e);
        }

        return admins;
    }

    public List<User> getBookmakersInRange(int startIndex,int endIndex){
        List<User> bookmakers = null;
        try {
            bookmakers = userDAO.getAllUsersInRange(UserDAO.GetType.BOOKMAKER,startIndex,endIndex);
        } catch (SQLException e) {
            logger.error("Can't get bookmakers by int range:" + startIndex + " - " + endIndex,e);
        }

        return bookmakers;
    }

    public List<User> getClientsInRange(int startIndex,int endIndex){
        List<User> clients = null;
        try {
            clients = userDAO.getAllUsersInRange(UserDAO.GetType.CLIENT,startIndex,endIndex);
        } catch (SQLException e) {
            logger.error("Can't get clients by int range:" + startIndex + " - " + endIndex,e);
        }

        return clients;
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
            logger.error("Can't change user:" + login + " language to "+ lang,e);
        }
    }

    public void setUserBan(int userId){
        User user = new User();
        user.setId(userId);
        user.setBanned(true);

        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.BAN);

        } catch (SQLException e) {
            logger.error("Can't set bann for user: " +userId,e);
        }
    }


    public void resetUserBan(int userId){
        User user = new User();
        user.setId(userId);
        user.setBanned(false);

        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.BAN);

        } catch (SQLException e) {
            logger.error("Can't reset bann for user: " + userId,e);
        }
    }


    public void changeUserRole(int userId,String role){
        User user = new User();
        user.setId(userId);
        user.setRole(Role.valueOf(role));
        try {
            userDAO.updateUser(user, UserDAO.UpdateUserType.ROLE);
        } catch (SQLException e) {
            logger.error("Can't change user role to:" + role + " for user " + userId,e);
        }
    }

    public List<String> getAllEmails(){
        List<String> emails = new ArrayList<>();

        List<User> allUsers = null;
        try {
             allUsers = userDAO.getAllUsers();
        } catch (SQLException e) {
            logger.error("Can't get all emails!",e);
            return null;
        }

        for(User user:allUsers){
            emails.add(user.getEmail());
        }

        return emails;
    }
}