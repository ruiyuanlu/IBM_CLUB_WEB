package com.istc.Entities.Entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lurui on 2016/11/21 0021.
 */
/***
 * @Version 控制的乐观锁如果在父类中使用了，就不能在子类中使用
 * 例如：People中使用了乐观锁，则Member和Minister和President都不能再使用乐观锁
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Minister extends Member implements Serializable{

    @ManyToMany
    @JoinTable(name = "dept_minister",
            joinColumns = {@JoinColumn(name = "minister_id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id")})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Department> manageDepts;

    public void addManageDept(Department department){
        if(department == null)return;
        if(this.manageDepts == null) this.manageDepts = new HashSet<>();
        if(department != null)this.manageDepts.add(department);
    }

    public void addManageDepts(Department[] departments){
        if(departments == null)return;
        if(this.manageDepts == null) this.manageDepts = new HashSet<>();
        for(Department department: departments)
            if(department != null)this.manageDepts.add(department);
    }

    public void deleteManageDept(Department department){
        if(department == null || this.manageDepts == null)return;
        if(department != null)this.manageDepts.remove(department);
    }

    public void deleteManageDepts(Department[] departments){
        if(departments == null || this.manageDepts == null)return;
        for(Department department: departments)
            if(department != null)this.manageDepts.remove(department);
    }

    //getters and setters
    public Minister(){
    }

    public Set<Department> getManageDepts() {
        return manageDepts;
    }

    public void setManageDepts(Set<Department> manageDepts) {
        this.manageDepts = manageDepts;
    }

    @Override
    public String toString() {
        return super.toString()+"Minister{" +
                "manageDepts=" + manageDepts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Minister)) return false;
        if (!super.equals(o)) return false;

        Minister minister = (Minister) o;

        return manageDepts != null ? manageDepts.equals(minister.manageDepts) : minister.manageDepts == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
