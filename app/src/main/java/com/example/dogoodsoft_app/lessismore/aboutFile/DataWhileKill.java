package com.example.dogoodsoft_app.lessismore.aboutFile;

import java.io.Serializable;

public class DataWhileKill implements Serializable {


    private int projectId = -1;

    private long starttime = 0;

    private long countTime = 0;

    private long deadTime = 0;


    private boolean isPause = false;

    public long getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(long deadTime) {
        this.deadTime = deadTime;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getCountTime() {
        return countTime;
    }

    public void setCountTime(long countTime) {
        this.countTime = countTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    @Override
    public String toString() {
        return projectId + "," + starttime + "," + countTime + "," + deadTime + "," + isPause;
    }
}

