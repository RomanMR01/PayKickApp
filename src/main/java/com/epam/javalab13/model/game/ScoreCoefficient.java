package com.epam.javalab13.model.game;

public class ScoreCoefficient {
	private int id;
	private Game game;
	private double startCoefficient;
	private double firstTeamCoefficient;
	private double secondTeamCoefficient;

	public ScoreCoefficient(){}

	public ScoreCoefficient(int id, Game game, double startCoefficient, double firstTeamCoefficient, double secondTeamCoefficient) {
		this.id = id;
		this.game = game;
		this.startCoefficient = startCoefficient;
		this.firstTeamCoefficient = firstTeamCoefficient;
		this.secondTeamCoefficient = secondTeamCoefficient;
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

	public double getStartCoefficient() {
		return startCoefficient;
	}

	public void setStartCoefficient(double startCoefficient) {
		this.startCoefficient = startCoefficient;
	}

	public double getFirstTeamCoefficient() {
		return firstTeamCoefficient;
	}

	public void setFirstTeamCoefficient(double firstTeamCoefficient) {
		this.firstTeamCoefficient = firstTeamCoefficient;
	}

	public double getSecondTeamCoefficient() {
		return secondTeamCoefficient;
	}

	public void setSecondTeamCoefficient(double secondTeamCoefficient) {
		this.secondTeamCoefficient = secondTeamCoefficient;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ScoreCoefficient that = (ScoreCoefficient) o;

		if (id != that.id) return false;
		if (Double.compare(that.startCoefficient, startCoefficient) != 0) return false;
		if (Double.compare(that.firstTeamCoefficient, firstTeamCoefficient) != 0) return false;
		if (Double.compare(that.secondTeamCoefficient, secondTeamCoefficient) != 0) return false;
		return game != null ? game.equals(that.game) : that.game == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (game != null ? game.hashCode() : 0);
		temp = Double.doubleToLongBits(startCoefficient);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(firstTeamCoefficient);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(secondTeamCoefficient);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "ScoreCoefficient{" +
				"id=" + id +
				", game=" + game +
				", startCoefficient=" + startCoefficient +
				", firstTeamCoefficient=" + firstTeamCoefficient +
				", secondTeamCoefficient=" + secondTeamCoefficient +
				'}';
	}
}
