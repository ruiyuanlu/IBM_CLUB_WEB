package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Person;
import com.istc.Service.BaseDAO.BaseDAO;

/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * Person类为类型, String为Person的ID类型
 */
public interface PersonDAO extends BaseDAO<Person, String> {
    Person check(Person person);
    Boolean exist(Person person);
}
