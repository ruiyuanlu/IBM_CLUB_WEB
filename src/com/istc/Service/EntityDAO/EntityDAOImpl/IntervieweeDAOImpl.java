package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.IntervieweeDAO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

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
    public Boolean isPassed(E interviewee) {
        Interviewee inter = (Interviewee)this.get(interviewee);
        return inter == null ? null : inter.getIsPassed();
    }

    @Override
    public void delete(String[] intervieweeIDs) {
        StringBuilder strb = new StringBuilder("delete from Interviewee where id = " + intervieweeIDs[0]);
        for(int i = 1; i < intervieweeIDs.length; i++)
            strb.append("or id = " + intervieweeIDs[i]);
        this.excuteUpdate(strb.toString());
    }

    @Override
    public List<Interviewee> get(String[] ids) {
        StringBuilder strb = new StringBuilder("select i from Interviewee i where i.id = " + ids[0]);
        for(int i = 1; i < ids.length; i++)
            strb.append("or i.id = " + ids[i]);
        return (List<Interviewee>)this.getSession().createQuery(strb.toString()).list();
    }
}