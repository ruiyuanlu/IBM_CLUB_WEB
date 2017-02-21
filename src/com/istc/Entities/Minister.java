package com.istc.Entities;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Department> manageDepts;

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
        result = 31 * result + (manageDepts != null ? manageDepts.hashCode() : 0);
        return result;
    }
}
