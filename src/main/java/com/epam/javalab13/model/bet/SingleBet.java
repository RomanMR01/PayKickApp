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
	private BetScore betScore;
	private BetTotalGoals betTotalGoals;
	private BetPlayer betPlayer;

	public SingleBet() {
	}

	/**
	 * Bet on the result
	 * 
	 * @param totalBet
	 * @param game
	 * @param category
	 * @param coefficient
	 * @param status
	 * @param betResult
	 */
	public SingleBet(int id,TotalBet totalBet, Game game, Category category, double coefficient, Status status,
			BetResult betResult) {
		this.id = id;
		this.totalBet = totalBet;
		this.game = game;
		this.category = category;
		this.coefficient = coefficient;
		this.status = status;
		this.betResult = betResult;
	}

	/**
	 * Bet on the score
	 * 
	 * @param totalBet
	 * @param game
	 * @param category
	 * @param coefficient
	 * @param status
	 * @param betScore
	 */
	public SingleBet(int id, TotalBet totalBet, Game game, Category category, double coefficient, Status status,
			BetScore betScore) {
		this.id = id;
		this.totalBet = totalBet;
		this.game = game;
		this.category = category;
		this.coefficient = coefficient;
		this.status = status;
		this.betScore = betScore;
	}

	/**
	 * Bet on the total goals
	 * 
	 * @param totalBet
	 * @param game
	 * @param category
	 * @param coefficient
	 * @param status
	 * @param betTotalGoals
	 */
	public SingleBet(int id,TotalBet totalBet, Game game, Category category, double coefficient, Status status,
			BetTotalGoals betTotalGoals) {
		this.id = id;
		this.totalBet = totalBet;
		this.game = game;
		this.category = category;
		this.coefficient = coefficient;
		this.status = status;
		this.betTotalGoals = betTotalGoals;
	}

	/**
	 * Bet on the player
	 * 
	 * @param totalBet
	 * @param game
	 * @param category
	 * @param coefficient
	 * @param status
	 * @param player
	 */
	public SingleBet(int id, TotalBet totalBet, Game game, Category category, double coefficient, Status status,
			BetPlayer player) {
		this.id = id;
		this.totalBet = totalBet;
		this.game = game;
		this.category = category;
		this.coefficient = coefficient;
		this.status = status;
		this.betPlayer = player;
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

	public BetResult getBetResult() {
		return betResult;
	}

	public void setBetResult(BetResult betResult) {
		this.betResult = betResult;
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

	public BetPlayer getBetPlayer() {
		return betPlayer;
	}

	public void setBetPlayer(BetPlayer betPlayer) {
		this.betPlayer = betPlayer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((betResult == null) ? 0 : betResult.hashCode());
		result = prime * result + ((betScore == null) ? 0 : betScore.hashCode());
		result = prime * result + ((betTotalGoals == null) ? 0 : betTotalGoals.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		long temp;
		temp = Double.doubleToLongBits(coefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalBet == null) ? 0 : totalBet.hashCode());
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
		SingleBet other = (SingleBet) obj;
		if (betResult == null) {
			if (other.betResult != null)
				return false;
		} else if (!betResult.equals(other.betResult))
			return false;
		if (betScore == null) {
			if (other.betScore != null)
				return false;
		} else if (!betScore.equals(other.betScore))
			return false;
		if (betTotalGoals == null) {
			if (other.betTotalGoals != null)
				return false;
		} else if (!betTotalGoals.equals(other.betTotalGoals))
			return false;
		if (category != other.category)
			return false;
		if (Double.doubleToLongBits(coefficient) != Double.doubleToLongBits(other.coefficient))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (totalBet == null) {
			if (other.totalBet != null)
				return false;
		} else if (!totalBet.equals(other.totalBet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SingleBet [id=" + id + ", totalBet=" + totalBet + ", game=" + game + ", category=" + category
				+ ", coefficient=" + coefficient + ", status=" + status + ", betResult=" + betResult + ", betScore="
				+ betScore + ", betTotalGoals=" + betTotalGoals + "]";
	}

}
