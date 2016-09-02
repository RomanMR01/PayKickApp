package com.epam.javalab13.model.game;

import java.util.List;

public class League {
private int id;
private String name;

//join team_league tl on tl.league_id=league.id 
private List<Team> teams;
public League() {
	super();
	// TODO Auto-generated constructor stub
}
public League(String name) {
	super();
	this.name = name;
}
public League(int id, String name, List<Team> teams) {
	super();
	this.id = id;
	this.name = name;
	this.teams = teams;
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
public List<Team> getTeams() {
	return teams;
}
public void setTeams(List<Team> teams) {
	this.teams = teams;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	League other = (League) obj;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}
@Override
public String toString() {
	return "League [id=" + id + ", name=" + name + ", teams=" + teams + "]";
}

}
