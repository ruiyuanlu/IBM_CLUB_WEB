package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class MinisterService {
    private MinisterDAO ministerDAO = DAOFactory.getInstance("MinisterDAO", MinisterDAO.class);
}
