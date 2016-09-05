package com.epam.javalab13.dao.game;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.bet.Result;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.transformer.game.ResultCoefficientTransformer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResultCoefficientDAO {
    private static final Logger logger = Logger.getLogger(ResultCoefficientDAO.class);
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private ResultCoefficientTransformer scTransformer;

    public ResultCoefficient findById(int id) {
        conn = ConnectionPool.getConnection();
        try {
            String sql = "SELECT r.id AS \"result_id\", r.result, r.coefficient, g.id AS \"game_id\","
                    + "  g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
                    + "  t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\", t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
                    + "  t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\", t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
                    + "  u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
                    + "FROM result_coefficient r JOIN game g ON r.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE r.id=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            scTransformer = new ResultCoefficientTransformer();
            ResultCoefficient sc = scTransformer.getOne(rs);
            return sc;
        } catch (SQLException e) {
            logger.error("failed to find ResultCoefficient with id " + id + " from database", e);
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public boolean create(ResultCoefficient rc) {
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            String insertQuery = "insert into result_coefficient ( game_id, result, coefficient) values(?,?,?);";
            ps = conn.prepareStatement(insertQuery);
            ps.setInt(1, rc.getGame().getId());
            ps.setString(2, rc.getResult().name());
            ps.setDouble(3, rc.getCoefficient());
            logger.info("inserting new ResultCoefficient to database " + rc);
            ps.executeUpdate();
            conn.commit();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = (int) rs.getLong(1);
                rc.setId(id);
            }
            logger.info("successfully inserted ResultCoefficient " + rc);
            return true;
        } catch (SQLException ex) {
            logger.error("failed to insert ResultCoefficient to database" + rc, ex);
            try {
                conn.rollback();
            } catch (SQLException e) {
                logger.error("failed to rollback", e);
            }
            return false;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public boolean update(ResultCoefficient rc) {
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            String insertQuery = "update result_coefficient set game_id=?, result=?, coefficient=? WHERE id=?;";
            ps = conn.prepareStatement(insertQuery);
            ps.setInt(1, rc.getGame().getId());
            ps.setString(2, rc.getResult().name());
            ps.setDouble(3, rc.getCoefficient());
            ps.setInt(4, rc.getId());
            logger.info("inserting new ResultCoefficient to database " + rc);
            ps.executeUpdate();
            conn.commit();
            logger.info("successfully inserted ResultCoefficient " + rc);
            return true;
        } catch (SQLException ex) {
            logger.error("failed to insert ResultCoefficient to database" + rc, ex);
            try {
                conn.rollback();
            } catch (SQLException e) {
                logger.error("failed to rollback", e);
            }
            return false;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public boolean delete(ResultCoefficient rc) {
        logger.info("deleting ResultCoefficient from database "+ rc);
        return false;
    }

    public boolean delete(int id) {
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            String insertQuery = "delete from result_coefficient WHERE id=?;";
            ps = conn.prepareStatement(insertQuery);
            ps.setInt(1, id);
            logger.info("deleting ResultCoefficient with id " +id+ " from database");
            ps.executeUpdate();
            conn.commit();

            logger.info("successfully deleted ResultCoefficient with id " + id);
            return true;
        } catch (SQLException ex) {
            logger.error("failed to delete ResultCoefficient with id " +id+" from database", ex);
            try {
                conn.rollback();
            } catch (SQLException e) {
                logger.error("failed to rollback", e);
            }
            return false;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public List<ResultCoefficient> findAll() {
        conn = ConnectionPool.getConnection();
        try {
            String sql = "SELECT r.id AS \"result_id\", r.result, r.coefficient, g.id AS \"game_id\","
                    + "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
                    + "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
                    + "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
                    + "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
                    + "FROM result_coefficient r JOIN game g ON r.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            scTransformer = new ResultCoefficientTransformer();
            return scTransformer.getAll(rs);
        } catch (SQLException e) {
            logger.error("failed to find ResultCoefficient from database", e);
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public List<ResultCoefficient> findAllByGame(Game game) {
        conn = ConnectionPool.getConnection();
        try {
            String sql = "SELECT r.id AS \"result_id\", r.result, r.coefficient, g.id AS \"game_id\","
                    + "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
                    + "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
                    + "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
                    + "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
                    + "FROM result_coefficient r JOIN game g ON r.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE r.game_id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, game.getId());
            rs = ps.executeQuery();
            scTransformer = new ResultCoefficientTransformer();
            return scTransformer.getAll(rs);
        } catch (SQLException e) {
            logger.error("failed to find ResultCoefficient from database", e);
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public List<ResultCoefficient> findAllByResult(Result result) {
        conn = ConnectionPool.getConnection();
        try {
            String sql = "SELECT r.id AS \"result_id\", r.result, r.coefficient, g.id AS \"game_id\","
                    + "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
                    + "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
                    + "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
                    + "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
                    + "FROM result_coefficient r JOIN game g ON r.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE r.result like ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, result.name());
            rs = ps.executeQuery();
            scTransformer = new ResultCoefficientTransformer();
            return scTransformer.getAll(rs);
        } catch (SQLException e) {
            logger.error("failed to find ResultCoefficient from database", e);
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

    public List<ResultCoefficient> findAllByCoefficient(double min,double max) {
        conn = ConnectionPool.getConnection();
        try {
            String sql = "SELECT r.id AS \"result_id\", r.result, r.coefficient, g.id AS \"game_id\","
                    + "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
                    + "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
                    + "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
                    + "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
                    + "FROM result_coefficient r JOIN game g ON r.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE r.coefficient BETWEN ? AND ?;";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            rs = ps.executeQuery();
            scTransformer = new ResultCoefficientTransformer();
            return scTransformer.getAll(rs);
        } catch (SQLException e) {
            logger.error("failed to find ResultCoefficient from database", e);
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("failed to close connection", e);
            }
        }
    }

}