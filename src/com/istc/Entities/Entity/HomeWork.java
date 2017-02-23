package com.istc.Entities.Entity;

import com.istc.Entities.ID.HomeWorkID;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Calendar;

@Entity
public class HomeWork implements Serializable{
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
        return homeWorkID.getDepartment();
    }

    public void setDeptID(Department deptID) {
        this.homeWorkID.setDepartment(deptID);
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

    @Override
    public String toString() {
        return "HomeWork{" +
                "beginTime=" + beginTime +
                ", homeWorkID=" + homeWorkID +
                ", fileID=" + fileID +
                ", homeWorkRequirement=" + homeWorkRequirement +
                ", endTime=" + endTime +
                ", homeWorkVersion=" + homeWorkVersion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeWork)) return false;

        HomeWork homeWork = (HomeWork) o;

        if (fileID != homeWork.fileID) return false;
        if (homeWorkVersion != homeWork.homeWorkVersion) return false;
        if (homeWorkID != null ? !homeWorkID.equals(homeWork.homeWorkID) : homeWork.homeWorkID != null) return false;
        if (homeWorkRequirement != null ? !homeWorkRequirement.equals(homeWork.homeWorkRequirement) : homeWork.homeWorkRequirement != null)
            return false;
        if (beginTime != null ? !beginTime.equals(homeWork.beginTime) : homeWork.beginTime != null) return false;
        return endTime != null ? endTime.equals(homeWork.endTime) : homeWork.endTime == null;

    }

    @Override
    public int hashCode() {
        int result = homeWorkID != null ? homeWorkID.hashCode() : 0;
        result = 31 * result + fileID;
        result = 31 * result + (homeWorkRequirement != null ? homeWorkRequirement.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + homeWorkVersion;
        return result;
    }
}
