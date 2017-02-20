package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Person;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.PersonDAO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * 继承了BaseDAOImpl中的所有方法
 * 同时也可以通过PersonDAO接口进行特有方法的扩展
 */
@Repository("personDAO")
public class PersonDAOImpl extends BaseDAOImpl<Person, String> implements PersonDAO{
    @Override
    public Person check(Person person) {
        String hql = "from "+ person.getClass().getSimpleName() +" p where p.id =:id and p.password =:password";
        Query query = getSession().createQuery(hql).setParameter("id", person.getID()).setParameter("password", person.getPassword());
        return findUnique(query);
    }

    @Override
    public Boolean exist(Person person) {
        return check(person) != null;
    }
}
