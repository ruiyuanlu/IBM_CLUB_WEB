package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.RegisterDAO;
import com.istc.Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class RegisterService {
    private RegisterDAO registerDAO = DAOFactory.getInstance("RegisterDAO", RegisterDAO.class);
}
