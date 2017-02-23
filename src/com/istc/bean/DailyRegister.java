package com.istc.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lurui on 2016/11/21 0021.
 */
//@Entity
public class DailyRegister implements Serializable{
    //@Id
    private String MemberID;
    //@Id
    private Integer times;
    //@Id
    private Integer deptID;

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }
}

