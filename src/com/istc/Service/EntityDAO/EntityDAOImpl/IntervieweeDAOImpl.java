package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Interviewee;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/25 0025.
 */
@Repository("intervieweeDAO")
public class IntervieweeDAOImpl<E extends Interviewee, PK extends Serializable> extends PersonDAOImpl<E, PK> {
}
