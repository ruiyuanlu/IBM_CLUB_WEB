package Service.EntityDAO.EntityDAOImpl;

import Entities.Entity.Person;
import Service.BaseDAO.BaseDAOImpl;
import Service.EntityDAO.EntityDAOInterfaces.PersonDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * 继承了BaseDAOImpl中的所有方法
 * 同时也可以通过PersonDAO接口进行特有方法的扩展
 */
public class PersonDAOImpl<E extends Person, PK extends Serializable> extends BaseDAOImpl<Person, String> implements PersonDAO<E, PK>{
}
