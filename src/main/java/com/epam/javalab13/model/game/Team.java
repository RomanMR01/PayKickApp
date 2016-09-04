package com.epam.javalab13.model.game;

public class Team {

	private int id;
	private String name;
	private String location;
	private String emblemUrl;
	private int totalWins;
	private int totalLoses;
	private int totalDraws;

	public Team(){}

	public Team(int id, String name, String location, String emblemUrl, int totalWins, int totalLoses, int totalDraws) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.emblemUrl = emblemUrl;
		this.totalWins = totalWins;
		this.totalLoses = totalLoses;
		this.totalDraws = totalDraws;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmblemUrl() {
		return emblemUrl;
	}

	public void setEmblemUrl(String emblemUrl) {
		this.emblemUrl = emblemUrl;
	}

	public int getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}

	public int getTotalLoses() {
		return totalLoses;
	}

	public void setTotalLoses(int totalLoses) {
		this.totalLoses = totalLoses;
	}

	public int getTotalDraws() {
		return totalDraws;
	}

	public void setTotalDraws(int totalDraws) {
		this.totalDraws = totalDraws;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Team team = (Team) o;

		if (id != team.id) return false;
		if (totalWins != team.totalWins) return false;
		if (totalLoses != team.totalLoses) return false;
		if (totalDraws != team.totalDraws) return false;
		if (name != null ? !name.equals(team.name) : team.name != null) return false;
		if (location != null ? !location.equals(team.location) : team.location != null) return false;
		return emblemUrl != null ? emblemUrl.equals(team.emblemUrl) : team.emblemUrl == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (emblemUrl != null ? emblemUrl.hashCode() : 0);
		result = 31 * result + totalWins;
		result = 31 * result + totalLoses;
		result = 31 * result + totalDraws;
		return result;
	}

	@Override
	public String toString() {
		return "Team{" +
				"id=" + id +
				", name='" + name + '\'' +
				", location='" + location + '\'' +
				", emblemUrl='" + emblemUrl + '\'' +
				", totalWins=" + totalWins +
				", totalLoses=" + totalLoses +
				", totalDraws=" + totalDraws +
				'}';
	}
}
