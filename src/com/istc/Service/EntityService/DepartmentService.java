package com.istc.Service.EntityService;


import com.istc.Entities.Entity.Department;
import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("departmentService")
@Transactional(rollbackFor = Exception.class)
public class DepartmentService {
    @Resource
    private transient DepartmentDAO departmentDAO;

    public void add(Department department){
        if (!(department==null||department.getDeptID()==null||department.getDeptID()==0))
        departmentDAO.save(department);
    }
    public void update(Department department){
        if (!(department==null||department.getDeptID()==null||department.getDeptID()==0))
        departmentDAO.edit(department);
    }
    public List<Member> getInsideMembers(int deptID){
        System.out.println("deptID="+deptID);
        System.out.println("deptID="+deptID);
        if (deptID==0)
            return null;
       Department department= (Department)departmentDAO.get(deptID);

       Set<Member> memberSet=department.getMembers();

       List<Member> result=new ArrayList<Member>();

       for (Member member:memberSet){
           result.add(member);
       }

       return result;
    }
    public boolean exist(int dept){
        return  departmentDAO.exist(dept);
    }

    public Department get(Integer deptID){
        if (deptID==0||deptID==null)
            return null;
        return (Department) departmentDAO.get(deptID);
    }

    public Department get(Department dept){
        if (dept==null||dept.getDeptID()==0||dept.getDeptID()==null)
            return null;
        return (Department) departmentDAO.get(dept.getDeptID());
    }
}
