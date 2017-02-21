package com.istc.Entities;


import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Clob;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Component
@Entity
public class Department {
    @Id
    private int deptID;
    @Lob
    private Clob introduction;
    @NotNull
    private String deptName;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar establishTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "minister_id",referencedColumnName = "id") //外键设置为部长ID, 多对一关系由多的一方管理
    private Minister minister;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//多对多关系由其中任意一方管理, 另一方设置mappedBy
    @JoinTable(name = "dept_member", joinColumns = {@JoinColumn(name = "dept_id")},inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_dept")
    private Set<Meeting> meetings;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HomeWork> homeWorks;

    @Version
    private int deptVersion;

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

    public Clob getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Clob introduction) {
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


}
