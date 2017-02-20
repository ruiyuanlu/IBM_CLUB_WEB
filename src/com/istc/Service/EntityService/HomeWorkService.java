package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.HomeWorkDAO;
import com.istc.Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class HomeWorkService {
    private HomeWorkDAO homeWorkDAO = DAOFactory.getInstance("HomeWorkDAO", HomeWorkDAO.class);
}
