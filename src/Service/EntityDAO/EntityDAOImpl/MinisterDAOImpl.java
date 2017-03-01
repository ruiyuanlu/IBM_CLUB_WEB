package Service.EntityDAO.EntityDAOImpl;

import Entities.Entity.Minister;
import Service.BaseDAO.BaseDAO;
import Service.BaseDAO.BaseDAOImpl;
import Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public class MinisterDAOImpl<E extends Minister, PK extends Serializable> extends BaseDAOImpl<Minister, String> implements MinisterDAO<E, PK> {
}
