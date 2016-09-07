package com.epam.javalab13.model.statistics;

/**
 * Represents user awards for all time
 */
public class TopUser {
    private String login;
    private double awardSum;

    /*
    If status WON it will be AWARD;
    If status LOST it will be AMOUNT
     */
    private double lastBetSum;

    public TopUser(){}

    public TopUser(double lastWin, String login, double awardSum) {
        this.lastBetSum = lastWin;
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
        return lastBetSum;
    }

    public void setLastWin(double lastWin) {
        this.lastBetSum = lastWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopUser topUser = (TopUser) o;

        if (Double.compare(topUser.awardSum, awardSum) != 0) return false;
        if (Double.compare(topUser.lastBetSum, lastBetSum) != 0) return false;
        return login != null ? login.equals(topUser.login) : topUser.login == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = login != null ? login.hashCode() : 0;
        temp = Double.doubleToLongBits(awardSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lastBetSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TopUser{" +
                "login='" + login + '\'' +
                ", awardSum=" + awardSum +
                ", lastWin=" + lastBetSum +
                '}';
    }
}
