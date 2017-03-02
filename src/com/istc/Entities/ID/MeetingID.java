package com.istc.Entities.ID;

import com.istc.Entities.Entity.Department;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class MeetingID implements java.io.Serializable{
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Department department;

    private Integer times;

    public MeetingID(Integer deptID, Integer times) {
        this.department = new Department();
        department.setDeptID(deptID);
        this.times = times;
    }

    public MeetingID(Department department, Integer times) {
        this.department = department;
        this.times = times;
    }

    public MeetingID() {
    }



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
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

    @Override
    public String toString() {
        return "MeetingID{" +
                "department=" + department +
                ", times=" + times +
                '}';
    }
}
