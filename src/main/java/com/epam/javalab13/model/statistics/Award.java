package com.epam.javalab13.model.statistics;

import com.epam.javalab13.model.User;

/**
 * Represents user awards for all time
 */
public class Award {
    private User user;
    private double awardSum;
    private int totalGames;

    public Award(){}

    public Award(User user, double awardSum, int totalGames) {
        this.user = user;
        this.awardSum = awardSum;
        this.totalGames = totalGames;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAwardSum() {
        return awardSum;
    }

    public void setAwardSum(double awardSum) {
        this.awardSum = awardSum;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Award award = (Award) o;

        if (Double.compare(award.awardSum, awardSum) != 0) return false;
        if (totalGames != award.totalGames) return false;
        return user != null ? user.equals(award.user) : award.user == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = user != null ? user.hashCode() : 0;
        temp = Double.doubleToLongBits(awardSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + totalGames;
        return result;
    }

    @Override
    public String toString() {
        return "Award{" +
                "user=" + user +
                ", awardSum=" + awardSum +
                ", totalGames=" + totalGames +
                '}';
    }
}
