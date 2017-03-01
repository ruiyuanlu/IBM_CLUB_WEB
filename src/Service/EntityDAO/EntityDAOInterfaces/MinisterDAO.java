package Service.EntityDAO.EntityDAOInterfaces;

import Entities.Entity.Minister;
import Service.BaseDAO.BaseDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public interface MinisterDAO<E extends Minister, PK extends Serializable> extends BaseDAO<Minister, String> {
}
