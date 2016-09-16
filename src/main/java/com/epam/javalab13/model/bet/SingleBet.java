package com.epam.javalab13.model.bet;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Player;

public class SingleBet {

	private int id;
	private TotalBet totalBet;
	private Game game;
	private Category category;
	private double coefficient;
	private Status status;

	private BetResult betResult;
	private BetPlayer betPlayer;
	private BetScore betScore;
	private BetTotalGoals betTotalGoals;

	public SingleBet(){}

	public SingleBet(int id, TotalBet totalBet, Game game, Category category, double coefficient, Status status) {
		this.id = id;
		this.totalBet = totalBet;
		this.game = game;
		this.category = category;
		this.coefficient = coefficient;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TotalBet getTotalBet() {
		return totalBet;
	}

	public void setTotalBet(TotalBet totalBet) {
		this.totalBet = totalBet;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SingleBet singleBet = (SingleBet) o;

		if (id != singleBet.id) return false;
		if (Double.compare(singleBet.coefficient, coefficient) != 0) return false;
		if (totalBet != null ? !totalBet.equals(singleBet.totalBet) : singleBet.totalBet != null) return false;
		if (game != null ? !game.equals(singleBet.game) : singleBet.game != null) return false;
		if (category != singleBet.category) return false;
		return status == singleBet.status;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (totalBet != null ? totalBet.hashCode() : 0);
		result = 31 * result + (game != null ? game.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		temp = Double.doubleToLongBits(coefficient);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SingleBet{" +
				"id=" + id +
				", totalBet=" + totalBet +
				", game=" + game +
				", category=" + category +
				", coefficient=" + coefficient +
				", status=" + status +
				'}';
	}

	public BetResult getBetResult() {
		return betResult;
	}

	public void setBetResult(BetResult betResult) {
		this.betResult = betResult;
	}

	public BetPlayer getBetPlayer() {
		return betPlayer;
	}

	public void setBetPlayer(BetPlayer betPlayer) {
		this.betPlayer = betPlayer;
	}

	public BetScore getBetScore() {
		return betScore;
	}

	public void setBetScore(BetScore betScore) {
		this.betScore = betScore;
	}

	public BetTotalGoals getBetTotalGoals() {
		return betTotalGoals;
	}

	public void setBetTotalGoals(BetTotalGoals betTotalGoals) {
		this.betTotalGoals = betTotalGoals;
	}
}
