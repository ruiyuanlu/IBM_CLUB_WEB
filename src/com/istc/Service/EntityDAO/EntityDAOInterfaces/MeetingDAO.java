package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Meeting;
import com.istc.Entities.ID.MeetingID;
import com.istc.Service.BaseDAO.BaseDAO;

import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public interface MeetingDAO extends BaseDAO<Meeting, MeetingID> {
    Meeting get(Integer deptID, Integer meetingTimes);
    Meeting getLatest(Integer deptID);
    Meeting getEarliest();
    Integer departmentMeetingsTotalCount(Integer dpartmentId);
    List<Meeting> getByDepartmentId(Integer dpartmentId);
}
