package com.epam.javalab13.model.game;

public class Player {
private int id;
private String fulName;
private int age;
private int totalGames;
private Team team;
/**
 * 
 */
public Player() {
	super();
	// TODO Auto-generated constructor stub
}
public Player(String fulName, int age) {
	super();
	this.fulName = fulName;
	this.age = age;
}
public Player(String fulName, int age, Team team) {
	super();
	this.fulName = fulName;
	this.age = age;
	this.team = team;
}
public Player(int id, String fulName, int age, int totalGames, Team team) {
	super();
	this.id = id;
	this.fulName = fulName;
	this.age = age;
	this.totalGames = totalGames;
	this.team = team;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFulName() {
	return fulName;
}
public void setFulName(String fulName) {
	this.fulName = fulName;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public int getTotalGames() {
	return totalGames;
}
public void setTotalGames(int totalGames) {
	this.totalGames = totalGames;
}
public Team getTeam() {
	return team;
}
public void setTeam(Team team) {
	this.team = team;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + age;
	result = prime * result + ((fulName == null) ? 0 : fulName.hashCode());
	result = prime * result + id;
	result = prime * result + ((team == null) ? 0 : team.hashCode());
	result = prime * result + totalGames;
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
	Player other = (Player) obj;
	if (age != other.age)
		return false;
	if (fulName == null) {
		if (other.fulName != null)
			return false;
	} else if (!fulName.equals(other.fulName))
		return false;
	if (id != other.id)
		return false;
	if (team == null) {
		if (other.team != null)
			return false;
	} else if (!team.equals(other.team))
		return false;
	if (totalGames != other.totalGames)
		return false;
	return true;
}
@Override
public String toString() {
	return "Player [id=" + id + ", fulName=" + fulName + ", age=" + age + ", totalGames=" + totalGames + ", team="
			+ team + "]";
}

}
