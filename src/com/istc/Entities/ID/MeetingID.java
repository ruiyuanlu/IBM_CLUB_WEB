package Entities.ID;

import Entities.Entity.Department;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class MeetingID implements java.io.Serializable{
    private int times;
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Department dept;

    public MeetingID() {
    }

    @Override
    public String toString() {
        return "MeetingID{" +
                "dept=" + dept +
                ", times=" + times +
                '}';
    }

    public MeetingID(Department deptID, int times) {
        this.dept = deptID;
        this.times = times;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department deptID) {
        this.dept = deptID;
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
        return dept != null ? dept.equals(meetingID.dept) : meetingID.dept == null;

    }

    @Override
    public int hashCode() {
        int result = times;
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        return result;
    }
}
