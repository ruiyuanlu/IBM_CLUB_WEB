package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Register;
import com.istc.Entities.ID.RegisterID;
import com.istc.Service.BaseDAO.BaseDAO;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public interface RegisterDAO extends BaseDAO<Register, RegisterID> {
    boolean saveOrUpdate(Register register);
}
