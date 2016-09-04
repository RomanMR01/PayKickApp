package com.epam.javalab13.model.bet;

import com.epam.javalab13.model.game.Player;

public class BetPlayer {
	private SingleBet singleBet;
	private Player player;

	public BetPlayer(){}

	public BetPlayer(SingleBet singleBet, Player player) {
		this.singleBet = singleBet;
		this.player = player;
	}

	public SingleBet getSingleBet() {
		return singleBet;
	}

	public void setSingleBet(SingleBet singleBet) {
		this.singleBet = singleBet;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BetPlayer betPlayer = (BetPlayer) o;

		if (singleBet != null ? !singleBet.equals(betPlayer.singleBet) : betPlayer.singleBet != null) return false;
		return player != null ? player.equals(betPlayer.player) : betPlayer.player == null;

	}

	@Override
	public int hashCode() {
		int result = singleBet != null ? singleBet.hashCode() : 0;
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "BetPlayer{" +
				"singleBet=" + singleBet +
				", player=" + player +
				'}';
	}
}
