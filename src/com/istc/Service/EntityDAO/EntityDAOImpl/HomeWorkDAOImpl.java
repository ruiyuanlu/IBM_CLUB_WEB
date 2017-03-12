package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.HomeWork;
import com.istc.Entities.ID.HomeWorkID;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.HomeWorkDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Repository("homeWorkDAO")
public class HomeWorkDAOImpl extends BaseDAOImpl<HomeWork, HomeWorkID> implements HomeWorkDAO{
}
