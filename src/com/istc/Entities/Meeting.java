package com.istc.Entities;

import com.istc.Entities.ID.MeetingID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Meeting implements Serializable{

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

    public void setTimes(Department times) {
        this.meetingID.setDepartment(times);
    }

    public Department getDeptID() {
        return meetingID.getDepartment();
    }

    public void setDeptID(Department deptID) {
        this.meetingID.setDepartment(deptID);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "dept=" + dept +
                ", meetingID=" + meetingID +
                ", meetingContent='" + meetingContent + '\'' +
                ", location='" + location + '\'' +
                ", meetingVersion=" + meetingVersion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meeting)) return false;

        Meeting meeting = (Meeting) o;

        if (meetingVersion != meeting.meetingVersion) return false;
        if (meetingID != null ? !meetingID.equals(meeting.meetingID) : meeting.meetingID != null) return false;
        if (meetingContent != null ? !meetingContent.equals(meeting.meetingContent) : meeting.meetingContent != null)
            return false;
        if (location != null ? !location.equals(meeting.location) : meeting.location != null) return false;
        return dept != null ? dept.equals(meeting.dept) : meeting.dept == null;

    }

    @Override
    public int hashCode() {
        int result = meetingID != null ? meetingID.hashCode() : 0;
        result = 31 * result + (meetingContent != null ? meetingContent.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + meetingVersion;
        return result;
    }
}
