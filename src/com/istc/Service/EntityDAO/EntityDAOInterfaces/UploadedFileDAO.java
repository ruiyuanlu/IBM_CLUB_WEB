package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.UploadedFile;
import com.istc.Service.BaseDAO.BaseDAO;

import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public interface UploadedFileDAO extends BaseDAO<UploadedFile, Integer> {
    List<UploadedFile> get(Integer[] fileIDs);
}
