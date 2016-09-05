package com.epam.javalab13.dao.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Status;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.Transformer;
import com.epam.javalab13.transformer.game.GameTransformer;

public class GameDAO {

	private Transformer<Game> gameTransformer;
//	private DAO<Team> teamDAO;
//	private DAO<User> userDAO;

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private static Logger logger = Logger.getLogger(Game.class);

	public Game findById(int id) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id WHERE g.id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			Game game = gameTransformer.getOne(rs);
			return game;
		} catch (SQLException e) {
			logger.error("failed to find game with id "+id+" from database", e);
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

	public boolean create(Game game) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String insertQuery = "insert into game ( title, location, date, first_team_id, second_team_id, status) values(?,?,?,?,?,?);";
			ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, game.getTitle());
			ps.setString(2, game.getLocation());
			ps.setDate(3, new java.sql.Date(game.getDate().getTime()));
			ps.setInt(4, game.getFirstTeam().getId());
			ps.setInt(5, game.getSecondTeam().getId());
			ps.setString(6, game.getStatus().name());
			logger.info("inserting new game to database " + game);
			ps.executeUpdate();
			conn.commit();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = (int) rs.getLong(1);
				game.setId(id);
			}
			logger.info("successfully inserted game " + game);
			return true;
		} catch (SQLException ex) {
			logger.error("failed to insert game to database" + game, ex);
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("failed to rollback", e);
			}
			return false;
		} finally {
			try {
				conn.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public boolean update(Game game) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String updateQuery = "update game g set g.title=?, g.location=?, g.date=?, g.first_team_id=?, g.second_team_id=?, g.first_goals=?,g.second_goals=?, g.status=?, g.bookmaker_id=?,g.profit=? where g.id=?;";
			ps = conn.prepareStatement(updateQuery);
			ps.setString(1, game.getTitle());
			ps.setString(2, game.getLocation());
			ps.setDate(3, new java.sql.Date(game.getDate().getTime()));
			ps.setInt(4, game.getFirstTeam().getId());
			ps.setInt(5, game.getSecondTeam().getId());
			ps.setInt(6, game.getFirstGoals());
			ps.setInt(7, game.getSecondGoals());
			ps.setString(8, game.getStatus().name());
			ps.setInt(9, game.getBookmaker().getId());
			ps.setDouble(10, game.getProfit());
			ps.setInt(11, game.getId());
			logger.info("updating game in database "+game);
			ps.executeUpdate();
			conn.commit();
			logger.info("successfully updated game "+game);
			return true;
		} catch (SQLException e1) {
			logger.error("failed to update game in database "+game, e1);
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

	public boolean delete(Game game) {
		logger.info("deleting game from database "+game);
		return delete(game.getId());
	}

	public boolean delete(int id) {
		try {
			conn = ConnectionPool.getConnection();
			String deleteQuery = "DELETE FROM game WHERE id = ?;";
			ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, id);
			conn.setAutoCommit(false);
			logger.info("deleting game with id "+id);
			conn.commit();
			logger.info("successfull deleting game with id "+id);
			return true;
		} catch (SQLException e1) {
			logger.error("failed to delete game with id "+id+" from database ", e1);
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

	public List<Game> findAll() {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByTitle(String title) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.title like ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByLocation(String location) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.location like ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, location);
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByDate(Date date) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.date like ?;";
			ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(date.getTime()));
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByTeam(Team team) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id,g.title AS \"game_id\",g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.first_team_id=? OR g.second_team_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, team.getId());
			ps.setInt(2, team.getId());
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByStatus(Status status) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id,g.title AS \"game_id\",g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.status like ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status.name());
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByBookmaker(User bookmaker) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title ,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.bookmaker_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookmaker.getId());
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	public List<Game> findAllByPrifit(double min, double max) {
		conn = ConnectionPool.getConnection();
		try {
			String sql = "SELECT g.id AS \"game_id\",g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.profit BETWEN ? AND ?;";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, min);
			ps.setDouble(2, max);
			rs = ps.executeQuery();
			gameTransformer = new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database", e);
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
