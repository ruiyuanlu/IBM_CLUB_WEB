package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.UploadedFile;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.UploadedFileDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Repository("uploadedFileDAO")
public class UploadedFileDAOImpl extends BaseDAOImpl<UploadedFile, Integer> implements UploadedFileDAO {
    @Override
    public List<UploadedFile> get(Integer[] fileIDs) {
        StringBuilder hql = new StringBuilder("from UploadedFile f where f.fileID = ").append(fileIDs[0]);
        for(int i = 1; i < fileIDs.length; i++)
            hql.append(" or f.fileID = ").append(fileIDs[i]);
        return (List<UploadedFile>)this.getSession().createQuery(hql.toString()).list();
    }
}
