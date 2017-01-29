package club.istc.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Created by lurui on 2016/11/21 0021.
 */
//@Entity
public class HomeWork implements Serializable{
    //@Id
    private Integer deptID;
    //@Id
    private Integer times;
    //@Basic
    private Integer fileID;
    //@Lob
    private Clob homeWorkRequirement;
    //@Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime beginTime;
    //@Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
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

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
