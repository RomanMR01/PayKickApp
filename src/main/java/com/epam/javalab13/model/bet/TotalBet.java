package com.epam.javalab13.model.bet;

import java.util.Date;
import java.util.List;

import com.epam.javalab13.model.User;

public class TotalBet {
	private int id;
	private User user;
	private Type type;
	private int amount;
	private Date date;
	private double award;
	private Status status;
	
	private List<SingleBet> singleBets;
	
	public TotalBet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TotalBet(Type type, int amount, Date date, double award, Status status) {
		super();
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.award = award;
		this.status = status;
	}
	public TotalBet(User user, Type type, int amount, Date date, double award, Status status) {
		super();
		this.user = user;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.award = award;
		this.status = status;
	}
	public TotalBet(int id, User user, Type type, int amount, Date date, double award, Status status) {
		super();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		long temp;
		temp = Double.doubleToLongBits(award);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		TotalBet other = (TotalBet) obj;
		if (amount != other.amount)
			return false;
		if (Double.doubleToLongBits(award) != Double.doubleToLongBits(other.award))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TotalBet [id=" + id + ", user=" + user + ", type=" + type + ", amount=" + amount + ", date=" + date
				+ ", award=" + award + ", status=" + status + "]";
	}
	public List<SingleBet> getSingleBets() {
		return singleBets;
	}
	public void setSingleBets(List<SingleBet> singleBets) {
		this.singleBets = singleBets;
	}
	
}
