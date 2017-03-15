package com.istc.Entities.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by lurui on 2016/11/18 0018.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable{

    @Id
    @Column(length = 20)
    protected String  ID;
    protected Calendar birthday;
    @Column(name = "name",length = 45)
    protected String name;
    @Lob
    protected String description;
    @Column(length = 128)
    protected String password;
    @Column(name = "qq",length = 20)
    protected String QQ;
    @Column(name = "phone",length = 20)
    protected String phoneNumber;
    @Basic(fetch = FetchType.EAGER)
    protected boolean gender;//性别
    protected int authority;
    @Transient
    private final static Boolean MALE = true;
    @Version
    private Integer peopleVersion;

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (gender != person.gender) return false;
        if (authority != person.authority) return false;
        if (ID != null ? !ID.equals(person.ID) : person.ID != null) return false;
        if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (description != null ? !description.equals(person.description) : person.description != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (QQ != null ? !QQ.equals(person.QQ) : person.QQ != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(person.phoneNumber) : person.phoneNumber != null) return false;
        return peopleVersion != null ? peopleVersion.equals(person.peopleVersion) : person.peopleVersion == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (QQ != null ? QQ.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (gender ? 1 : 0);
        result = 31 * result + authority;
        result = 31 * result + (peopleVersion != null ? peopleVersion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", QQ='" + QQ + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", authority=" + authority +
                ", peopleVersion=" + peopleVersion +
                '}';
    }

    private Integer getPeopleVersion() {
        return peopleVersion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void setPeopleVersion(Integer peopleVersion) {
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

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Integer getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
