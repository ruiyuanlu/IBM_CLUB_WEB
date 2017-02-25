package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Interviewee;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/25 0025.
 */
public interface IntervieweeDAO<E extends Interviewee, PK extends Serializable> extends PersonDAO<Interviewee, String> {
    Boolean isPassed(String id);
    Boolean isPassed(Interviewee interviewee);
}