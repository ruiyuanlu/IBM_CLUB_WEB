package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.UploadedFileDAO;
import com.istc.Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class UploadedFileService {
    private UploadedFileDAO uploadedFileDAO = DAOFactory.getInstance("UploadedFileDAO", UploadedFileDAO.class);
}
