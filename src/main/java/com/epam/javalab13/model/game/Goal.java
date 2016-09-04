package com.epam.javalab13.model.game;

public class Goal {
    private int id;
    private Player player;
    private Team team;
    private Game game;
    private int minute;

    public Goal() {
    }

    public Goal(int id, Player player, Team team, Game game, int minute) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (id != goal.id) return false;
        if (minute != goal.minute) return false;
        if (player != null ? !player.equals(goal.player) : goal.player != null) return false;
        if (team != null ? !team.equals(goal.team) : goal.team != null) return false;
        return game != null ? game.equals(goal.game) : goal.game == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (game != null ? game.hashCode() : 0);
        result = 31 * result + minute;
        return result;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", player=" + player +
                ", team=" + team +
                ", game=" + game +
                ", minute=" + minute +
                '}';
    }
}
