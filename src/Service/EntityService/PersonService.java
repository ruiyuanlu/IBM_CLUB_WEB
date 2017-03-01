package Service.EntityService;

import Entities.Entity.Meeting;
import Entities.Entity.Person;
import Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import Service.EntityDAO.EntityDAOInterfaces.PersonDAO;
import Utilities.DAOFactory;
import Utilities.HibernateUtils;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * 每个实体类对应相应的Service类
 * Service类用于操作相应的DAO层
 * 完成数据库的增删改查
 * Service层是M层中最靠近C层的部分
 */
public class PersonService {
    private PersonDAO personDAO = DAOFactory.getInstance("PersonDAO", PersonDAO.class);
    private MeetingDAO meetingDAO = DAOFactory.getInstance("MeetingDAO", MeetingDAO.class);

    /**
     * 这段代码应该写在
     * 查找所有Person实体
     * @return 所有的Person对象列表
     */
    public List<Person> findAll(){
        List<Person> list = new ArrayList<Person>();
        Transaction tx = null;
        try {
            tx = HibernateUtils.getSession().beginTransaction();
            list = personDAO.findAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return list;
    }

    public Meeting findMeeting(){
        return meetingDAO.getMeetingByDepartmentId(1);
    }

    public void delete(String id){

    }
}
