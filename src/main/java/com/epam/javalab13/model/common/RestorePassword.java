package com.epam.javalab13.model.common;

import java.util.Date;

/**
 * Created by Vikno on 9/7/2016.
 */
public class RestorePassword {

    private Date startTime;
    private Date endTime;
    private String userLogin;

    public RestorePassword(){}

    public RestorePassword(Date startTime, Date endTime, String userLogin) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RestorePassword{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
