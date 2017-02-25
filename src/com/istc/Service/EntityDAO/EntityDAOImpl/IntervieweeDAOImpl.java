package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Entities.Entity.Person;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.IntervieweeDAO;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/25 0025.
 */
@Repository("intervieweeDAO")
public class IntervieweeDAOImpl<E extends Interviewee, PK extends Serializable> extends PersonDAOImpl<Interviewee, String> implements IntervieweeDAO<E, PK>{

    @Override
    public Boolean isPassed(String id) {
        Interviewee interviewee = (Interviewee)this.get(id);
        return interviewee == null ? null : interviewee.getIsPassed();
    }

    @Override
    public Boolean isPassed(Interviewee interviewee) {
        Interviewee inter = (Interviewee)this.get(interviewee);
        return inter == null ? null : inter.getIsPassed();
    }
}