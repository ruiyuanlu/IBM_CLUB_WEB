package com.istc.Entities;

import com.istc.Entities.ID.MeetingID;

import javax.persistence.*;

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

    public void setTimes(Department times) {
        this.meetingID.setDepartment(times);
    }

    public Department getDeptID() {
        return meetingID.getDepartment();
    }

    public void setDeptID(Department deptID) {
        this.meetingID.setDepartment(deptID);
    }
}
