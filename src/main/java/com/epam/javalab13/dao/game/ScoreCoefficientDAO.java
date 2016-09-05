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
import com.epam.javalab13.model.game.ScoreCoefficient;
import com.epam.javalab13.model.game.TotalGoalsCoefficient;
import com.epam.javalab13.transformer.game.ScoreCoefficientTransformer;
import com.epam.javalab13.transformer.game.TotalGoalsCoefficientTransformer;

public class ScoreCoefficientDAO {

	private static final Logger logger = Logger.getLogger(ScoreCoefficientDAO.class);
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private ScoreCoefficientTransformer scTransformer;

	public ScoreCoefficient findById(int id) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT s.id AS \"score_id\", s.start_coefficient, s.first_team_coefficient, s.second_team_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM score_coefficient s JOIN game g ON s.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE s.id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			scTransformer = new ScoreCoefficientTransformer();
			ScoreCoefficient sc = scTransformer.getOne(rs);
			return sc;
		} catch (SQLException e) {
			logger.error("failed to find ScoreCoefficient with id " + id + " from database", e);
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

	public boolean create(ScoreCoefficient sc) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String insertQuery = "insert into score_coefficient ( game_id, start_coefficient, first_team_coefficient, second_team_coefficient) values(?,?,?,?);";
			ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, sc.getGame().getId());
			ps.setDouble(2, sc.getStartCoefficient());
			ps.setDouble(3, sc.getFirstTeamCoefficient());
			ps.setDouble(4, sc.getSecondTeamCoefficient());
			logger.info("inserting new ScoreCoefficient to database " + sc);
			ps.executeUpdate();
			conn.commit();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = (int) rs.getLong(1);
				sc.setId(id);
			}
			logger.info("successfully inserted ScoreCoefficient " + sc);
			return true;
		} catch (SQLException ex) {
			logger.error("failed to insert ScoreCoefficient to database" + sc, ex);
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

	public boolean update(ScoreCoefficient sc) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String insertQuery = "update score_coefficient s set s.game_id=?, s.start_coefficient=? , s.first_team_coefficient=? s.second_team_coefficient=? WHERE s.id=?;";
			ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, sc.getGame().getId());
			ps.setDouble(2, sc.getStartCoefficient());
			ps.setDouble(3, sc.getFirstTeamCoefficient());
			ps.setDouble(4, sc.getSecondTeamCoefficient());
			ps.setInt(5, sc.getId());
			logger.info("inserting new ScoreCoefficient to database " + sc);
			ps.executeUpdate();
			conn.commit();
			logger.info("successfully inserted ScoreCoefficient " + sc);
			return true;
		} catch (SQLException ex) {
			logger.error("failed to insert ScoreCoefficient to database" + sc, ex);
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

	public boolean delete(ScoreCoefficient sc) {
		logger.info("deleting ScoreCoefficient from database " + sc);
		return delete(sc.getId());
	}

	public boolean delete(int id) {
		try {
			conn = ConnectionPool.getConnection();
			String deleteQuery = "DELETE FROM score_coefficient WHERE id = ?;";
			ps = conn.prepareStatement(deleteQuery);
			conn.setAutoCommit(false);
			ps.setInt(1, id);
			logger.info("deleting ScoreCoefficient with id " + id);
			conn.commit();
			logger.info("successfull deleting ScoreCoefficient with id " + id);
			return true;
		} catch (SQLException e1) {
			logger.error("failed to delete ScoreCoefficient with id " + id + " from database ", e1);
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("failed to rollback transaction", e);
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

	public List<ScoreCoefficient> findAll() {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT s.id AS \"score_id\", s.start_coefficient, s.first_team_coefficient, s.second_team_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM score_coefficient s JOIN game g ON s.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			scTransformer = new ScoreCoefficientTransformer();
			List<ScoreCoefficient> scList = scTransformer.getAll(rs);
			return scList;
		} catch (SQLException e) {
			logger.error("failed to find all ScoreCoefficient from database", e);
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
	
	public List<ScoreCoefficient> findAllByGame(Game game) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT s.id AS \"score_id\", s.start_coefficient, s.first_team_coefficient, s.second_team_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM score_coefficient s JOIN game g ON s.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE s.game_id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,game.getId());
			rs = ps.executeQuery();
			scTransformer = new ScoreCoefficientTransformer();
			List<ScoreCoefficient> scList = scTransformer.getAll(rs);
			return scList;
		} catch (SQLException e) {
			logger.error("failed to find all ScoreCoefficient from database", e);
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
	
	public List<ScoreCoefficient> findAllByStartCoefficient(double min, double max) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT s.id AS \"score_id\", s.start_coefficient, s.first_team_coefficient, s.second_team_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM score_coefficient s JOIN game g ON s.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE s.start_coefficient BETWEN ? AND ?;";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1,min);
			ps.setDouble(2,max);
			rs = ps.executeQuery();
			scTransformer = new ScoreCoefficientTransformer();
			List<ScoreCoefficient> scList = scTransformer.getAll(rs);
			return scList;
		} catch (SQLException e) {
			logger.error("failed to find all ScoreCoefficient from database", e);
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
	public List<ScoreCoefficient> findAllByFirstTeamCoefficient(double min, double max) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT s.id AS \"score_id\", s.start_coefficient, s.first_team_coefficient, s.second_team_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM score_coefficient s JOIN game g ON s.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE s.First_team_coefficient BETWEN ? AND ?;";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1,min);
			ps.setDouble(2,max);
			rs = ps.executeQuery();
			scTransformer = new ScoreCoefficientTransformer();
			List<ScoreCoefficient> scList = scTransformer.getAll(rs);
			return scList;
		} catch (SQLException e) {
			logger.error("failed to find all ScoreCoefficient from database", e);
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
	public List<ScoreCoefficient> findAllBySecondTeamCoefficient(double min, double max) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT s.id AS \"score_id\", s.start_coefficient, s.first_team_coefficient, s.second_team_coefficient, g.id AS \"game_id\","
					+ "g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM score_coefficient s JOIN game g ON s.game_id = g.id JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE s.second_team_coefficient BETWEN ? AND ?;";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1,min);
			ps.setDouble(2,max);
			rs = ps.executeQuery();
			scTransformer = new ScoreCoefficientTransformer();
			List<ScoreCoefficient> scList = scTransformer.getAll(rs);
			return scList;
		} catch (SQLException e) {
			logger.error("failed to find all ScoreCoefficient from database", e);
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
