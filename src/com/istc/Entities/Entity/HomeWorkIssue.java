package com.istc.Entities.Entity;

/**
 * Created by lurui on 2017/3/8 0008.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * 作业发布的实体
 */
@Entity
public class HomeWorkIssue implements Serializable{
    @Id
    private Integer id;
    @Lob
    private String homeWorkRequirement;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;

    @ManyToOne
    private Department department;
    @OneToMany
    private Set<HomeWork> homeWorks;

    @Version
    private int version;

    public HomeWorkIssue() {
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getHomeWorkRequirement() {
        return homeWorkRequirement;
    }

    public void setHomeWorkRequirement(String homeWorkRequirement) {
        this.homeWorkRequirement = homeWorkRequirement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }
}
