package com.istc.Entities;

import com.istc.Entities.ID.RegisterID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Register implements Serializable{

    @Id
    private RegisterID registerID;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "register_member", joinColumns = {@JoinColumn(name = "register_dept"),
            @JoinColumn(name = "register_times")}, inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> members;

    @Version
    private int registerVersion;

    public Register() {
        this.registerID = new RegisterID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Register)) return false;

        Register register = (Register) o;

        if (registerVersion != register.registerVersion) return false;
        if (registerID != null ? !registerID.equals(register.registerID) : register.registerID != null) return false;
        return members != null ? members.equals(register.members) : register.members == null;

    }

    @Override
    public int hashCode() {
        int result = registerID != null ? registerID.hashCode() : 0;
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + registerVersion;
        return result;
    }

    @Override
    public String toString() {
        return "Register{" +
                "members=" + members +
                ", registerID=" + registerID +
                ", registerVersion=" + registerVersion +
                '}';
    }

    public void setDepartment(Department department) {
        this.registerID.setDepartment(department);
    }

    public RegisterID getRegisterID() {
        return registerID;
    }

    public void setRegisterID(RegisterID registerID) {
        this.registerID = registerID;
    }

    public int getTimes() {
        return registerID.getTimes();
    }

    public Department getDepartment() {
        return this.registerID.getDepartment();
    }

    public void setTimes(int times) {
        this.registerID.setTimes(times);
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    private int getRegisterVersion() {
        return registerVersion;
    }

    private void setRegisterVersion(int registerVersion) {
        this.registerVersion = registerVersion;
    }

}
