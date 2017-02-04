package Entities;

import javax.persistence.*;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
class MeetingID implements java.io.Serializable{
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

@Entity
public class Meeting {

    @Id
    private MeetingID meetingID;
    @Basic
    private String meetingContent;
    private String location;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "meeting_dept")
    private Department dept;
    @Version
    private int meetingVersion;

    public Meeting() {
        this.meetingID = new MeetingID();
    }

    private int getMeetingVersion() {
        return meetingVersion;
    }

    private void setMeetingVersion(int meetingVersion) {
        this.meetingVersion = meetingVersion;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MeetingID getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(MeetingID meetingID) {
        this.meetingID = meetingID;
    }

    public String getMeetingContent() {
        return meetingContent;
    }

    public void setMeetingContent(String meetingContent) {
        this.meetingContent = meetingContent;
    }

    public int getTimes() {
        return this.meetingID.getTimes();
    }

    public void setTimes(int times) {
        this.meetingID.setDeptID(times);
    }

    public int getDeptID() {
        return meetingID.getDeptID();
    }

    public void setDeptID(int deptID) {
        this.meetingID.setDeptID(deptID);
    }
}
