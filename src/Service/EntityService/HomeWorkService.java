package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.HomeWorkDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class HomeWorkService {
    private HomeWorkDAO homeWorkDAO = DAOFactory.getInstance("HomeWorkDAO", HomeWorkDAO.class);
}
