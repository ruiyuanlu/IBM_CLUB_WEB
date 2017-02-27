package com.istc.Entities.Entity;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Entity
public class Department implements Serializable{
    @Id
    private int deptID;
    @Column(length = 603)
    private String introduction;
    @NotNull
    private String deptName;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar establishTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "minister_id",referencedColumnName = "id") //外键设置为部长ID, 多对一关系由多的一方管理,@JoinColumn 可以避免生成额外的关联表，降低效率
    private Minister minister;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//多对多关系由其中任意一方管理, 维护多对多关系的一方设置mappedBy，此处维护方是Member类，这里可以让它自动产生关联表
//    @JoinTable(name = "dept_member", joinColumns = {@JoinColumn(name = "dept_id")},inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Meeting> meetings;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HomeWork> homeWorks;

    @Version
    private int deptVersion;

    public void addHomework(HomeWork homeWork){
        if(homeWork == null)return;
        if(this.homeWorks == null) this.homeWorks = new HashSet<>();
        if(homeWork != null)this.homeWorks.add(homeWork);
    }

    public void addHomeworks(HomeWork[] homeWorks){
        if(homeWorks == null)return;
        if(this.homeWorks == null) this.homeWorks = new HashSet<>();
        for(HomeWork homeWork: homeWorks)
            if(homeWork != null)this.homeWorks.add(homeWork);
    }

    public void deleteHomework(HomeWork homeWork){
        if(homeWork == null || this.homeWorks == null)return;
        if(homeWork != null)this.homeWorks.remove(homeWork);
    }

    public void deleteHomeworks(HomeWork[] homeWorks){
        if(homeWorks == null || this.homeWorks == null)return;
        for(HomeWork homeWork: homeWorks)
            if(homeWork != null)this.homeWorks.remove(homeWork);
    }
    public void addMember(Member member){
        if(member == null)return;
        if(this.members == null) this.members = new HashSet<>();
        if(member != null)this.members.add(member);
    }

    public void addMembers(Member[] members){
        if(members == null)return;
        if(this.members == null) this.members = new HashSet<>();
        for(Member member: members)
            if(member != null)this.members.add(member);
    }

    public void deleteMember(Member member){
        if(member == null || this.members == null)return;
        if(member != null)this.members.remove(member);
    }

    public void deleteMembers(Member[] members){
        if(members == null || this.members == null)return;
        for(Member member: members)
            if(member != null)this.members.remove(member);
    }

    public void addMeeting(Meeting meeting){
        if(meeting == null)return;
        if(this.meetings == null) this.meetings = new HashSet<>();
        if(meeting != null)this.meetings.add(meeting);
    }

    public void addMeetings(Meeting[] meetings){
        if(meetings == null)return;
        if(this.meetings == null) this.meetings = new HashSet<>();
        for(Meeting meeting: meetings)
            if(meeting != null)this.meetings.add(meeting);
    }

    public void deleteMeeting(Meeting meeting){
        if(meeting == null || this.meetings == null)return;
        if(meeting != null)this.meetings.remove(meeting);
    }

    public void deleteMeetings(Meeting[] meetings){
        if(meetings == null || this.meetings == null)return;
        for(Meeting meeting: meetings)
            if(meeting != null)this.meetings.remove(meeting);
    }

    // getters and setters
    private int getDeptVersion() {
        return deptVersion;
    }

    private void setDeptVersion(int deptVersion) {
        this.deptVersion = deptVersion;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Department() {
    }

    public Calendar getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Calendar establishTime) {
        this.establishTime = establishTime;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Minister getMinister() {
        return minister;
    }

    public void setMinister(Minister minister) {
        this.minister = minister;
    }


    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptID=" + deptID +
                ", introduction='" + introduction + '\'' +
                ", deptName='" + deptName + '\'' +
                ", establishTime=" + establishTime +
                ", minister=" + minister +
                ", members=" + members +
                ", meetings=" + meetings +
                ", homeWorks=" + homeWorks +
                ", deptVersion=" + deptVersion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (deptID != that.deptID) return false;
        if (deptVersion != that.deptVersion) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (deptName != null ? !deptName.equals(that.deptName) : that.deptName != null) return false;
        if (establishTime != null ? !establishTime.equals(that.establishTime) : that.establishTime != null)
            return false;
        if (minister != null ? !minister.equals(that.minister) : that.minister != null) return false;
        if (members != null ? !members.equals(that.members) : that.members != null) return false;
        if (meetings != null ? !meetings.equals(that.meetings) : that.meetings != null) return false;
        return homeWorks != null ? homeWorks.equals(that.homeWorks) : that.homeWorks == null;

    }

    @Override
    public int hashCode() {
        int result = deptID;
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (establishTime != null ? establishTime.hashCode() : 0);
        result = 31 * result + (minister != null ? minister.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (meetings != null ? meetings.hashCode() : 0);
        result = 31 * result + (homeWorks != null ? homeWorks.hashCode() : 0);
        result = 31 * result + deptVersion;
        return result;
    }
}
