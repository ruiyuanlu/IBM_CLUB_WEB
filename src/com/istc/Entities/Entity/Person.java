package com.istc.Entities.Entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by lurui on 2016/11/18 0018.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable{

    @Id
    @Column(length = 20)
    protected String  ID;
    private Integer age;
    @Column(name = "name",length = 45)
    protected String name;
    protected String description;
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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", QQ='" + QQ + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", peopleVersion=" + peopleVersion +
                '}';
    }
    private int getPeopleVersion() {
        return peopleVersion;
    }

    private void setPeopleVersion(int peopleVersion) {
        this.peopleVersion = peopleVersion;
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
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
