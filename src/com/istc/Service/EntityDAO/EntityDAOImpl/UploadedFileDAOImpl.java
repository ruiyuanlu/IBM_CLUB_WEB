package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.UploadedFile;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.UploadedFileDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Repository("uploadedFileDAO")
public class UploadedFileDAOImpl extends BaseDAOImpl<UploadedFile, Integer> implements UploadedFileDAO {
}
