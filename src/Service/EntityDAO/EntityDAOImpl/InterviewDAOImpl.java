package Service.EntityDAO.EntityDAOImpl;

import Entities.Entity.Interview;
import Service.BaseDAO.BaseDAOImpl;
import Service.EntityDAO.EntityDAOInterfaces.InterviewDAO;

/**
 * Created by lurui on 2017/2/25 0025.
 */
public class InterviewDAOImpl<E extends Interview, PK extends String> extends BaseDAOImpl<Interview, String> implements InterviewDAO<E, PK>{
}
