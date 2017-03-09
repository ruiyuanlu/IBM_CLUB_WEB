package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.Minister;
import com.istc.Service.BaseDAO.BaseDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public interface MinisterDAO<E extends Minister, PK extends Serializable> extends BaseDAO<Minister, String> {
    Minister get(Minister minister);
    Minister get(String id);
    Boolean exist(E minister);
    Boolean exist(String id);
}
