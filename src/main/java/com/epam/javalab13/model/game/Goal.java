package com.epam.javalab13.model.game;

public class Goal {
private int id;
private Player player;
private Team team;
private Game game;
private int minute;
public Goal() {
	super();
	// TODO Auto-generated constructor stub
}
public Goal(Player player, Team team, Game game, int minute) {
	super();
	this.player = player;
	this.team = team;
	this.game = game;
	this.minute = minute;
}
public Goal(int id, Player player, Team team, Game game, int minute) {
	super();
	this.id = id;
	this.player = player;
	this.team = team;
	this.game = game;
	this.minute = minute;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Player getPlayer() {
	return player;
}
public void setPlayer(Player player) {
	this.player = player;
}
public Team getTeam() {
	return team;
}
public void setTeam(Team team) {
	this.team = team;
}
public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}
public int getMinute() {
	return minute;
}
public void setMinute(int minute) {
	this.minute = minute;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((game == null) ? 0 : game.hashCode());
	result = prime * result + id;
	result = prime * result + minute;
	result = prime * result + ((player == null) ? 0 : player.hashCode());
	result = prime * result + ((team == null) ? 0 : team.hashCode());
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
	Goal other = (Goal) obj;
	if (game == null) {
		if (other.game != null)
			return false;
	} else if (!game.equals(other.game))
		return false;
	if (id != other.id)
		return false;
	if (minute != other.minute)
		return false;
	if (player == null) {
		if (other.player != null)
			return false;
	} else if (!player.equals(other.player))
		return false;
	if (team == null) {
		if (other.team != null)
			return false;
	} else if (!team.equals(other.team))
		return false;
	return true;
}
@Override
public String toString() {
	return "Goal [id=" + id + ", player=" + player + ", team=" + team + ", game=" + game + ", minute=" + minute + "]";
}

}
