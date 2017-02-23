package com.istc.bean;

import java.util.Set;

/**
 * Created by lurui on 2016/11/20 0020.
 */

public class Member extends Person {
    //@ManyToMany(fetch = FetchType.LAZY)
    private Set<Department> departments;
    //@OneToMany(fetch = FetchType.LAZY)
    private Set<HomeWork> homeWorks;
    //@Basic
    private Integer authority;

    public Set<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}
