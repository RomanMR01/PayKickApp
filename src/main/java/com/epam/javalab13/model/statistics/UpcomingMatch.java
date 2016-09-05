package com.epam.javalab13.model.statistics;

import com.epam.javalab13.model.bet.Result;

/**
 * Created by Vikno on 9/5/2016.
 */
public class UpcomingMatch {
    private String title;
    private String firstTeam;
    private String secondTeam;

    private Result c1;
    private Result c2;
    private Result cX;
    private Result c1X;
    private Result cX2;
    private Result c12;

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

    public Result getC1() {
        return c1;
    }

    public void setC1(Result c1) {
        this.c1 = c1;
    }

    public Result getC2() {
        return c2;
    }

    public void setC2(Result c2) {
        this.c2 = c2;
    }

    public Result getcX() {
        return cX;
    }

    public void setcX(Result cX) {
        this.cX = cX;
    }

    public Result getC1X() {
        return c1X;
    }

    public void setC1X(Result c1X) {
        this.c1X = c1X;
    }

    public Result getcX2() {
        return cX2;
    }

    public void setcX2(Result cX2) {
        this.cX2 = cX2;
    }

    public Result getC12() {
        return c12;
    }

    public void setC12(Result c12) {
        this.c12 = c12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpcomingMatch that = (UpcomingMatch) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (firstTeam != null ? !firstTeam.equals(that.firstTeam) : that.firstTeam != null) return false;
        if (secondTeam != null ? !secondTeam.equals(that.secondTeam) : that.secondTeam != null) return false;
        if (c1 != that.c1) return false;
        if (c2 != that.c2) return false;
        if (cX != that.cX) return false;
        if (c1X != that.c1X) return false;
        if (cX2 != that.cX2) return false;
        return c12 == that.c12;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (firstTeam != null ? firstTeam.hashCode() : 0);
        result = 31 * result + (secondTeam != null ? secondTeam.hashCode() : 0);
        result = 31 * result + (c1 != null ? c1.hashCode() : 0);
        result = 31 * result + (c2 != null ? c2.hashCode() : 0);
        result = 31 * result + (cX != null ? cX.hashCode() : 0);
        result = 31 * result + (c1X != null ? c1X.hashCode() : 0);
        result = 31 * result + (cX2 != null ? cX2.hashCode() : 0);
        result = 31 * result + (c12 != null ? c12.hashCode() : 0);
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
