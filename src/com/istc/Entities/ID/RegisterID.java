package com.istc.Entities.ID;

import com.istc.Entities.Entity.Department;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Embeddable
public class RegisterID implements java.io.Serializable{
    private int times;
    @OneToOne
    private Department department;

    public RegisterID(){
    }

    public Department getDepartment() {
        return department;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterID)) return false;

        RegisterID that = (RegisterID) o;

        if (times != that.times) return false;
        return department != null ? department.equals(that.department) : that.department == null;

    }

    @Override
    public int hashCode() {
        int result = times;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegisterID{" +
                "department=" + department +
                ", times=" + times +
                '}';
    }
}
