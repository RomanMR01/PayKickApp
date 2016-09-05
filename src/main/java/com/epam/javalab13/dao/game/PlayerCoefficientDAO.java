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
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.transformer.game.GameTransformer;
import com.epam.javalab13.transformer.game.PlayerCoefficientTransformer;

public class PlayerCoefficientDAO {

	private static final Logger logger = Logger.getLogger(PlayerCoefficientDAO.class);
	private Connection conn;
	private PreparedStatement ps;

	private GameDAO gameDao;
	private PlayerCoefficientTransformer pcTransformer;
	private ResultSet rs;
	private PlayerDAO playerDao;

	public boolean create(PlayerCoefficient pc) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into player_coefficient (game_id, player_id, coefficient) values (?,?,?);";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pc.getGame().getId());
			ps.setInt(2, pc.getPlayer().getId());
			ps.setDouble(3, pc.getCoefficient());
			ps.executeUpdate();
			conn.commit();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = (int) rs.getLong(1);
				pc.setId(id);
			}
			return true;
		} catch (SQLException e) {
			logger.error("failed to create PlayerCoefficient " + pc, e);
			return false;
		} finally {
			try {
				conn.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("failed to close connection", e);
			}
		}
	}
	
	public boolean update(PlayerCoefficient pc) {
		try {
			conn = ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String sql = "update player_coefficient set game_id=?, player_id=?, coefficient=? where id=?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pc.getGame().getId());
			ps.setInt(2, pc.getPlayer().getId());
			ps.setDouble(3, pc.getCoefficient());
			ps.setInt(4, pc.getId());
			ps.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			logger.error("failed to update PlayerCoefficient " + pc, e);
			return false;
		} finally {
			try {
				conn.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("failed to close connection", e);
			}
		}
	}
	
	public PlayerCoefficient findById(int id){
		conn = ConnectionPool.getConnection();
		try {
			String sql = "select * from player_coefficient where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			pcTransformer=new PlayerCoefficientTransformer();
			PlayerCoefficient pc = pcTransformer.getOne(rs);
			gameDao=new GameDAO();
			pc.setGame(gameDao.findById(pc.getGame().getId()));
			playerDao=new PlayerDAO();
			pc.setPlayer(playerDao.getPlayer(pc.getPlayer(), "id"));
			return pc;
		} catch (SQLException e) {
			logger.error("failed to find game with id "+id+" from database", e);
			return null;
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
	
	public List<PlayerCoefficient> findAll(){
		conn = ConnectionPool.getConnection();
		try {
			String sql = "select * from player_coefficient ;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			pcTransformer=new PlayerCoefficientTransformer();
			List<PlayerCoefficient> pcList = pcTransformer.getAll(rs);
			for (PlayerCoefficient pc : pcList) {
				gameDao=new GameDAO();
				pc.setGame(gameDao.findById(pc.getGame().getId()));
				playerDao = new PlayerDAO();
				pc.setPlayer(playerDao.getPlayer(pc.getPlayer(), "id"));
			}
			return pcList;
		} catch (SQLException e) {
			logger.error("failed to find all PlayerCoefficient from database", e);
			return null;
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
	public List<PlayerCoefficient> findAllByGame(Game game){
		conn = ConnectionPool.getConnection();
		try {
			String sql = "select * from player_coefficient where game_id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, game.getId());
			rs = ps.executeQuery();
			pcTransformer=new PlayerCoefficientTransformer();
			List<PlayerCoefficient> pcList = pcTransformer.getAll(rs);
			for (PlayerCoefficient pc : pcList) {
				gameDao=new GameDAO();
				pc.setGame(gameDao.findById(pc.getGame().getId()));
				playerDao = new PlayerDAO();
				pc.setPlayer(playerDao.getPlayer(pc.getPlayer(), "id"));
			}
			return pcList;
		} catch (SQLException e) {
			logger.error("failed to find all PlayerCoefficient from database", e);
			return null;
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
	public List<PlayerCoefficient> findAllByPlayer(Player player){
		conn = ConnectionPool.getConnection();
		try {
			String sql = "select * from player_coefficient where player_id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			pcTransformer=new PlayerCoefficientTransformer();
			List<PlayerCoefficient> pcList = pcTransformer.getAll(rs);
			for (PlayerCoefficient pc : pcList) {
				gameDao=new GameDAO();
				pc.setGame(gameDao.findById(pc.getGame().getId()));
				playerDao = new PlayerDAO();
				pc.setPlayer(playerDao.getPlayer(pc.getPlayer(), "id"));
			}
			return pcList;
		} catch (SQLException e) {
			logger.error("failed to find all PlayerCoefficient from database", e);
			return null;
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
	public List<PlayerCoefficient> findAllCoefficient(double min,double max){
		conn = ConnectionPool.getConnection();
		try {
			String sql = "select * from player_coefficient where coefficient betwen ? and ?;";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, min);
			ps.setDouble(2, max);
			rs = ps.executeQuery();
			pcTransformer=new PlayerCoefficientTransformer();
			List<PlayerCoefficient> pcList = pcTransformer.getAll(rs);
			for (PlayerCoefficient pc : pcList) {
				gameDao=new GameDAO();
				pc.setGame(gameDao.findById(pc.getGame().getId()));
				playerDao = new PlayerDAO();
				pc.setPlayer(playerDao.getPlayer(pc.getPlayer(), "id"));
			}
			return pcList;
		} catch (SQLException e) {
			logger.error("failed to find all PlayerCoefficient from database", e);
			return null;
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
	
	

}
