package com.epam.javalab13.model.bet;

public class BetTotalGoals {
	private SingleBet singleBet;
	private int totalGoal;

	public BetTotalGoals(){}

	public BetTotalGoals(SingleBet singleBet, int totalGoal) {
		this.singleBet = singleBet;
		this.totalGoal = totalGoal;
	}

	public SingleBet getSingleBet() {
		return singleBet;
	}

	public void setSingleBet(SingleBet singleBet) {
		this.singleBet = singleBet;
	}

	public int getTotalGoal() {
		return totalGoal;
	}

	public void setTotalGoal(int totalGoal) {
		this.totalGoal = totalGoal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BetTotalGoals that = (BetTotalGoals) o;

		if (totalGoal != that.totalGoal) return false;
		return singleBet != null ? singleBet.equals(that.singleBet) : that.singleBet == null;

	}

	@Override
	public int hashCode() {
		int result = singleBet != null ? singleBet.hashCode() : 0;
		result = 31 * result + totalGoal;
		return result;
	}

	@Override
	public String toString() {
		return "BetTotalGoals{" +
				"singleBet=" + singleBet +
				", totalGoal=" + totalGoal +
				'}';
	}
}
