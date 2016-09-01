package com.epam.javalab13.model.bet;

public class BetScore {
	private SingleBet singleBet;
	private int firstTeamScore;
	private int secondTeamScore;

	public BetScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BetScore(SingleBet singleBet, int firstTeamScore, int secondTeamScore) {
		super();
		this.singleBet = singleBet;
		this.firstTeamScore = firstTeamScore;
		this.secondTeamScore = secondTeamScore;
	}

	public SingleBet getSingleBet() {
		return singleBet;
	}

	public void setSingleBet(SingleBet singleBet) {
		this.singleBet = singleBet;
	}

	public int getFirstTeamScore() {
		return firstTeamScore;
	}

	public void setFirstTeamScore(int firstTeamScore) {
		this.firstTeamScore = firstTeamScore;
	}

	public int getSecondTeamScore() {
		return secondTeamScore;
	}

	public void setSecondTeamScore(int secondTeamScore) {
		this.secondTeamScore = secondTeamScore;
	}

	@Override
	public String toString() {
		return "BetScore [firstTeamScore=" + firstTeamScore + ", secondTeamScore=" + secondTeamScore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstTeamScore;
		result = prime * result + secondTeamScore;
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
		BetScore other = (BetScore) obj;
		if (firstTeamScore != other.firstTeamScore)
			return false;
		if (secondTeamScore != other.secondTeamScore)
			return false;
		return true;
	}

}
