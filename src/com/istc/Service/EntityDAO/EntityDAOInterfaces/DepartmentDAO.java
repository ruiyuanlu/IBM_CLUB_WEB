package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Department;
import com.istc.Service.BaseDAO.BaseDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public interface DepartmentDAO<E extends Department, PK extends Serializable> extends BaseDAO<Department, Integer> {
    Boolean exist(int id);
}
