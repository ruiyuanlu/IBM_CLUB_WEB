package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Register;
import com.istc.Entities.ID.RegisterID;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.RegisterDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Repository("registerDAO")
public class RegisterDAOImpl extends BaseDAOImpl<Register, RegisterID> implements RegisterDAO {
}
