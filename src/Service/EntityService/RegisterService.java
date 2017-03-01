package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.RegisterDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class RegisterService {
    private RegisterDAO registerDAO = DAOFactory.getInstance("RegisterDAO", RegisterDAO.class);
}
