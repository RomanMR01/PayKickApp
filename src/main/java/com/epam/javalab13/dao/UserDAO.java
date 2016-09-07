package com.epam.javalab13.dao;

import java.sql.*;
import java.util.List;

import com.epam.javalab13.model.User;
import com.epam.javalab13.transformer.UserTransformer;
import org.apache.log4j.Logger;

/**
 * CRUD operations for manipulation with user table
 */
public class UserDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    public enum UpdateUserType {
        PASSWORD,
        BALANCE,
        ROLE,
        BAN,
        LANGUAGE
    }


    public enum GetOneUserType {
        ID,
        LOGIN,
        EMAIL
    }

    public enum GetUsersType {
        ROLE,
        GENDER,
        BANN,
        LANGUAGE,
        AGE
    }

    public enum GetType {
        ALL,
        BOSS,
        ADMIN,
        BOOKMAKER,
        CLIENT
    }


    /**
     * Add new user into database
     *
     * @param user the User object
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        final String SQL = "INSERT INTO user(full_name, age, gender, email, login, password) VALUES(?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.prepareStatement(SQL);

            st.setString(1, user.getFullName());
            st.setInt(2, user.getAge());
            st.setString(3, user.getGender().toString());
            st.setString(4, user.getEmail());
            st.setString(5, user.getLogin());
            st.setString(6, user.getPassword());

            st.executeUpdate();
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }
        }
    }

    /**
     * Update user by difference parameters
     *
     * @param user the User object
     * @param type the type of User update: PASSWORD, BALANCE, ROLE, BANN or LANGUAGE status
     * @throws SQLException
     */
    public void updateUser(User user, UpdateUserType type) throws SQLException {
        final String SQL_PASSWORD = "UPDATE user u SET u.password = ? WHERE u.id=?";
        final String SQL_BALANCE = "UPDATE user u SET u.balance = ? WHERE u.id=?";
        final String SQL_ROLE = "UPDATE user u SET u.role = ? WHERE u.id=?";
        final String SQL_BANN = "UPDATE user u SET u.is_banned = ? WHERE u.id=?";
        final String SQL_LANGUAGE = "UPDATE user u SET u.language = ? WHERE u.id=?";

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case PASSWORD:
                    st = conn.prepareStatement(SQL_PASSWORD);

                    st.setString(1, user.getPassword());
                    st.setInt(2, user.getId());
                    break;
                case BALANCE:
                    st = conn.prepareStatement(SQL_BALANCE);

                    st.setDouble(1, user.getBalance());
                    st.setInt(2, user.getId());

                    break;
                case ROLE:
                    st = conn.prepareStatement(SQL_ROLE);

                    st.setString(1, user.getRole().toString());
                    st.setInt(2, user.getId());

                    break;
                case BAN:
                    st = conn.prepareStatement(SQL_BANN);

                    int bannValue = 0;
                    if (user.isBanned()) {
                        bannValue = 1;
                    }

                    st.setBoolean(1, user.isBanned());
                    st.setInt(2, user.getId());

                    break;
                case LANGUAGE:
                    st = conn.prepareStatement(SQL_LANGUAGE);

                    st.setString(1, user.getLanguage().toString());
                    st.setInt(2, user.getId());

                    break;
            }

            st.executeUpdate();
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }
        }
    }

    /**
     * Getting user by difference parameters
     *
     * @param user the User object
     * @param type the types for getting user :ID, LOGIN, EMAIL
     * @return full initialized User object by user login
     * @throws SQLException
     */
    public User getUser(User user, GetOneUserType type) throws SQLException {
        final String SQL_ID = "SELECT * FROM user u WHERE u.id = ?";
        final String SQL_LOGIN = "SELECT * FROM user u WHERE u.login LIKE ?";
        final String SQL_EMAIL = "SELECT * FROM user u WHERE u.email LIKE ?";

        User returnUser = null;
        UserTransformer userTransformer = new UserTransformer();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case ID:
                    st = conn.prepareStatement(SQL_ID);
                    st.setInt(1, user.getId());
                    rs = st.executeQuery();

                    returnUser = userTransformer.getOne(rs);
                    break;
                case LOGIN:
                    st = conn.prepareStatement(SQL_LOGIN);
                    st.setString(1, user.getLogin());
                    rs = st.executeQuery();

                    returnUser = userTransformer.getOne(rs);
                    break;
                case EMAIL:
                    st = conn.prepareStatement(SQL_EMAIL);
                    st.setString(1, user.getEmail());
                    rs = st.executeQuery();

                    returnUser = userTransformer.getOne(rs);
                    break;


            }
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }

        }

        return returnUser;
    }

    /**
     * Getting users by difference parameters
     *
     * @param value search value
     * @param type  the types for getting user :ID, LOGIN, EMAIL
     * @return full initialized User object by user login
     * @throws SQLException
     */
    public List<User> getUsers(String value, GetUsersType type) throws SQLException {
        final String SQL_ROLE = "SELECT * FROM user u WHERE u.role LIKE ?";
        final String SQL_GENDER = "SELECT * FROM user u WHERE u.gender LIKE ?";
        final String SQL_BANN = "SELECT * FROM user u WHERE u.is_banned = ?";
        final String SQL_LANGUAGE = "SELECT * FROM user u WHERE u.language LIKE ?";
        final String SQL_AGE = "SELECT * FROM user u WHERE u.age < ?";

        List<User> users = null;
        UserTransformer userTransformer = new UserTransformer();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();

            switch (type) {
                case ROLE:
                    st = conn.prepareStatement(SQL_ROLE);
                    st.setString(1, value);
                    rs = st.executeQuery();

                    users = userTransformer.getAll(rs);
                    break;
                case GENDER:
                    st = conn.prepareStatement(SQL_GENDER);
                    st.setString(1, value);
                    rs = st.executeQuery();

                    users = userTransformer.getAll(rs);
                    break;
                case BANN:
                    st = conn.prepareStatement(SQL_BANN);
                    st.setString(1, value);
                    rs = st.executeQuery();

                    users = userTransformer.getAll(rs);
                    break;
                case LANGUAGE:
                    st = conn.prepareStatement(SQL_LANGUAGE);
                    st.setString(1, value);
                    rs = st.executeQuery();

                    users = userTransformer.getAll(rs);
                    break;
                case AGE:
                    st = conn.prepareStatement(SQL_AGE);
                    st.setString(1, value);
                    rs = st.executeQuery();

                    users = userTransformer.getAll(rs);
                    break;
            }
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }

        }

        return users;
    }

    /**
     * Getting all users
     *
     * @return list of all users
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        final String SQL = "SELECT * FROM user";

        List<User> users = null;
        UserTransformer userTransformer = new UserTransformer();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL);

            users = userTransformer.getAll(rs);

        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }

        }

        return users;
    }

    /**
     * Getting all users by type (all or by them roles) in some range
     *
     * @param type  the type of get (ALL, BOSS, ADMIN, BOOKMAKER, CLIENT)
     * @param start the start of limit
     * @param end   the end of limit
     * @return list of users
     * @throws SQLException
     */
    public List<User> getAllUsersInRange(GetType type, int start, int end) throws SQLException {
        final String SQL_ALL = "SELECT * FROM user u LIMIT ?,?";
        final String SQL_BOSS = "SELECT * FROM user u WHERE u.role='BOSS' LIMIT ?,?";
        final String SQL_ADMIN = "SELECT * FROM user u WHERE u.role='ADMIN' LIMIT ?,?";
        final String SQL_BOOKMAKER = "SELECT * FROM user u WHERE u.role='BOOKMAKER' LIMIT ?,?";
        final String SQL_CLIENT = "SELECT * FROM user u WHERE u.role='CLIENT' LIMIT ?,?";


        List<User> users = null;
        UserTransformer userTransformer = new UserTransformer();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();

            switch (type){
                case ALL:
                    st = conn.prepareStatement(SQL_ALL);
                    st.setInt(1,start);
                    st.setInt(2,end);
                    break;
                case BOSS:
                    st = conn.prepareStatement(SQL_BOSS);
                    st.setInt(1,start);
                    st.setInt(2,end);
                    break;
                case ADMIN:
                    st = conn.prepareStatement(SQL_ADMIN);
                    st.setInt(1,start);
                    st.setInt(2,end);
                    break;
                case BOOKMAKER:
                    st = conn.prepareStatement(SQL_BOOKMAKER);
                    st.setInt(1,start);
                    st.setInt(2,end);
                    break;
                case CLIENT:
                    st = conn.prepareStatement(SQL_CLIENT);
                    st.setInt(1,start);
                    st.setInt(2,end);
                    break;
            }

            rs = st.executeQuery();
            users = userTransformer.getAll(rs);
        } finally {
            if (st != null) try {
                st.close();
            } catch (Exception e) {
                logger.warn("Exception while close statement:", e);
            }
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                logger.warn("Exception while close connection:", e);
            }

        }

        return users;
    }


}
