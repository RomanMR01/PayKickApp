package com.epam.javalab13.service.game;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.GameDAO.UpdateGameType;
import com.epam.javalab13.dao.game.PlayerCoefficientDAO;
import com.epam.javalab13.dao.game.ResultCoefficientDAO;
import com.epam.javalab13.dao.game.ScoreCoefficientDAO;
import com.epam.javalab13.dao.game.TotalGoalsCoefficientDAO;
import com.epam.javalab13.model.bet.Result;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.model.game.ScoreCoefficient;
import com.epam.javalab13.model.game.Status;
import com.epam.javalab13.model.game.TotalGoalsCoefficient;

public class CoefficientService {

	private GameDAO gameDao = new GameDAO();
	private ResultCoefficientDAO resCoefDao;
	private ScoreCoefficientDAO scoreCoeffDao;
	private PlayerCoefficientDAO playerCoefficientDAO;
	private TotalGoalsCoefficientDAO totalGoalsCoefficientDAO;
	
	private static Logger logger= Logger.getLogger(CoefficientService.class);
	
	public boolean updateCoefficients(String gameId, String[] resultCoefficients, String[] goalsCoefficients,
			String[] firstTeamPlayerCoefficients, String[] secondTeamPlayerCoefficients, String[] firstTeamPlayerId,
			String[] secondTeamPlayerId) {
		int intGameId = Integer.valueOf(gameId);
		Game game;
		try {
			game = gameDao.getGamesById(intGameId);
			Status gameStatus = game.getStatus();

			updateResultCoefficients(game, resultCoefficients, gameStatus);
			updateGoalsCoefficients(game, goalsCoefficients, gameStatus);
			updatePlayerCoefficients(game, firstTeamPlayerCoefficients, firstTeamPlayerId, gameStatus);
			updatePlayerCoefficients(game, secondTeamPlayerCoefficients, secondTeamPlayerId, gameStatus);
			game.setStatus(Status.ACTIVE);
			gameDao.updateGameByType(game, UpdateGameType.GAME_ACTIVE);
			return true;
		} catch (SQLException e) {
			logger.error("failed to update coefficients", e);
			return false;
		}
	}

	private void updatePlayerCoefficients(Game game, String[] playerCoefficients, String[] playerId, Status gameStatus)
			throws SQLException {
		playerCoefficientDAO = new PlayerCoefficientDAO();
		if (Status.NEW.equals(gameStatus)) {
			for (int i = 0; i < playerId.length; i++) {
				int id = Integer.valueOf(playerId[i]);
				Player player = new Player();
				player.setId(id);
				double coefficient = Double.valueOf(playerCoefficients[i]);
				PlayerCoefficient playerCoefficient = new PlayerCoefficient(0, game, player, coefficient);
				playerCoefficientDAO.addPlayerCoefficient(playerCoefficient);
			}
		} else {
			for (int i = 0; i < playerId.length; i++) {
				int id = Integer.valueOf(playerId[i]);
				Player player = new Player();
				player.setId(id);
				double coefficient = Double.valueOf(playerCoefficients[i]);
				PlayerCoefficient playerCoefficient = new PlayerCoefficient(0, game, player, coefficient);
				playerCoefficientDAO.updateByGameAndPlayer(playerCoefficient);
			}
		}
	}

	private void updateGoalsCoefficients(Game game, String[] goalsCoefficients, Status gameStatus) throws SQLException {
		double startCoefficient = Double.valueOf(goalsCoefficients[1]);
		double firstTeamCoefficient = Double.valueOf(goalsCoefficients[0]);
		double secondTeamCoefficient = Double.valueOf(goalsCoefficients[2]);
		ScoreCoefficient scoreCoeff = new ScoreCoefficient(0, game, startCoefficient, firstTeamCoefficient,
				secondTeamCoefficient);
		scoreCoeffDao = new ScoreCoefficientDAO();
		totalGoalsCoefficientDAO = new TotalGoalsCoefficientDAO();
		TotalGoalsCoefficient totalGoalsCoefficient = new TotalGoalsCoefficient(0, game, startCoefficient);
		if (Status.NEW.equals(gameStatus)) {
			scoreCoeffDao.addScoreCoefficient(scoreCoeff);
			totalGoalsCoefficientDAO.addTotalGoalsCoefficient(totalGoalsCoefficient);
		} else {
			scoreCoeffDao.updateByGame(scoreCoeff);
			totalGoalsCoefficientDAO.updateByGame(totalGoalsCoefficient);
		}
	}

	private void updateResultCoefficients(Game game, String[] resultCoefficients, Status gameStatus)
			throws SQLException {
		Result[] results = Result.values();
		resCoefDao = new ResultCoefficientDAO();
		if (Status.NEW.equals(gameStatus)) {
			for (int i = 0; i < results.length; i++) {
				Result result = results[i];
				double coefficient = Double.valueOf(resultCoefficients[i]);
				ResultCoefficient resCoef = new ResultCoefficient(0, game, result, coefficient);
				resCoefDao.addResultCoefficient(resCoef);
			}
		} else {
			for (int i = 0; i < results.length; i++) {
				Result result = results[i];
				double coefficient = Double.valueOf(resultCoefficients[i]);
				ResultCoefficient resCoef = new ResultCoefficient(0, game, result, coefficient);
				resCoefDao.updateByGame(resCoef);
			}
		}

	}

}
