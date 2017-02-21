package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Meeting;
import com.istc.Entities.ID.MeetingID;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by lurui on 2017/2/5 0005.
 */

@Repository("meetingDAO")
public class MeetingDAOImpl extends BaseDAOImpl<Meeting, MeetingID> implements MeetingDAO{
}
