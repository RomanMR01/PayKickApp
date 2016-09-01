package com.epam.javalab13.model.game;

import java.util.List;

public class Team {

	private int id;
	private String name;
	private String location;
	private String emblemUrl;
	private int totalWins;
	private int totalLoses;
	private int totalDraws;
	
	//join team_league tl on tl.team_id=team.id 
	private List<League> leagues;
	
	private List<Player> players;
	
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Team(String name, String location, String emblemUrl) {
		super();
		this.name = name;
		this.location = location;
		this.emblemUrl = emblemUrl;
	}
	public Team(String name, String location, String emblemUrl, List<League> leagues) {
		super();
		this.name = name;
		this.location = location;
		this.emblemUrl = emblemUrl;
		this.leagues = leagues;
	}
	public Team(int id, String name, String location, String emblemUrl, int totalWins, int totalLoses, int totalDraws,
			List<League> leagues) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.emblemUrl = emblemUrl;
		this.totalWins = totalWins;
		this.totalLoses = totalLoses;
		this.totalDraws = totalDraws;
		this.leagues = leagues;
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
	public List<League> getLeagues() {
		return leagues;
	}
	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emblemUrl == null) ? 0 : emblemUrl.hashCode());
		result = prime * result + id;
		result = prime * result + ((leagues == null) ? 0 : leagues.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + totalDraws;
		result = prime * result + totalLoses;
		result = prime * result + totalWins;
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
		Team other = (Team) obj;
		if (emblemUrl == null) {
			if (other.emblemUrl != null)
				return false;
		} else if (!emblemUrl.equals(other.emblemUrl))
			return false;
		if (id != other.id)
			return false;
		if (leagues == null) {
			if (other.leagues != null)
				return false;
		} else if (!leagues.equals(other.leagues))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalDraws != other.totalDraws)
			return false;
		if (totalLoses != other.totalLoses)
			return false;
		if (totalWins != other.totalWins)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", location=" + location + ", emblemUrl=" + emblemUrl
				+ ", totalWins=" + totalWins + ", totalLoses=" + totalLoses + ", totalDraws=" + totalDraws
				+ ", leagues=" + leagues + "]";
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
}
