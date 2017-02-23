package com.istc.Entities.ID;

import com.istc.Entities.Entity.Department;
import com.istc.Entities.Entity.Member;

import javax.persistence.*;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class HomeWorkID implements java.io.Serializable{
    @ManyToOne
    private Department department;
    private int times;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member homeWorkSubmitter;

    public HomeWorkID() {
    }

    public Member getHomeWorkSubmitter() {
        return homeWorkSubmitter;
    }

    public void setHomeWorkSubmitter(Member homeWorkSubmitter) {
        this.homeWorkSubmitter = homeWorkSubmitter;
    }

    public Department getDepartment() {
        return department;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeWorkID)) return false;

        HomeWorkID that = (HomeWorkID) o;

        if (times != that.times) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        return homeWorkSubmitter != null ? homeWorkSubmitter.equals(that.homeWorkSubmitter) : that.homeWorkSubmitter == null;

    }

    @Override
    public int hashCode() {
        int result = department != null ? department.hashCode() : 0;
        result = 31 * result + times;
        result = 31 * result + (homeWorkSubmitter != null ? homeWorkSubmitter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HomeWorkID{" +
                "department=" + department +
                ", times=" + times +
                ", homeWorkSubmitter=" + homeWorkSubmitter +
                '}';
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
