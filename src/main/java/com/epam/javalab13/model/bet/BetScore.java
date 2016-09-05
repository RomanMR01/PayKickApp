package com.epam.javalab13.model.bet;

public class BetScore {
	private SingleBet singleBet;
	private int firstTeamScore;
	private int secondTeamScore;

	public BetScore(){}

	public BetScore(SingleBet singleBet, int firstTeamScore, int secondTeamScore) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BetScore betScore = (BetScore) o;

		if (firstTeamScore != betScore.firstTeamScore) return false;
		if (secondTeamScore != betScore.secondTeamScore) return false;
		return singleBet != null ? singleBet.equals(betScore.singleBet) : betScore.singleBet == null;

	}

	@Override
	public int hashCode() {
		int result = singleBet != null ? singleBet.hashCode() : 0;
		result = 31 * result + firstTeamScore;
		result = 31 * result + secondTeamScore;
		return result;
	}

	@Override
	public String toString() {
		return "BetScore{" +
				"singleBet=" + singleBet +
				", firstTeamScore=" + firstTeamScore +
				", secondTeamScore=" + secondTeamScore +
				'}';
	}
}
