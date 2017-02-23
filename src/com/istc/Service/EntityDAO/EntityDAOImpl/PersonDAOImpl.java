package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Person;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.PersonDAO;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * 继承了BaseDAOImpl中的所有方法
 * 同时也可以通过PersonDAO接口进行特有方法的扩展
 */
@Repository("personDAO")
public class PersonDAOImpl<E extends Person, PK extends Serializable> extends BaseDAOImpl<Person, String> implements PersonDAO<E, PK>{
    public Person get(E person) {
        Person p = this.get(person.getID());
        return p == null ? null : p.getPassword().equals(person.getPassword()) ? p : null;
    }

    @Override
    public Boolean exist(E person) {
        return get(person) != null;
    }
}
