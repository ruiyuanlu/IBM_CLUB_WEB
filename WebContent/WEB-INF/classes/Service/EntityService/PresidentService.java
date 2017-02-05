package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.PresidentDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class PresidentService {
    private PresidentDAO presidentDAO = DAOFactory.getInstance("PresidentDAO", PresidentDAO.class);
}
