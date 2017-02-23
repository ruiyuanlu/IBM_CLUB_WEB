package com.istc.Entities.Entity;
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
import java.io.Serializable;
import java.util.Set;

/**
 * Created by lurui on 2016/11/20 0020.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Member extends Person implements Serializable{

    @Basic
    private Integer authority;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//将mappedBy设置为对方的集合的引用变量的名称
    private Set<Department> enterDepts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "members")
    private Set<Register> registerRecords;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_homeWork")
    private Set<HomeWork> homeWorks;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> sendMessages;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> receiveMessages;


    public Member() {
    }

    public Set<Message> getSendMessages() {
        return sendMessages;
    }

    public Set<Message> getReceiveMessages() {
        return receiveMessages;
    }

    public void setReceiveMessages(Set<Message> receiveMessages) {
        this.receiveMessages = receiveMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        if (!super.equals(o)) return false;

        Member member = (Member) o;

        if (authority != null ? !authority.equals(member.authority) : member.authority != null) return false;
        if (enterDepts != null ? !enterDepts.equals(member.enterDepts) : member.enterDepts != null) return false;
        if (registerRecords != null ? !registerRecords.equals(member.registerRecords) : member.registerRecords != null)
            return false;
        if (homeWorks != null ? !homeWorks.equals(member.homeWorks) : member.homeWorks != null) return false;
        if (sendMessages != null ? !sendMessages.equals(member.sendMessages) : member.sendMessages != null)
            return false;
        return receiveMessages != null ? receiveMessages.equals(member.receiveMessages) : member.receiveMessages == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        result = 31 * result + (enterDepts != null ? enterDepts.hashCode() : 0);
        result = 31 * result + (registerRecords != null ? registerRecords.hashCode() : 0);
        result = 31 * result + (homeWorks != null ? homeWorks.hashCode() : 0);
        result = 31 * result + (sendMessages != null ? sendMessages.hashCode() : 0);
        result = 31 * result + (receiveMessages != null ? receiveMessages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "authority=" + authority +
                ", enterDepts=" + enterDepts +
                ", registerRecords=" + registerRecords +
                ", homeWorks=" + homeWorks +
                ", sendMessages=" + sendMessages +
                ", receiveMessages=" + receiveMessages +
                '}';
    }

    public void setSendMessages(Set<Message> sendMessages) {
        this.sendMessages = sendMessages;
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

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

}
