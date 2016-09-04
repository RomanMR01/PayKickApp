package com.epam.javalab13.model.game;

public class TotalGoalsCoefficient {
    private int id;
    private Game game;
    private double goalCoefficient;

    public TotalGoalsCoefficient(){}

    public TotalGoalsCoefficient(int id, Game game, double goalCoefficient) {
        this.id = id;
        this.game = game;
        this.goalCoefficient = goalCoefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getGoalCoefficient() {
        return goalCoefficient;
    }

    public void setGoalCoefficient(double goalCoefficient) {
        this.goalCoefficient = goalCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalGoalsCoefficient that = (TotalGoalsCoefficient) o;

        if (id != that.id) return false;
        if (Double.compare(that.goalCoefficient, goalCoefficient) != 0) return false;
        return game != null ? game.equals(that.game) : that.game == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (game != null ? game.hashCode() : 0);
        temp = Double.doubleToLongBits(goalCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TotalGoalsCoefficient{" +
                "id=" + id +
                ", game=" + game +
                ", goalCoefficient=" + goalCoefficient +
                '}';
    }
}
