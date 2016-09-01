package com.epam.javalab13.model.game;

public class PlayerCoefficient {
	private int id;
	private Game game;
	private Player player;
	private double coefficient;
	public PlayerCoefficient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlayerCoefficient(Game game, Player player, double coefficient) {
		super();
		this.game = game;
		this.player = player;
		this.coefficient = coefficient;
	}
	public PlayerCoefficient(int id, Game game, Player player, double coefficient) {
		super();
		this.id = id;
		this.game = game;
		this.player = player;
		this.coefficient = coefficient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + id;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		PlayerCoefficient other = (PlayerCoefficient) obj;
		if (Double.doubleToLongBits(coefficient) != Double.doubleToLongBits(other.coefficient))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id != other.id)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlayerCoefficient [id=" + id + ", game=" + game + ", player=" + player + ", coefficient=" + coefficient
				+ "]";
	}
	
	
	
}
