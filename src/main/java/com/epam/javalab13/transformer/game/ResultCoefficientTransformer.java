package com.epam.javalab13.transformer.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.model.bet.Result;
import com.epam.javalab13.model.game.ResultCoefficient;
import com.epam.javalab13.transformer.Transformer;

public class ResultCoefficientTransformer implements Transformer<ResultCoefficient> {

	private static final Logger logger = Logger.getLogger(ResultCoefficientTransformer.class);
	private GameTransformer gameTransformer;

	@Override
	public ResultCoefficient getOne(ResultSet rs) {
		ResultCoefficient rc = null;
		try {
			if (rs.next()) {
				rc = createResultCoefficient(rs);
			}
		} catch (SQLException e) {
			logger.error("failed to instantiate ResultCoefficient from ResultSet",e);
		}
		return rc;
	}

	private ResultCoefficient createResultCoefficient(ResultSet rs) throws SQLException {
		ResultCoefficient rc = new ResultCoefficient();
		rc.setId(rs.getInt("result_id"));
		rc.setCoefficient(rs.getDouble("coefficient"));
		rc.setResult(Result.valueOf(rs.getString("result")));
		if (gameTransformer == null) {
			gameTransformer = new GameTransformer();
		}
		rc.setGame(gameTransformer.createGame(rs));
		return rc;
	}

	@Override
	public List<ResultCoefficient> getAll(ResultSet rs) {
		List<ResultCoefficient> rcList = new ArrayList<>();
		try {
			while(rs.next()){
				rcList.add(createResultCoefficient(rs));
			}
		} catch (SQLException e) {
			logger.error("failed to instantiate ResultCoefficient from ResultSet",e);
		}
		return rcList;
	}

}
