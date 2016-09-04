package com.epam.javalab13.transformer.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.ScoreCoefficient;
import com.epam.javalab13.transformer.Transformer;

public class ScoreCoefficientTransformer implements Transformer<ScoreCoefficient> {
	
	private static final Logger logger = Logger.getLogger(ScoreCoefficientTransformer.class);
	private GameTransformer gameTransformer;
	
	@Override
	public ScoreCoefficient getOne(ResultSet rs) {
		ScoreCoefficient sc= null;
		try {
			if(rs.next()){
				sc=createScoreCoefficient(rs);
			}
		} catch (SQLException e) {
			logger.error("failed to instantiate ScoreCoefficient from ResultSet", e);
		}
		return sc;
	}
	
	
	private ScoreCoefficient createScoreCoefficient(ResultSet rs) throws SQLException {
		ScoreCoefficient sc = new ScoreCoefficient();
		sc.setId(rs.getInt("score_id"));
		sc.setStartCoefficient(rs.getDouble("start_coefficient"));
		sc.setFirstTeamCoefficient(rs.getDouble("first_team_coefficient"));
		sc.setSecondTeamCoefficient(rs.getDouble("second_team_coefficient"));
		gameTransformer=new GameTransformer();
		Game game = gameTransformer.createGame(rs);
		sc.setGame(game);
		return sc;
	}



	@Override
	public List<ScoreCoefficient> getAll(ResultSet rs) {
		List<ScoreCoefficient> scoreCoefficients = new ArrayList<>();
		try {
			while(rs.next()){
				scoreCoefficients.add(createScoreCoefficient(rs));
			}
		} catch (SQLException e) {
			logger.error("failed to instantiate ScoreCoefficient from ResultSet", e);
		}
		return scoreCoefficients;
	}
}
