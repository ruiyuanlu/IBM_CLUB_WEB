package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import com.istc.Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class MeetingService {
    private MeetingDAO meetingDAO = DAOFactory.getInstance("MeetingDAO", MeetingDAO.class);
}
