package Entities;

import javax.persistence.*;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
class HomeWorkID implements java.io.Serializable{
    private int deptID;
    private int times;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member homeWorkSubmitter;

    public HomeWorkID() {
    }

    public Member getHomeWorkSubmitter() {
        return homeWorkSubmitter;
    }

    public void setHomeWorkSubmitter(Member homeWorkSubmitter) {
        this.homeWorkSubmitter = homeWorkSubmitter;
    }

    public int getDeptID() {
        return deptID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeWorkID)) return false;

        HomeWorkID that = (HomeWorkID) o;

        if (deptID != that.deptID) return false;
        if (times != that.times) return false;
        return homeWorkSubmitter != null ? homeWorkSubmitter.equals(that.homeWorkSubmitter) : that.homeWorkSubmitter == null;

    }

    @Override
    public int hashCode() {
        int result = deptID;
        result = 31 * result + times;
        result = 31 * result + (homeWorkSubmitter != null ? homeWorkSubmitter.hashCode() : 0);
        return result;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
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

    public int getDeptID() {
        return homeWorkID.getDeptID();
    }

    public void setDeptID(Integer deptID) {
        this.homeWorkID.setDeptID(deptID);
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
