package Entities.Entity;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.sql.Clob;
import java.util.Calendar;
import java.util.HashSet;
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
    @JoinColumn(name = "dept_meeting")
    private Set<Meeting> meetings;

    @OneToMany
    private Set<HomeWork> homeWorks;

    @OneToMany
    private Set<Register> registers;

    public Department() {
    }

    public Department(int deptID) {
        this.deptID = deptID;
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
