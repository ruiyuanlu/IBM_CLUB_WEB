package com.istc.Service.EntityService;


import com.istc.Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import com.istc.Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class DepartmentService {
    private DepartmentDAO departmentDAO = DAOFactory.getInstance("DepartmentDAO", DepartmentDAO.class);
}
