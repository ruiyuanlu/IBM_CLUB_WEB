package com.istc.Service.EntityService;

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
}
