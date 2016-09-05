package com.epam.javalab13.model.game;

import com.epam.javalab13.model.bet.Result;

public class ResultCoefficient {
    private int id;
    private Game game;
    private Result result;
    private double coefficient;

    public ResultCoefficient(){}

    public ResultCoefficient(int id, Game game, Result result, double coefficient) {
        this.id = id;
        this.game = game;
        this.result = result;
        this.coefficient = coefficient;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultCoefficient that = (ResultCoefficient) o;

        if (id != that.id) return false;
        if (Double.compare(that.coefficient, coefficient) != 0) return false;
        if (game != null ? !game.equals(that.game) : that.game != null) return false;
        return result == that.result;

    }

    @Override
    public int hashCode() {
        int result1;
        long temp;
        result1 = id;
        result1 = 31 * result1 + (game != null ? game.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        temp = Double.doubleToLongBits(coefficient);
        result1 = 31 * result1 + (int) (temp ^ (temp >>> 32));
        return result1;
    }

    @Override
    public String toString() {
        return "ResultCoefficient{" +
                "id=" + id +
                ", game=" + game +
                ", result=" + result +
                ", coefficient=" + coefficient +
                '}';
    }
}
