package Entities.ID;

import Entities.Entity.Department;
import Entities.Entity.Member;

import javax.persistence.*;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class HomeWorkID implements java.io.Serializable{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department dept;
    private int times;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member homeWorkSubmitter;

    public HomeWorkID() {
    }

    public HomeWorkID(Department dept, Member homeWorkSubmitter, int times) {
        this.dept = dept;
        this.homeWorkSubmitter = homeWorkSubmitter;
        this.times = times;
    }

    public Member getHomeWorkSubmitter() {
        return homeWorkSubmitter;
    }

    public void setHomeWorkSubmitter(Member homeWorkSubmitter) {
        this.homeWorkSubmitter = homeWorkSubmitter;
    }

    public Department getDept() {
        return dept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeWorkID)) return false;

        HomeWorkID that = (HomeWorkID) o;

        if (times != that.times) return false;
        if (dept != null ? !dept.equals(that.dept) : that.dept != null) return false;
        return homeWorkSubmitter != null ? homeWorkSubmitter.equals(that.homeWorkSubmitter) : that.homeWorkSubmitter == null;

    }

    @Override
    public int hashCode() {
        int result = dept != null ? dept.hashCode() : 0;
        result = 31 * result + times;
        result = 31 * result + (homeWorkSubmitter != null ? homeWorkSubmitter.hashCode() : 0);
        return result;
    }

    public void setDept(Department deptID) {
        this.dept = deptID;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
