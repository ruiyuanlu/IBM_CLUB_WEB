package com.istc.Entities.Entity;


import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.Cascade;

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

    @ManyToMany
    @JoinTable(name = "dept_minister",
            joinColumns = {@JoinColumn(name = "dept_id")},
            inverseJoinColumns = {@JoinColumn(name = "minister_id")})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Minister> ministers;

    @ManyToMany
    @JoinTable(name = "dept_member",
            joinColumns = {@JoinColumn(name = "mem_id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id")})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JoinTable(name = "dept_member", joinColumns = {@JoinColumn(name = "dept_id")},inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "meetingID.dept")
//    @JoinColumn(name = "dept_meeting")
    private Set<Meeting> meetings;

    @OneToMany(mappedBy = "homeWorkID.dept")
    private Set<HomeWork> homeWorks;

    @OneToMany(mappedBy = "registerID.department")
    private Set<Register> registers;


    @Version
    private int deptVersion;

    /**
     *
     * 此处已声明与register相关的方法
     */

    public void addRegister(Register register){
        if(register == null)return;
        if(this.registers == null) this.registers = new HashSet<>();
        if(register != null)this.registers.add(register);
    }

    public void addRegisters(Register[] registers){
        if(registers == null)return;
        if(this.registers == null) this.registers = new HashSet<>();
        for(Register register: registers)
            if(register != null)this.registers.add(register);
    }

    public void deleteRegister(Register register){
        if(register == null || this.registers == null)return;
        if(register != null)this.registers.remove(register);
    }

    public void deleteRegisters(Register[] registers){
        if(registers == null || this.registers == null)return;
        for(Register register: registers)
            if(register != null)this.registers.remove(register);
    }

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

    public Set<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Set<Register> registers) {
        this.registers = registers;
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

    public Set<Minister> getMinister() {
        return ministers;
    }

    public void setMinister(Set<Minister> minister) {
        this.ministers = ministers;
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
                ", minister=" + ministers +
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
        if (ministers != null ? !ministers.equals(that.ministers) : that.ministers != null) return false;
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
        result = 31 * result + deptVersion;
        return result;
    }
}
