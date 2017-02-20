package com.istc.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by lurui on 2016/11/18 0018.
 */

/**
 * 由于年龄随时间变化
 * 因此使用birthday进行计算，不存储age
 */
@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable{

    @Id
    @Column(length = 20)
    protected String  ID;
    @Column
    protected String password;
    @Column(name = "name",length = 45)
    protected String name;
    protected String description;
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Calendar birthDay;
    @Column(name = "qq",length = 20)
    protected String QQ;
    @Column(name = "phone",length = 20)
    protected String phoneNumber;
    @Basic(fetch = FetchType.EAGER)
    protected boolean gender;//性别
    @Transient
    private final static Boolean MALE = true;
    @Version
    private int peopleVersion;

    public Person() {
    }

    private int getPeopleVersion() {
        return peopleVersion;
    }

    private void setPeopleVersion(int peopleVersion) {
        this.peopleVersion = peopleVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return ID != null ? ID.equals(person.ID) : person.ID == null;
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getGender() {
        return this.gender == MALE;
    }

    public void setGender(Boolean isMale) {
        this.gender = isMale;
    }

    public Boolean isMale(){
        return this.gender == MALE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isGender() {
        return gender;
    }

    public static Boolean getMALE() {
        return MALE;
    }

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

}
