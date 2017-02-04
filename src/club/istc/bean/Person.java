package club.istc.bean;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.*;


/**
 * Created by lurui on 2016/11/18 0018.
 */
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name="tb_person")
public class Person implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
    //@Column(length = 20)
    protected String  ID;    
    //@Column(length = 20)
    protected String  password;
    //@Column(name = "name",length = 45)
    protected String name;
    //@Lob
    protected Clob description;
    //@Column(name = "qq",length = 20)
    protected String QQ;    
    //@Column(name = "Authority",length = 5)
    protected Integer Authority=000;
    //@Column(name = "phone",length = 20)
    protected String phoneNumber;
    //@Basic(fetch = FetchType.EAGER)
    protected boolean gender;
	//@Basic(fetch = FetchType.EAGER)
	//    @Id
	    private Integer age;
    //@Transient
    protected final static Boolean MALE = true;

    public Person() {
    }

    public Clob getDescription(){
        return description;
    }

    public void setDescription(Clob description){
        this.description = description;
    }

    public Integer getAuthority() {
		return Authority;
	}

	public void setAuthority(Integer authority) {
		Authority = authority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
