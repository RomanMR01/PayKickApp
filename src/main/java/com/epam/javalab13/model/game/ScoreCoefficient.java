package com.epam.javalab13.model.game;

public class ScoreCoefficient {
	private int id;
	private Game game;
	private double startCoefficient;
	private double firstTeamCoefficient;
	private double secondTeamCoefficient;
	public ScoreCoefficient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScoreCoefficient(Game game, double startCoefficient, double firstTeamCoefficient,
			double secondTeamCoefficient) {
		super();
		this.game = game;
		this.startCoefficient = startCoefficient;
		this.firstTeamCoefficient = firstTeamCoefficient;
		this.secondTeamCoefficient = secondTeamCoefficient;
	}
	public ScoreCoefficient(int id, Game game, double startCoefficient, double firstTeamCoefficient,
			double secondTeamCoefficient) {
		super();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(firstTeamCoefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + id;
		temp = Double.doubleToLongBits(secondTeamCoefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(startCoefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ScoreCoefficient other = (ScoreCoefficient) obj;
		if (Double.doubleToLongBits(firstTeamCoefficient) != Double.doubleToLongBits(other.firstTeamCoefficient))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(secondTeamCoefficient) != Double.doubleToLongBits(other.secondTeamCoefficient))
			return false;
		if (Double.doubleToLongBits(startCoefficient) != Double.doubleToLongBits(other.startCoefficient))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ScoreCoefficient [id=" + id + ", game=" + game + ", startCoefficient=" + startCoefficient
				+ ", firstTeamCoefficient=" + firstTeamCoefficient + ", secondTeamCoefficient=" + secondTeamCoefficient
				+ "]";
	}
	
	
}
