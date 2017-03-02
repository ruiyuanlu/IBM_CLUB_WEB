package com.istc.Entities.Entity;

import com.istc.Entities.ID.MeetingID;

import javax.persistence.*;

@Entity
public class Meeting {

    @Id
    private MeetingID meetingID;
    @Lob
    private String meetingContent;
    private String location;



    @Version
    private int meetingVersion;

    public Meeting() {
        this.meetingID = new MeetingID();
    }

    public Meeting(Department dept, int times) {
        this.meetingID = new MeetingID(dept, times);
    }

    private int getMeetingVersion() {
        return meetingVersion;
    }

    private void setMeetingVersion(int meetingVersion) {
        this.meetingVersion = meetingVersion;
    }

    public Department getDept() {
        return this.meetingID.getDept();
    }

    public void setDept(Department dept) {
        this.meetingID.setDept(dept);
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
        this.meetingID.setDept(times);
    }

    public Department getDeptID() {
        return meetingID.getDept();
    }

    public void setDeptID(Department deptID) {
        this.meetingID.setDept(deptID);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "location='" + location + '\'' +
                ", meetingID=" + meetingID +
                ", meetingContent='" + meetingContent + '\'' +
                ", meetingVersion=" + meetingVersion +
                '}';
    }
}
