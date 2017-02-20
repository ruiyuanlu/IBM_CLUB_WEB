package com.istc.Entities;
/**--------------------------------------------------------------------------------
 *
 * proxy,权限管理
 * 把权限管理集中起来
 * 作为M层的安全机制
 *
 * C->接口，修改部门信息(主席)->proxy,判定权限，M层查询(匹配用户名和密码，查询权限)，如果均匹配，正常执行
 * 如果条件不满足，抛出illegalAccess
 * Java有一个Proxy类，叫代理，invoke(Method,args)->modify
 * 1.对象继承层次，只有chairman 对象可以
 * 2.给定的其他条件(咱们定义的权限)
 *
 * 所有的权限管理都放在proxy里
 *
 * */
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lurui on 2016/11/20 0020.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Member extends Person {

    @Basic
    private Integer authority;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//将mappedBy设置为对方的集合的引用变量的名称
    private Set<Department> enterDepts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "members")
    private Set<Register> registerRecords;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_homeWork")
    private Set<HomeWork> homeWorks;


    public Member() {
    }

    public Set<Register> getRegisterRecords() {
        return registerRecords;
    }

    public void setRegisterRecords(Set<Register> registerRecords) {
        this.registerRecords = registerRecords;
    }

    public Set<Department> getEnterDepts() {
        return enterDepts;
    }

    public void setEnterDepts(Set<Department> enterDepts) {
        this.enterDepts = enterDepts;
    }

    public Set<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public Set<Department> getManageDepts() {
        return enterDepts;
    }

    public void setManageDepts(Set<Department> manageDepts) {
        this.enterDepts = manageDepts;
    }

    /**
     * 修改
     */
    //事物处理
    public void addDepartment(Department dept){
        if (dept == null)return;
        if (this.enterDepts == null)this.enterDepts = new HashSet<>();
        enterDepts.add(dept);
        return;
    }


    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}
