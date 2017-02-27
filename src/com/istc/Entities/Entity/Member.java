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
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lurui on 2016/11/20 0020.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Member extends Person implements Serializable{

    @Basic
    private Integer authority;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "members")//将mappedBy设置为对方的集合的引用变量的名称
    private Set<Department> enterDepts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "members")
    private Set<Register> registerRecords;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_homeWork")
    private Set<HomeWork> homeWorks;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_sendMessage")
    private Set<Message> sendMessages;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_receiveMessage")
    private Set<Message> receiveMessages;


    public Member() {
    }

    public void addRegisterRecords(Register register){
        if(register == null)return;
        if(this.registerRecords == null) this.registerRecords = new HashSet<>();
        if(register != null)this.registerRecords.add(register);
    }


    public void addRegisterRecords(Register[] registerRecords){
        if(registerRecords == null)return;
        if(this.registerRecords == null) this.registerRecords = new HashSet<>();
        for(Register register: registerRecords)
            if(register != null)this.registerRecords.add(register);
    }

    public void deleteRegisterRecords(Register register){
        if(register == null || this.registerRecords == null)return;
        if(register != null)this.registerRecords.remove(register);
    }

    public void deleteRegisterRecords(Register[] registerRecords){
        if(registerRecords == null || this.registerRecords == null)return;
        for(Register register: registerRecords)
            if(register != null)this.registerRecords.remove(register);
    }
    public void addReceiveMessage(Message receiveMessage){
        if(receiveMessage == null)return;
        if(this.receiveMessages == null) this.receiveMessages = new HashSet<>();
        if(receiveMessage != null)this.receiveMessages.add(receiveMessage);
    }

    public void addReceiveMessages(Message[] receiveMessages){
        if(receiveMessages == null)return;
        if(this.receiveMessages == null) this.receiveMessages = new HashSet<>();
        for(Message receiveMessage: receiveMessages)
            if(receiveMessage != null)this.receiveMessages.add(receiveMessage);
    }


    public void deleteReceiveMessage(Message receiveMessage){
        if(receiveMessage == null || this.receiveMessages == null)return;
        if(receiveMessage != null)this.receiveMessages.remove(receiveMessage);
    }


    public void deleteReceiveMessages(Message[] receiveMessages){
        if(receiveMessages == null || this.receiveMessages == null)return;
        for(Message receiveMessage: receiveMessages)
            if(receiveMessage != null)this.receiveMessages.remove(receiveMessage);
    }
    public void addSendMessage(Message sendMessage){
        if(sendMessage == null)return;
        if(this.sendMessages == null) this.sendMessages = new HashSet<>();
        if(sendMessage != null)this.sendMessages.add(sendMessage);
    }


    public void addSendMessages(Message[] sendMessages){
        if(sendMessages == null)return;
        if(this.sendMessages == null) this.sendMessages = new HashSet<>();
        for(Message sendMessage: sendMessages)
            if(sendMessage != null)this.sendMessages.add(sendMessage);
    }


    public void deleteSendMessage(Message sendMessage){
        if(sendMessage == null || this.sendMessages == null)return;
        if(sendMessage != null)this.sendMessages.remove(sendMessage);
    }


    public void deleteSendMessages(Message[] sendMessages){
        if(sendMessages == null || this.sendMessages == null)return;
        for(Message sendMessage: sendMessages)
            if(sendMessage != null)this.sendMessages.remove(sendMessage);
    }

    public void addHomeWork(HomeWork homeWork){
        if(homeWork == null)return;
        if(this.homeWorks == null) this.homeWorks = new HashSet<>();
        if(homeWork != null)this.homeWorks.add(homeWork);
    }


    public void addHomeWorks(HomeWork[] homeWorks){
        if(homeWorks == null)return;
        if(this.homeWorks == null) this.homeWorks = new HashSet<>();
        for(HomeWork homeWork: homeWorks)
            if(homeWork != null)this.homeWorks.add(homeWork);
    }


    public void deleteHomeWork(HomeWork homeWork){
        if(homeWork == null || this.homeWorks == null)return;
        if(homeWork != null)this.homeWorks.remove(homeWork);
    }


    public void deleteHomeWorks(HomeWork[] homeWorks){
        if(homeWorks == null || this.homeWorks == null)return;
        for(HomeWork homeWork: homeWorks)
            if(homeWork != null)this.homeWorks.remove(homeWork);
    }



    // getter && setters && toString && hashCode
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
