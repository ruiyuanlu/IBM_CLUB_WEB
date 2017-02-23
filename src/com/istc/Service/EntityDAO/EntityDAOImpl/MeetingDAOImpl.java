package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Meeting;
import com.istc.Entities.ID.MeetingID;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */

@Repository("meetingDAO")
public class MeetingDAOImpl extends BaseDAOImpl<Meeting, MeetingID> implements MeetingDAO{

    @Override
    public Meeting get(Integer deptID, Integer meetingTimes) {
        return this.get(new MeetingID(deptID, meetingTimes));
    }

    @Override
    public Meeting getLatest(Integer deptID) {
        String hql = "select max(m.createTime) from Meeting m where m.meetingID.department.deptID = :deptID";
        return this.findUnique(getSession().createQuery(hql).setParameter("deptID", deptID));
    }

    @Override
    public Meeting getEarliest() {
        String hql = "select min(m.createTime) from Meeting m";
        return this.findUnique(hql);
    }

    @Override
    public Integer departmentMeetingsTotalCount(Integer deptID) {
        String hql = "select count(m) from Meeting m where m.meetingID.department.deptID = :deptID";
        return this.count(getSession().createQuery(hql).setParameter("deptID", deptID));
    }

    @Override
    public List<Meeting> getByDepartmentId(Integer deptID) {
        String hql = "select m from Meeting m where m.meetingID.department.deptID = :deptID";
        return this.findAll(getSession().createQuery(hql).setParameter("deptID", deptID));
    }
}
