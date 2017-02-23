package com.istc.Entities.Entity;

import com.istc.Entities.ID.MeetingID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Meeting implements Serializable{

    @Id
    private MeetingID meetingID;
    @Basic
    private String meetingContent;
    private String location;
    //创建会议这条记录的时间
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar createRecordTime;
    //创建会议时用户选择的时间
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar startTime;


    @Version
    private int meetingVersion;

    public Meeting() {
        this.meetingID = new MeetingID();
    }

    public Meeting(Department department, Integer times, String meetingContent, String location, Integer year, Integer month, Integer day, Integer hour, Integer min, Integer sec){
        this.meetingID = new MeetingID(department,times);
        this.meetingContent = meetingContent;
        this.location = location;
        this.createRecordTime = Calendar.getInstance();
        //设置会议开始时间
        this.startTime = Calendar.getInstance();
        this.setStartTime(year, month,day, hour, min, sec);
    }

    public Meeting(Integer deptID, Integer meetingTimes) {
        this.meetingID = new MeetingID(deptID, meetingTimes);
    }

    public Calendar getCreateRecordTime() {
        return createRecordTime;
    }

    public void setCreateRecordTime(Calendar createRecordTime) {
        this.createRecordTime = createRecordTime;
    }

    private int getMeetingVersion() {
        return meetingVersion;
    }

    private void setMeetingVersion(int meetingVersion) {
        this.meetingVersion = meetingVersion;
    }

    public Department getDept() {
        return meetingID.getDepartment();
    }

    public void setDept(Department dept) {
        this.meetingID.setDepartment(dept);
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

    public Integer getTimes() {
        return this.meetingID.getTimes();
    }

    public void setTimes(Integer times) {
        this.meetingID.setTimes(times);
    }

    public Department getDepartment() {
        return meetingID.getDepartment();
    }

    public void setDepartment(Department dept) {
        this.meetingID.setDepartment(dept);
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;

    }

    public void setStartTime(Integer year, Integer month, Integer day, Integer hour, Integer min, Integer sec){
        startTime.set(Calendar.YEAR, year);
        startTime.set(Calendar.MONTH, month);
        startTime.set(Calendar.DATE, day);
        startTime.set(Calendar.HOUR, hour);
        startTime.set(Calendar.MINUTE, min);
        startTime.set(Calendar.SECOND, sec);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "createRecordTime=" + createRecordTime +
                ", meetingID=" + meetingID +
                ", meetingContent='" + meetingContent + '\'' +
                ", location='" + location + '\'' +
                ", startTime=" + startTime +
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
        if (createRecordTime != null ? !createRecordTime.equals(meeting.createRecordTime) : meeting.createRecordTime != null)
            return false;
        return startTime != null ? startTime.equals(meeting.startTime) : meeting.startTime == null;

    }

    @Override
    public int hashCode() {
        int result = meetingID != null ? meetingID.hashCode() : 0;
        result = 31 * result + (meetingContent != null ? meetingContent.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (createRecordTime != null ? createRecordTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + meetingVersion;
        return result;
    }
}
