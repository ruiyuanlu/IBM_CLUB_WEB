package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Meeting;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("meetingService")
@Transactional(rollbackFor = Exception.class)
public class MeetingService {
    @Resource
    private transient MeetingDAO meetingDAO;

    /**
     * 增删改查
     */
    public void add(Meeting meeting){
        if (meeting!=null&&meeting.getMeetingID()!=null)
        meetingDAO.save(meeting);
    }

    public void delete(Meeting meeting){
        if (meeting!=null&&meeting.getMeetingID()!=null)
        meetingDAO.delete(meeting.getMeetingID());
    }

    public void delete(Integer deptID, Integer meetingTimes){
        if (deptID!=0&&deptID!=null&&meetingTimes!=0&&meetingTimes!=null)
        delete(new Meeting(deptID, meetingTimes));
    }
    public void edit(Meeting meeting){
        if (meeting!=null&&meeting.getMeetingID()!=null)
        meetingDAO.edit(meeting);
    }

    public Meeting get(Meeting meeting){
        if (meeting!=null&&meeting.getMeetingID()!=null)
            return meetingDAO.get(meeting.getMeetingID());
        else
            return null;
    }

    public Meeting get(Integer departmentId, Integer meetingTimes){
        if (departmentId!=0&&departmentId!=null&&meetingTimes!=0&&meetingTimes!=null)
            return meetingDAO.get(departmentId, meetingTimes);
        else
            return null;
    }

    public List<Meeting> getMeetingsByDepartmetId(Integer departmentId){
        if (departmentId!=0&&departmentId!=null)
            return meetingDAO.getByDepartmentId(departmentId);
        else
            return null;
    }

    /**
     * 获取最近的一次例会
     * @return 最近一次例会
     */
    public Meeting getLatest(Integer departmentId){
        if (departmentId!=0&&departmentId!=null)
            return meetingDAO.getLatest(departmentId);
        else
            return null;
    }
    /**
     * 获取最早的一次例会
     * @return 最早一次例会
     */
    public Meeting getEarliest(){
        return meetingDAO.getEarliest();
    }

    public Integer countDepartmentMeetingsById(Integer departmentId){
        if (departmentId!=0&&departmentId!=null)
            return meetingDAO.departmentMeetingsTotalCount(departmentId);
        else
            return 0;
    }
}
