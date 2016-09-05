package com.epam.javalab13.model.game;

public class PlayerCoefficient {
	private int id;
	private Game game;
	private Player player;
	private double coefficient;

	public PlayerCoefficient(){}

	public PlayerCoefficient(int id, Game game, Player player, double coefficient) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PlayerCoefficient that = (PlayerCoefficient) o;

		if (id != that.id) return false;
		if (Double.compare(that.coefficient, coefficient) != 0) return false;
		if (game != null ? !game.equals(that.game) : that.game != null) return false;
		return player != null ? player.equals(that.player) : that.player == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (game != null ? game.hashCode() : 0);
		result = 31 * result + (player != null ? player.hashCode() : 0);
		temp = Double.doubleToLongBits(coefficient);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "PlayerCoefficient{" +
				"id=" + id +
				", game=" + game +
				", player=" + player +
				", coefficient=" + coefficient +
				'}';
	}
}
