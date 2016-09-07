package com.epam.javalab13.model.statistics;

/**
 * Represents upcoming match with title, names of teams
 * and coefficients for game result.
 */
public class UpcomingMatch {
    private String title;
    private String firstTeam;
    private String secondTeam;

    private double c1;//First team win
    private double c2;//Second team win
    private double cX;//Draw
    private double c1X;//First ream win or draw
    private double cX2;//Second team win or draw
    private double c12;//One team win (or first or second, but not DRAW!)

    public UpcomingMatch(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public double getC1() {
        return c1;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public double getC2() {
        return c2;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public double getCX() {
        return cX;
    }

    public void setCX(double cX) {
        this.cX = cX;
    }

    public double getC1X() {
        return c1X;
    }

    public void setC1X(double c1X) {
        this.c1X = c1X;
    }

    public double getCX2() {
        return cX2;
    }

    public void setCX2(double cX2) {
        this.cX2 = cX2;
    }

    public double getC12() {
        return c12;
    }

    public void setC12(double c12) {
        this.c12 = c12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpcomingMatch that = (UpcomingMatch) o;

        if (Double.compare(that.c1, c1) != 0) return false;
        if (Double.compare(that.c2, c2) != 0) return false;
        if (Double.compare(that.cX, cX) != 0) return false;
        if (Double.compare(that.c1X, c1X) != 0) return false;
        if (Double.compare(that.cX2, cX2) != 0) return false;
        if (Double.compare(that.c12, c12) != 0) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (firstTeam != null ? !firstTeam.equals(that.firstTeam) : that.firstTeam != null) return false;
        return secondTeam != null ? secondTeam.equals(that.secondTeam) : that.secondTeam == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (firstTeam != null ? firstTeam.hashCode() : 0);
        result = 31 * result + (secondTeam != null ? secondTeam.hashCode() : 0);
        temp = Double.doubleToLongBits(c1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c1X);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cX2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UpcomingMatch{" +
                "title='" + title + '\'' +
                ", firstTeam='" + firstTeam + '\'' +
                ", secondTeam='" + secondTeam + '\'' +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", cX=" + cX +
                ", c1X=" + c1X +
                ", cX2=" + cX2 +
                ", c12=" + c12 +
                '}';
    }
}
