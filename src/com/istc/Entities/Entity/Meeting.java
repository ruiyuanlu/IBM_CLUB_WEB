package com.istc.Entities.Entity;

import com.istc.Entities.ID.MeetingID;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Meeting {

    @Id
    private MeetingID meetingID;
    @Lob
    private String meetingContent;
    private String location;

    private Calendar createRecordTime;
    private Calendar startTime;

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

    @Version
    private int meetingVersion;

    public Calendar getCreateRecordTime() {
        return createRecordTime;
    }

    public void setCreateRecordTime(Calendar createRecordTime) {
        this.createRecordTime = createRecordTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
    public void setStartTime(Integer year, Integer month, Integer day, Integer hour, Integer min, Integer sec){
        startTime.set(Calendar.YEAR, year);
        startTime.set(Calendar.MONTH, month - 1);//month 是 当前月 -1
        startTime.set(Calendar.DAY_OF_MONTH, day);
        startTime.set(Calendar.HOUR, hour);
        startTime.set(Calendar.MINUTE, min);
        startTime.set(Calendar.SECOND, sec);
    }

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
