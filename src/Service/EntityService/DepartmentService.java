package Service.EntityService;


import Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class DepartmentService {
    private DepartmentDAO departmentDAO = DAOFactory.getInstance("DepartmentDAO", DepartmentDAO.class);
}
