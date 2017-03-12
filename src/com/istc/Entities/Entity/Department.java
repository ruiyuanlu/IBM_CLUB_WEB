package com.istc.Entities.Entity;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Entity
public class Department {
    @Id
    private Integer deptID;
    @Version
    private int deptVersion;
    @Lob
    private String introduction;
    private String deptName;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar establishTime;

    @ManyToMany
    @JoinTable(name = "dept_minister",
            joinColumns = {@JoinColumn(name = "dept_id")},
            inverseJoinColumns = {@JoinColumn(name = "minister_id")})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Minister> ministers;

//    @ManyToMany( fetch = FetchType.LAZY,mappedBy = "enterDepts")//多对多关系由其中任意一方管理, 另一方设置mappedBy,也可以由双方都可管理
    @ManyToMany
    @JoinTable(name = "dept_member",
            joinColumns = {@JoinColumn(name = "mem_id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id")})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Member> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "meetingID.dept")
    private Set<Meeting> meetings;

    @OneToMany(mappedBy = "homeWorkID.dept")
    private Set<HomeWork> homeWorks;

    @OneToMany(mappedBy = "registerID.department")
    private Set<Register> registers;

    @OneToMany(mappedBy = "department")
    private Set<HomeWorkIssue> homeWorkIssues;

    public Department() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (deptVersion != that.deptVersion) return false;
        if (deptID != null ? !deptID.equals(that.deptID) : that.deptID != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (deptName != null ? !deptName.equals(that.deptName) : that.deptName != null) return false;
        if (establishTime != null ? !establishTime.equals(that.establishTime) : that.establishTime != null)
            return false;
        if (ministers != null ? !ministers.equals(that.ministers) : that.ministers != null) return false;
        if (members != null ? !members.equals(that.members) : that.members != null) return false;
        if (meetings != null ? !meetings.equals(that.meetings) : that.meetings != null) return false;
        if (homeWorks != null ? !homeWorks.equals(that.homeWorks) : that.homeWorks != null) return false;
        return registers != null ? registers.equals(that.registers) : that.registers == null;
    }

    @Override
    public int hashCode() {
        int result = deptID != null ? deptID.hashCode() : 0;
        result = 31 * result + deptVersion;
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (establishTime != null ? establishTime.hashCode() : 0);
        result = 31 * result + (ministers != null ? ministers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptID=" + deptID +
                ", deptVersion=" + deptVersion +
                ", introduction='" + introduction + '\'' +
                ", deptName='" + deptName + '\'' +
                ", establishTime=" + establishTime +
                ", ministers=" + ministers +
                ", members=" + members +
                ", meetings=" + meetings +
                ", homeWorks=" + homeWorks +
                ", registers=" + registers +
                '}';
    }

    public Department(int deptID) {
        this.deptID = deptID;
    }

    public void setMinisters(Set<Minister> ministers) {
        this.ministers = ministers;
    }

    public Set<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Set<Register> registers) {
        this.registers = registers;
    }

    public Set<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Set<Minister> getMinisters() {
        return ministers;
    }

    public void setMinister(Set<Minister> minister0) {
        this.ministers = minister0;
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

    public Set<HomeWorkIssue> getHomeWorkIssues() {
        return homeWorkIssues;
    }

    public void setHomeWorkIssues(Set<HomeWorkIssue> homeWorkIssues) {
        this.homeWorkIssues = homeWorkIssues;
    }
}
