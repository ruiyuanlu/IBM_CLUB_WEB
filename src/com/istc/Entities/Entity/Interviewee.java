package com.istc.Entities.Entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by lurui on 2017/2/25 0025.
 */

/**
 * 面试者
 * isPassed 代表是否通过
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Interviewee extends Person {
    Boolean isPassed;

    public Interviewee() {
    }

    public Interviewee(String id, Boolean isPassed) {
        this.setID(id);
        this.isPassed = isPassed;
    }

    public Boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Boolean isPassed) {
        this.isPassed = isPassed;
    }
}
