package Service.EntityDAO.EntityDAOImpl;

import Entities.Entity.Meeting;
import Entities.ID.MeetingID;
import Service.BaseDAO.BaseDAOImpl;
import Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import Utilities.HibernateUtils;
import org.hibernate.query.Query;


/**
 * Created by lurui on 2017/2/5 0005.
 */
public class MeetingDAOImpl extends BaseDAOImpl<Meeting, MeetingID> implements MeetingDAO{
    @Override
    public Meeting getMeetingByDepartmentId(Integer deptId) {
        String hql = "from Meeting m where m.meetingID.dept.deptID=:id";
        return (Meeting) HibernateUtils.getSession().createQuery(hql).setParameter("id", deptId).uniqueResult();
    }
}
