package com.epam.javalab13.model.statistics;

import com.epam.javalab13.model.User;

/**
 * Represents user awards for all time
 */
public class TopUser {
    private String login;
    private double awardSum;
    private double lastWin;

    public TopUser(){}

    public TopUser(double lastWin, String login, double awardSum) {
        this.lastWin = lastWin;
        this.login = login;
        this.awardSum = awardSum;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getAwardSum() {
        return awardSum;
    }

    public void setAwardSum(double awardSum) {
        this.awardSum = awardSum;
    }

    public double getLastWin() {
        return lastWin;
    }

    public void setLastWin(double lastWin) {
        this.lastWin = lastWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopUser topUser = (TopUser) o;

        if (Double.compare(topUser.awardSum, awardSum) != 0) return false;
        if (Double.compare(topUser.lastWin, lastWin) != 0) return false;
        return login != null ? login.equals(topUser.login) : topUser.login == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = login != null ? login.hashCode() : 0;
        temp = Double.doubleToLongBits(awardSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lastWin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TopUser{" +
                "login='" + login + '\'' +
                ", awardSum=" + awardSum +
                ", lastWin=" + lastWin +
                '}';
    }
}
