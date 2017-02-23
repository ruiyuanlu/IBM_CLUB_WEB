package com.istc.bean;


import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lurui on 2016/11/21 0021.
 */
public class Department {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deptID;
    //@Lob
    private Clob content;
    //@Column
    private String deptName;
    //@ManyToMany
    private Set<Member> deptMembers;
    //@OneToMany
    private Set<Meeting> deptMeetings;

    public Department() {
        this.deptMembers = new HashSet<Member>();
        this.deptMeetings = new HashSet<Meeting>();
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Clob getContent() {
        return content;
    }

    public void setContent(Clob content) {
        this.content = content;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<Member> getDeptMembers() {
        return deptMembers;
    }

    public Set<Meeting> getDeptMeetings() {
        return deptMeetings;
    }

    public void setDeptMeetings(Set<Meeting> deptMeetings) {
        this.deptMeetings = deptMeetings;
    }
}
