package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.UploadedFileDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class UploadedFileService {
    private UploadedFileDAO uploadedFileDAO = DAOFactory.getInstance("UploadedFileDAO", UploadedFileDAO.class);
}
