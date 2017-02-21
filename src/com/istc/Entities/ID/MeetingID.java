package com.istc.Entities.ID;

import com.istc.Entities.Department;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class MeetingID implements java.io.Serializable{
    @ManyToOne
    private Department department;
    private int times;

    public MeetingID() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        return department != null ? department.equals(meetingID.department) : meetingID.department == null;

    }

    @Override
    public int hashCode() {
        int result = times;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }
}
