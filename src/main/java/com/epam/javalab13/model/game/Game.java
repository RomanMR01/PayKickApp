package com.epam.javalab13.model.game;

import java.util.Date;

import com.epam.javalab13.model.User;

public class Game {
	private int id;
	private String title;
	private String location;
	private Date date;
	private Team firstTeam;
	private Team secondTeam;
	private int firstGoals;
	private int secondGoals;
	private Status status;
	private User bookmaker;
	private double profit;

	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param title
	 * @param location
	 * @param date
	 * @param firstTeam
	 * @param secondTeam
	 * @param status
	 */
	public Game(String title, String location, Date date, Team firstTeam, Team secondTeam, Status status) {
		super();
		this.title = title;
		this.location = location;
		this.date = date;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.status = status;
	}
	
	/**
	 * 
	 * @param title
	 * @param location
	 * @param date
	 * @param firstTeam
	 * @param secondTeam
	 * @param status
	 * @param bookmaker
	 * @param profit
	 */
	public Game(String title, String location, Date date, Team firstTeam, Team secondTeam, Status status,
			User bookmaker, double profit) {
		super();
		this.title = title;
		this.location = location;
		this.date = date;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.status = status;
		this.bookmaker = bookmaker;
		this.profit = profit;
	}
	
	/**
	 * 
	 * @param title
	 * @param location
	 * @param date
	 * @param firstTeam
	 * @param secondTeam
	 * @param firstGoals
	 * @param secondGoals
	 * @param status
	 * @param bookmaker
	 * @param profit
	 */
	public Game(String title, String location, Date date, Team firstTeam, Team secondTeam, int firstGoals,
			int secondGoals, Status status, User bookmaker, double profit) {
		super();
		this.title = title;
		this.location = location;
		this.date = date;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.firstGoals = firstGoals;
		this.secondGoals = secondGoals;
		this.status = status;
		this.bookmaker = bookmaker;
		this.profit = profit;
	}
	
	/**
	 * 
	 * @param id
	 * @param title
	 * @param location
	 * @param date
	 * @param firstTeam
	 * @param secondTeam
	 * @param firstGoals
	 * @param secondGoals
	 * @param status
	 * @param bookmaker
	 * @param profit
	 */
	public Game(int id, String title, String location, Date date, Team firstTeam, Team secondTeam, int firstGoals,
			int secondGoals, Status status, User bookmaker, double profit) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.date = date;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.firstGoals = firstGoals;
		this.secondGoals = secondGoals;
		this.status = status;
		this.bookmaker = bookmaker;
		this.profit = profit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Team getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}

	public Team getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}

	public int getFirstGoals() {
		return firstGoals;
	}

	public void setFirstGoals(int firstGoals) {
		this.firstGoals = firstGoals;
	}

	public int getSecondGoals() {
		return secondGoals;
	}

	public void setSecondGoals(int secondGoals) {
		this.secondGoals = secondGoals;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(User bookmaker) {
		this.bookmaker = bookmaker;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookmaker == null) ? 0 : bookmaker.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + firstGoals;
		result = prime * result + ((firstTeam == null) ? 0 : firstTeam.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		long temp;
		temp = Double.doubleToLongBits(profit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + secondGoals;
		result = prime * result + ((secondTeam == null) ? 0 : secondTeam.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Game other = (Game) obj;
		if (bookmaker == null) {
			if (other.bookmaker != null)
				return false;
		} else if (!bookmaker.equals(other.bookmaker))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (firstGoals != other.firstGoals)
			return false;
		if (firstTeam == null) {
			if (other.firstTeam != null)
				return false;
		} else if (!firstTeam.equals(other.firstTeam))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(profit) != Double.doubleToLongBits(other.profit))
			return false;
		if (secondGoals != other.secondGoals)
			return false;
		if (secondTeam == null) {
			if (other.secondTeam != null)
				return false;
		} else if (!secondTeam.equals(other.secondTeam))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", location=" + location + ", date=" + date + ", firstTeam="
				+ firstTeam + ", secondTeam=" + secondTeam + ", firstGoals=" + firstGoals + ", secondGoals="
				+ secondGoals + ", status=" + status + ", bookmaker=" + bookmaker + ", profit=" + profit + "]";
	}

}
