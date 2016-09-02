package com.epam.javalab13.model.game;

import com.epam.javalab13.model.bet.Result;

public class ResultCoefficient {
private int id;
private Game game;
private Result result;
private double coefficient;
public ResultCoefficient() {
	super();
	// TODO Auto-generated constructor stub
}
public ResultCoefficient(Game game, Result result, double coefficient) {
	super();
	this.game = game;
	this.result = result;
	this.coefficient = coefficient;
}
public ResultCoefficient(int id, Game game, Result result, double coefficient) {
	super();
	this.id = id;
	this.game = game;
	this.result = result;
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
public Result getResult() {
	return result;
}
public void setResult(Result result) {
	this.result = result;
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
	result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
	ResultCoefficient other = (ResultCoefficient) obj;
	if (Double.doubleToLongBits(coefficient) != Double.doubleToLongBits(other.coefficient))
		return false;
	if (game == null) {
		if (other.game != null)
			return false;
	} else if (!game.equals(other.game))
		return false;
	if (id != other.id)
		return false;
	if (result != other.result)
		return false;
	return true;
}
@Override
public String toString() {
	return "ResultCoefficient [id=" + id + ", game=" + game + ", result=" + result + ", coefficient=" + coefficient
			+ "]";
}

}
