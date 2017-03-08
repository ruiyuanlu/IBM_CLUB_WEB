package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Person;
import com.istc.Service.BaseDAO.BaseDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * Person类为类型, String为Person的ID类型
 */
public interface PersonDAO<E extends Person, PK extends Serializable> extends BaseDAO<Person, String> {
    Person get(E person);
    Person get(String id);
    Boolean exist(E person);
    Boolean exist(String id);
}
