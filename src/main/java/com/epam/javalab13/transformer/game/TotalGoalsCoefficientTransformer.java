package com.epam.javalab13.transformer.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.model.game.TotalGoalsCoefficient;
import com.epam.javalab13.transformer.Transformer;

public class TotalGoalsCoefficientTransformer implements Transformer<TotalGoalsCoefficient> {

	private static final Logger logger = Logger.getLogger(TotalGoalsCoefficientTransformer.class);
	private GameTransformer gameTransformer;

	@Override
	public TotalGoalsCoefficient getOne(ResultSet rs) {
		TotalGoalsCoefficient tgc=null;
		try {
			if(rs.next()){
				tgc=createTotalGoalsCoefficient(rs);
			}
		} catch (SQLException e) {
			logger.error("failed to instantiate TotalGoalsCoefficient from ResultSet", e);
		}
		return tgc;
	}

	private TotalGoalsCoefficient createTotalGoalsCoefficient(ResultSet rs) throws SQLException {
		TotalGoalsCoefficient tgc = new TotalGoalsCoefficient();
		tgc.setId(rs.getInt("id"));
		tgc.setGoalCoefficient(rs.getDouble("goal_coefficient"));
		tgc.setGame(gameTransformer.createGame(rs));
		return tgc;
	}
	
	@Override
	public List<TotalGoalsCoefficient> getAll(ResultSet rs) {
		List<TotalGoalsCoefficient> tgcoefficients = new ArrayList<>();
		try {
			while(rs.next()){
				tgcoefficients.add(createTotalGoalsCoefficient(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tgcoefficients;
	}

}
