package com.istc.Entities.ID;

import javax.persistence.Embeddable;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class MeetingID implements java.io.Serializable{
    private int times;
    private int deptID;

    public MeetingID() {
    }

    public int getDeptID() {
        return deptID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingID)) return false;

        MeetingID meetingID = (MeetingID) o;

        if (times != meetingID.times) return false;
        return deptID == meetingID.deptID;

    }

    @Override
    public int hashCode() {
        int result = times;
        result = 31 * result + deptID;
        return result;
    }
}
