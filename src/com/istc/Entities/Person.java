package com.istc.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by lurui on 2016/11/18 0018.
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
    @Column
    protected String email;
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Calendar birthDay;
    @Column(name = "name",length = 45)
    protected String name;
    @Column(length = 403)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        if (gender != person.gender) return false;
        if (peopleVersion != person.peopleVersion) return false;
        if (ID != null ? !ID.equals(person.ID) : person.ID != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (birthDay != null ? !birthDay.equals(person.birthDay) : person.birthDay != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (description != null ? !description.equals(person.description) : person.description != null) return false;
        if (QQ != null ? !QQ.equals(person.QQ) : person.QQ != null) return false;
        return phoneNumber != null ? phoneNumber.equals(person.phoneNumber) : person.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (QQ != null ? QQ.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (gender ? 1 : 0);
        result = 31 * result + peopleVersion;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthDay=" + birthDay +
                ", ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", QQ='" + QQ + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", peopleVersion=" + peopleVersion +
                '}';
    }

    public Integer getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
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

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
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

}
