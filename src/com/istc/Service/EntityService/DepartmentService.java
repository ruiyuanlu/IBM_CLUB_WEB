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

    public void add(Department department){
        departmentDAO.save(department);
    }
    public void update(Department department){
        departmentDAO.edit(department);
    }

    public Department get(Integer deptID){
        return (Department) departmentDAO.get(deptID);
    }

    public Department get(Department dept){
        return (Department) departmentDAO.get(dept.getDeptID());
    }
}
