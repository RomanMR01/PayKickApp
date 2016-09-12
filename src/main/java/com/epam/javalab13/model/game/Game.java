package com.epam.javalab13.model.game;

import java.util.Date;
import java.util.List;

import com.epam.javalab13.model.User;

public class Game implements Comparable<Game> {
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

	private List<ResultCoefficient> resultCoefficients;
	private ScoreCoefficient scoreCoefficient;
	private List<PlayerCoefficient> firstTeamPlayerCoefficients;
	private List<PlayerCoefficient> secondTeamPlayerCoefficients;

	public Game(){}

	public Game(int id, String title, String location, Date date, Team firstTeam, Team secondTeam, int firstGoals, int secondGoals, Status status, User bookmaker, double profit) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Game game = (Game) o;

		if (id != game.id) return false;
		if (firstGoals != game.firstGoals) return false;
		if (secondGoals != game.secondGoals) return false;
		if (Double.compare(game.profit, profit) != 0) return false;
		if (title != null ? !title.equals(game.title) : game.title != null) return false;
		if (location != null ? !location.equals(game.location) : game.location != null) return false;
		if (date != null ? !date.equals(game.date) : game.date != null) return false;
		if (firstTeam != null ? !firstTeam.equals(game.firstTeam) : game.firstTeam != null) return false;
		if (secondTeam != null ? !secondTeam.equals(game.secondTeam) : game.secondTeam != null) return false;
		if (status != game.status) return false;
		return bookmaker != null ? bookmaker.equals(game.bookmaker) : game.bookmaker == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (firstTeam != null ? firstTeam.hashCode() : 0);
		result = 31 * result + (secondTeam != null ? secondTeam.hashCode() : 0);
		result = 31 * result + firstGoals;
		result = 31 * result + secondGoals;
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (bookmaker != null ? bookmaker.hashCode() : 0);
		temp = Double.doubleToLongBits(profit);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Game{" +
				"id=" + id +
				", title='" + title + '\'' +
				", location='" + location + '\'' +
				", date=" + date +
				", firstTeam=" + firstTeam +
				", secondTeam=" + secondTeam +
				", firstGoals=" + firstGoals +
				", secondGoals=" + secondGoals +
				", status=" + status +
				", bookmaker=" + bookmaker +
				", profit=" + profit +
				'}';
	}

	@Override
	public int compareTo(Game o) {
		return getDate().compareTo(o.getDate());
	}

	public List<ResultCoefficient> getResultCoefficients() {
		return resultCoefficients;
	}

	public void setResultCoefficients(List<ResultCoefficient> resultCoefficients) {
		this.resultCoefficients = resultCoefficients;
	}

	public ScoreCoefficient getScoreCoefficient() {
		return scoreCoefficient;
	}

	public void setScoreCoefficient(ScoreCoefficient scoreCoefficient) {
		this.scoreCoefficient = scoreCoefficient;
	}

	public List<PlayerCoefficient> getFirstTeamPlayerCoefficients() {
		return firstTeamPlayerCoefficients;
	}

	public void setFirstTeamPlayerCoefficients(List<PlayerCoefficient> firstTeamPlayerCoefficients) {
		this.firstTeamPlayerCoefficients = firstTeamPlayerCoefficients;
	}

	public List<PlayerCoefficient> getSecondTeamPlayerCoefficients() {
		return secondTeamPlayerCoefficients;
	}

	public void setSecondTeamPlayerCoefficients(List<PlayerCoefficient> secondTeamPlayerCoefficients) {
		this.secondTeamPlayerCoefficients = secondTeamPlayerCoefficients;
	}
}
