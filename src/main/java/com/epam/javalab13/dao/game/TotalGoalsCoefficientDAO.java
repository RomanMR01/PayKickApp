package com.epam.javalab13.dao.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.TotalGoalsCoefficient;
import com.epam.javalab13.transformer.Transformer;
import com.epam.javalab13.transformer.game.TotalGoalsCoefficientTransformer;

public class TotalGoalsCoefficientDAO {

	private Connection conn;
	private Statement smt;
	private PreparedStatement ps;
	private ResultSet rs;

	private static Logger logger = Logger.getLogger(TotalGoalsCoefficientDAO.class);

	private Transformer<TotalGoalsCoefficient> tgcTransformer;

	public boolean create(TotalGoalsCoefficient tgc) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String insertQuery = "insert into total_goals_coefficient ( game_id, goalCoefficient) values(?,?);";
			ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tgc.getGame().getId());
			ps.setDouble(2, tgc.getGoalCoefficient());
			logger.info("inserting new TotalGoalsCoefficient to database " + tgc);
			ps.executeUpdate();
			conn.commit();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = (int) rs.getLong(1);
				tgc.setId(id);
			}
			logger.info("successfully inserted TotalGoalsCoefficient " + tgc);
			return true;
		} catch (SQLException ex) {
			logger.error("failed to insert TotalGoalsCoefficient to database" + tgc, ex);
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("failed to rollback", e);
			}
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public TotalGoalsCoefficient findById(int id) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT t.id, t.goal_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM total_goals_coefficient t JOIN game g ON t.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE t.id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			tgcTransformer = new TotalGoalsCoefficientTransformer();
			TotalGoalsCoefficient tgc = tgcTransformer.getOne(rs);
			return tgc;
		} catch (SQLException e) {
			logger.error("failed to find TotalGoalsCoefficient with id " + id + " from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public boolean update(TotalGoalsCoefficient tgc) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String insertQuery = "update total_goals_coefficient t set t.game_id=?, t.goal_coefficient=?;";
			ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, tgc.getGame().getId());
			ps.setDouble(2, tgc.getGoalCoefficient());
			logger.info("inserting new TotalGoalsCoefficient to database " + tgc);
			ps.executeUpdate();
			conn.commit();
			logger.info("successfully inserted TotalGoalsCoefficient " + tgc);
			return true;
		} catch (SQLException ex) {
			logger.error("failed to insert TotalGoalsCoefficient to database" + tgc, ex);
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("failed to rollback", e);
			}
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public boolean delete(TotalGoalsCoefficient tgc) {
		logger.info("deleting TotalGoalsCoefficient from database " + tgc);
		return delete(tgc.getId());
	}

	public boolean delete(int id) {
		try {
			conn = ConnectionPool.getConnection();
			String deleteQuery = "DELETE FROM total_goals_coefficient WHERE id = ?;";
			ps = conn.prepareStatement(deleteQuery);
			conn.setAutoCommit(false);
			ps.setInt(1, id);
			logger.info("deleting TotalGoalsCoefficient with id " + id);
			conn.commit();
			logger.info("successfull deleting TotalGoalsCoefficient with id " + id);
			return true;
		} catch (SQLException e1) {
			logger.error("failed to delete TotalGoalsCoefficient with id " + id + " from database ", e1);
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("failed to rollback transaction", e);
			}
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<TotalGoalsCoefficient> findAll() {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT t.id, t.goal_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM total_goals_coefficient t JOIN game g ON t.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			tgcTransformer = new TotalGoalsCoefficientTransformer();
			return tgcTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all TotalGoalsCoefficient from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<TotalGoalsCoefficient> findAllByGame(Game game) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT t.id, t.goal_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM total_goals_coefficient t JOIN game g ON t.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE t.game_id =?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, game.getId());
			rs = ps.executeQuery();
			tgcTransformer = new TotalGoalsCoefficientTransformer();
			return tgcTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all TotalGoalsCoefficient from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<TotalGoalsCoefficient> findAllByCoefficient(double min, double max) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT t.id, t.goal_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM total_goals_coefficient t JOIN game g ON t.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE t.coefficient BETWEN ? AND ?;";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, min);
			ps.setDouble(2, max);
			rs = ps.executeQuery();
			tgcTransformer = new TotalGoalsCoefficientTransformer();
			return tgcTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all TotalGoalsCoefficient from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

}
