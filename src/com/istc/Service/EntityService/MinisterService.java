package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Minister;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import com.istc.Utilities.DAOFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("ministerService")
@Transactional(rollbackFor = Exception.class)
public class MinisterService {
    @Resource
    private transient MinisterDAO ministerDAO;
    public void update(Minister minister){
        if (minister!=null&&minister.getID()!=null)
        ministerDAO.edit(minister);
    }

    public Minister get(Minister minister){
        if (minister==null)return null;
        return ministerDAO.get(minister);
    }
    public Minister get(String id){
        return ministerDAO.get(id);
    }
    public boolean exist(Minister minister){
        return minister == null ? false : get(minister) != null;
    }
    public boolean exist(String id){
        return id == null || "".equals(id) ? false : get(id) != null;
    }
}
