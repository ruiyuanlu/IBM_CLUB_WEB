package com.istc.Entities.Entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by lurui on 2017/2/25 0025.
 */

/**
 * 面试者
 * passed 代表是否通过
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Interviewee extends Person {
    Boolean passed;

    public Interviewee() {
    }

    public Interviewee(String id, Boolean passed) {
        this.setID(id);
        this.passed = passed;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
