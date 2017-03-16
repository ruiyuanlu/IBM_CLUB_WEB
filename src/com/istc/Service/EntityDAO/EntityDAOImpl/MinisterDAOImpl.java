package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.Minister;
import com.istc.Entities.Entity.Person;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import org.springframework.stereotype.Repository;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
@Repository("ministerDAO")
public class MinisterDAOImpl<E extends Minister, PK extends Serializable> extends BaseDAOImpl<Minister, String> implements MinisterDAO<E, PK> {

    @Override
    public Minister get(Minister minister) {
        Minister minister1 = this.get(minister.getID());
        return minister1 == null ? null : minister1.getPassword().equals(minister.getPassword()) ? minister1 : null;
    }

    @Override
    public Boolean exist(E minister) {
        return !(get(minister.getID())==null);
    }

    @Override
    public Boolean exist(String id) {
        return !(get(id)==null);
    }
}


