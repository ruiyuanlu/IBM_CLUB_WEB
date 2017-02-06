package Service.EntityDAO.EntityDAOInterfaces;

import Entities.Entity.Person;
import Service.BaseDAO.BaseDAO;

/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * Person类为类型, String为Person的ID类型
 */
public interface PersonDAO extends BaseDAO<Person, String> {
}
