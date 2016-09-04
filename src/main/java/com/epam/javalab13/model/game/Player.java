package com.epam.javalab13.model.game;

public class Player {
    private int id;
    private String fulName;
    private int age;
    private int totalGames;
    private Team team;

    public Player(){}

    public Player(int id, String fulName, int age, int totalGames, Team team) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (age != player.age) return false;
        if (totalGames != player.totalGames) return false;
        if (fulName != null ? !fulName.equals(player.fulName) : player.fulName != null) return false;
        return team != null ? team.equals(player.team) : player.team == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fulName != null ? fulName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + totalGames;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", fulName='" + fulName + '\'' +
                ", age=" + age +
                ", totalGames=" + totalGames +
                ", team=" + team +
                '}';
    }
}
