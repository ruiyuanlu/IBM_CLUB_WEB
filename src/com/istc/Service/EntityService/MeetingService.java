package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import com.istc.Utilities.DAOFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("meetingService")
@Transactional(rollbackFor = Exception.class)
public class MeetingService {
    @Resource
    private transient MeetingDAO meetingDAO;
}
