package com.epam.javalab13.model.bet;

import com.epam.javalab13.model.game.Player;

public class BetPlayer {
	private int id;
	private SingleBet singleBet;
	private Player player;

	public BetPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BetPlayer(SingleBet singleBet, Player player) {
		super();
		this.singleBet = singleBet;
		this.player = player;
	}

	public BetPlayer(int id, SingleBet singleBet, Player player) {
		super();
		this.id = id;
		this.singleBet = singleBet;
		this.player = player;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((singleBet == null) ? 0 : singleBet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BetPlayer other = (BetPlayer) obj;
		if (id != other.id)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (singleBet == null) {
			if (other.singleBet != null)
				return false;
		} else if (!singleBet.equals(other.singleBet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BetPlayer [id=" + id + ", singleBet=" + singleBet + ", player=" + player + "]";
	}

}
