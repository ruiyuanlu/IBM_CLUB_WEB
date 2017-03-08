package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Register;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.RegisterDAO;
import com.istc.Utilities.DAOFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("registerService")
@Transactional(rollbackFor = Exception.class)
public class RegisterService {
    @Resource
    private transient RegisterDAO registerDAO;

    public boolean add(Register register){
        if(register == null || register.getRegisterID() == null)return false;
        registerDAO.saveOrUpdate(register);
        return true;
    }

    public boolean edit(Register register){
        if(register == null || register.getRegisterID() == null)return false;
        registerDAO.edit(register);
        return true;
    }
}
