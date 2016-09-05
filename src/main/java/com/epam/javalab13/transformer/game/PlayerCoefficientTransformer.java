package com.epam.javalab13.transformer.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.PlayerCoefficient;
import com.epam.javalab13.transformer.Transformer;

public class PlayerCoefficientTransformer implements Transformer<PlayerCoefficient> {

	@Override
	public PlayerCoefficient getOne(ResultSet rs) throws SQLException {
		PlayerCoefficient pc = null;
		if(rs.next()){
			pc = createPlayerCoefficient(rs);
		}
		return pc;
	}
	
	
	private PlayerCoefficient createPlayerCoefficient(ResultSet rs) throws SQLException {
		PlayerCoefficient pc = new PlayerCoefficient();
		pc.setId(rs.getInt("id"));
		pc.setCoefficient(rs.getDouble("coefficient"));
		Game game=new Game();
		game.setId(rs.getInt("game_id"));
		pc.setGame(game);
		Player player = new Player();
		player.setId(rs.getInt("player_id"));
		pc.setPlayer(player);
		return pc;
	}


	@Override
	public List<PlayerCoefficient> getAll(ResultSet rs) throws SQLException {
		List<PlayerCoefficient> pcList=new ArrayList<>();
		while(rs.next()){
			pcList.add(createPlayerCoefficient(rs));
		}
		return pcList;
	}

}
