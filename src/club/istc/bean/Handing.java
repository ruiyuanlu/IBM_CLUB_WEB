package club.istc.bean;

/**
 * Created by lurui on 2016/11/21 0021.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * */
//@Entity
public class Handing implements Serializable{
    //@Id
    private String MemberID;
    //@Id
    private Integer deptID;
    //@Id
    private Integer times;
    //@Temporal(TemporalType.TIMESTAMP)
    LocalDateTime handingTime;
    //@Basic
    private Integer points;

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

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

    public LocalDateTime getHandingTime() {
        return handingTime;
    }

    public void setHandingTime(LocalDateTime handingTime) {
        this.handingTime = handingTime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        if(points >=0 || points <=100)
            this.points = points;
    }
}
