package com.epam.javalab13.model.bet;

import com.epam.javalab13.model.User;
import java.util.Date;

public class TotalBet implements Comparable<TotalBet> {
	private int id;
	private User user;
	private Type type;
	private int amount;
	private Date date;
	private double award;
	private Status status;

	public TotalBet() {
	}

	public TotalBet(int id, User user, Type type, int amount, Date date, double award, Status status) {
		this.id = id;
		this.user = user;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.award = award;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAward() {
		return award;
	}

	public void setAward(double award) {
		this.award = award;
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

		TotalBet totalBet = (TotalBet) o;

		if (id != totalBet.id) return false;
		if (amount != totalBet.amount) return false;
		if (Double.compare(totalBet.award, award) != 0) return false;
		if (user != null ? !user.equals(totalBet.user) : totalBet.user != null) return false;
		if (type != totalBet.type) return false;
		if (date != null ? !date.equals(totalBet.date) : totalBet.date != null) return false;
		return status == totalBet.status;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + amount;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		temp = Double.doubleToLongBits(award);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TotalBet{" +
				"id=" + id +
				", user=" + user +
				", type=" + type +
				", amount=" + amount +
				", date=" + date +
				", award=" + award +
				", status=" + status +
				'}';
	}

	@Override
	public int compareTo(TotalBet o) {
		return getDate().compareTo(o.getDate());
	}
}
