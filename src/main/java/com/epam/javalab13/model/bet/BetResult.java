package com.epam.javalab13.model.bet;

public class BetResult {
	private SingleBet singleBet;
	private Result result;

	public BetResult(){}

	public BetResult(SingleBet singleBet, Result result) {
		this.singleBet = singleBet;
		this.result = result;
	}

	public SingleBet getSingleBet() {
		return singleBet;
	}

	public void setSingleBet(SingleBet singleBet) {
		this.singleBet = singleBet;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BetResult betResult = (BetResult) o;

		if (singleBet != null ? !singleBet.equals(betResult.singleBet) : betResult.singleBet != null) return false;
		return result == betResult.result;

	}

	@Override
	public int hashCode() {
		int result1 = singleBet != null ? singleBet.hashCode() : 0;
		result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
		return result1;
	}

	@Override
	public String toString() {
		return "BetResult{" +
				"singleBet=" + singleBet +
				", result=" + result +
				'}';
	}
}
