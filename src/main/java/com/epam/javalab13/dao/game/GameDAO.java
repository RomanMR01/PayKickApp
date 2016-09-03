package com.epam.javalab13.dao.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.dao.DAO;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.transformer.Transformer;
import com.epam.javalab13.transformer.game.GameTransformer;

public class GameDAO implements DAO<Game> {

	private Transformer<Game> gameTransformer;
	private DAO<Team> teamDAO;
	private DAO<User> userDAO;
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private static Logger logger = Logger.getLogger(Game.class);

	@Override
	public Game findById(int id) {
		conn=ConnectionPool.getConnection();
		try {
			String sql="SELECT g.id,g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id where g.id=?;";
//			String sql="SELECT * FROM game g WHERE g.id=?;";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			gameTransformer=new GameTransformer();
			Game game = gameTransformer.getOne(rs);
//			int firstTeamId = rs.getInt("first_team_id");
//			int secondTeamId = rs.getInt("second_team_id");
//			int bookmakerId = rs.getInt("bookmaker_id");
//			game.setFirstTeam(teamDAO.findById(firstTeamId));
//			game.setSecondTeam(teamDAO.findById(secondTeamId));
//			game.setBookmaker(userDAO.findById(bookmakerId));
			return game;
		} catch (SQLException e) {
			logger.error("failed to find game from database",e);
			return null;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection", e);
			}
		}
	}

	@Override
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
			ps.executeUpdate();
			conn.commit();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = (int) rs.getLong(1);
				game.setId(id);
			}
			return true;
		} catch (SQLException ex) {
			logger.error("failed to insert game to database", ex);
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

	@Override
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
			ps.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e1) {
			logger.error("failed to update game in database", e1);
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

	@Override
	public boolean delete(Game game) {
		return delete(game.getId());
	}

	@Override
	public boolean delete(int id) {
		try {
			conn = ConnectionPool.getConnection();
			String deleteQuery = "DELETE FROM game WHERE id = ?;";
			ps = conn.prepareStatement(deleteQuery);
			conn.setAutoCommit(false);
			conn.commit();
			return true;
		} catch (SQLException e1) {
			logger.error("failed to delete game from database", e1);
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

	@Override
	public List<Game> findAll() {
		conn=ConnectionPool.getConnection();
		try {
			String sql="SELECT g.id,g.title,g.location,g.date,g.first_goals,g.second_goals,g.status,g.profit,"
					+ "t1.id AS \"t1_id\",t1.name AS \"t1_name\",t1.location AS \"t1_location\",t1.emblem_url AS \"t1_emblem_url\",t1.total_wins AS \"t1_total_wins\",t1.total_loses AS \"t1_total_loses\",t1.total_draws AS \"t1_total_draws\","
					+ "t2.id AS \"t2_id\",t2.name AS \"t2_name\",t2.location AS \"t2_location\",t2.emblem_url AS \"t2_emblem_url\",t2.total_wins AS \"t2_total_wins\",t2.total_loses AS \"t2_total_loses\",t2.total_draws AS \"t2_total_draws\","
					+ "u.id AS \"u_id\",u.full_name,u.age,u.gender,u.email,u.login,u.password,u.balance,u.avatar_url,u.role,u.language,u.is_banned "
					+ "FROM game g JOIN team t1 ON g.first_team_id = t1.id JOIN team t2 ON g.second_team_id = t2.id JOIN user u ON g.bookmaker_id = u.id;";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			gameTransformer=new GameTransformer();
			return gameTransformer.getAll(rs);
		} catch (SQLException e) {
			logger.error("failed to find all games from database",e);
			return null;
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("failed to close connection",e);
			}
		}
	}

}
