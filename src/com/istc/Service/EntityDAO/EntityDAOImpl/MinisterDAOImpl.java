package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Minister;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
@Repository("MinisterDAO")
public class MinisterDAOImpl<E extends Minister, PK extends Serializable> extends MemberDAOImpl<Minister, String> implements MinisterDAO<E, PK> {
}
