package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Department;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Repository("departmentDAO")
public class DepartmentDAOImpl<E extends Department, PK extends String> extends BaseDAOImpl<Department, Integer> implements DepartmentDAO<E, PK> {
    @Override
    public Boolean exist(int dept) {
        return this.get(dept)!=null;
    }
}
