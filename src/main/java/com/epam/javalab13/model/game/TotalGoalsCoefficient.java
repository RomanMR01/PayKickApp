package com.epam.javalab13.model.game;

public class TotalGoalsCoefficient {
private int id;
private Game game;
private double goalCoefficient;
public TotalGoalsCoefficient() {
	super();
	// TODO Auto-generated constructor stub
}
public TotalGoalsCoefficient(Game game, double goalCoefficient) {
	super();
	this.game = game;
	this.goalCoefficient = goalCoefficient;
}
public TotalGoalsCoefficient(int id, Game game, double goalCoefficient) {
	super();
	this.id = id;
	this.game = game;
	this.goalCoefficient = goalCoefficient;
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
public double getGoalCoefficient() {
	return goalCoefficient;
}
public void setGoalCoefficient(double goalCoefficient) {
	this.goalCoefficient = goalCoefficient;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((game == null) ? 0 : game.hashCode());
	long temp;
	temp = Double.doubleToLongBits(goalCoefficient);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + id;
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
	TotalGoalsCoefficient other = (TotalGoalsCoefficient) obj;
	if (game == null) {
		if (other.game != null)
			return false;
	} else if (!game.equals(other.game))
		return false;
	if (Double.doubleToLongBits(goalCoefficient) != Double.doubleToLongBits(other.goalCoefficient))
		return false;
	if (id != other.id)
		return false;
	return true;
}
@Override
public String toString() {
	return "TotalGoalsCoefficient [id=" + id + ", game=" + game + ", goalCoefficient=" + goalCoefficient + "]";
}

}
