package com.istc.Service.EntityService;


import com.istc.Entities.Entity.Department;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("departmentService")
@Transactional(rollbackFor = Exception.class)
public class DepartmentService {
    @Resource
    private transient DepartmentDAO departmentDAO;

    public Department get(Integer deptID){
        return departmentDAO.get(deptID);
    }

    public Department get(Department dept){
        return departmentDAO.get(dept.getDeptID());
    }
}
