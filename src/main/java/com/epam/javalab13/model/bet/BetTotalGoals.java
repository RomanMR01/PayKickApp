package com.epam.javalab13.model.bet;

public class BetTotalGoals {
	private SingleBet singleBet;
	private int totalGoal;

	public BetTotalGoals() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BetTotalGoals(SingleBet singleBet, int totalGoal) {
		super();
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
	public String toString() {
		return "BetTotalGoals [totalGoal=" + totalGoal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + totalGoal;
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
		BetTotalGoals other = (BetTotalGoals) obj;
		if (totalGoal != other.totalGoal)
			return false;
		return true;
	}

}
