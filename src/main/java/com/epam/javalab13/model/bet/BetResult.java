package com.epam.javalab13.model.bet;

public class BetResult {
	private SingleBet singleBet;
	private Result result;

	public BetResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BetResult(SingleBet singleBet, Result result) {
		super();
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
	public String toString() {
		return "BetResult [result=" + result + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		BetResult other = (BetResult) obj;
		if (result != other.result)
			return false;
		return true;
	}

}
