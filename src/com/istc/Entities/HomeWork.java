package com.istc.Entities;

import com.istc.Entities.ID.HomeWorkID;

import javax.persistence.*;
import java.sql.*;
import java.util.Calendar;

@Entity
public class HomeWork {
    @Id
    private HomeWorkID homeWorkID;
    @Basic
    private int fileID;
    @Lob
    private Clob homeWorkRequirement;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar beginTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar endTime;
    @Version
    private int homeWorkVersion;


    public HomeWork() {
        this.homeWorkID = new HomeWorkID();
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

    public Department getDeptID() {
        return homeWorkID.getDept();
    }

    public void setDeptID(Department deptID) {
        this.homeWorkID.setDept(deptID);
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

    public Clob getHomeWorkRequirement() {
        return homeWorkRequirement;
    }

    public void setHomeWorkRequirement(Clob homeWorkRequirement) {
        this.homeWorkRequirement = homeWorkRequirement;
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
}
