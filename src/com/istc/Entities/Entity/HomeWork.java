package com.istc.Entities.Entity;


import com.istc.Entities.ID.HomeWorkID;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class HomeWork {
    @Id
    private HomeWorkID homeWorkID;
    @Basic
    private int fileID;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar beginTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar endTime;
    //-1代表作业没被看过，看过不打分则为 -1
    private Integer score;
    @ManyToOne
    private HomeWorkIssue homeWorkIssue;
    @Version
    private int homeWorkVersion;


    public HomeWork() {
        this.homeWorkID = new HomeWorkID();
        score = -1;
    }

    public HomeWork(Department dept, Member member, int times) {
        this.homeWorkID = new HomeWorkID(dept, member, times);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        if(score < 0 || score > 100){
            score = 0;
            return;
        }
        this.score = score;
    }

    private int getHomeWorkVersion() {
        return homeWorkVersion;
    }

    private void setHomeWorkVersion(int homeWorkVersion) {
        this.homeWorkVersion = homeWorkVersion;
    }

    public Member getHomeWorkSubmitter() {
        return this.homeWorkID.getHomeWorkSubmitter();
    }

    public void setHomeWorkSubmitter(Member homeWorkSubmitter) {
        this.homeWorkID.setHomeWorkSubmitter(homeWorkSubmitter);
    }

    public Department getDept() {
        return homeWorkID.getDept();
    }

    public void setDept(Department dept) {
        this.homeWorkID.setDept(dept);
    }

    public HomeWorkID getHomeWorkID() {
        return homeWorkID;
    }

    public void setHomeWorkID(HomeWorkID homeWorkID) {
        this.homeWorkID = homeWorkID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public int getTimes() {
        return homeWorkID.getTimes();
    }

    public void setTimes(Integer times) {
        this.homeWorkID.setTimes(times);
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public HomeWorkIssue getHomeWorkIssue() {
        return homeWorkIssue;
    }

    public void setHomeWorkIssue(HomeWorkIssue homeWorkIssue) {
        this.homeWorkIssue = homeWorkIssue;
    }
}
