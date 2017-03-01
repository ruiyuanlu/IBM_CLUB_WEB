import Entities.Entity.*;
import Entities.ID.RegisterID;
import Service.EntityDAO.EntityDAOImpl.DepartmentDAOImpl;
import Service.EntityDAO.EntityDAOImpl.InterviewDAOImpl;
import Service.EntityDAO.EntityDAOImpl.MemberDAOImpl;
import Service.EntityDAO.EntityDAOImpl.MinisterDAOImpl;
import Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import Service.EntityDAO.EntityDAOInterfaces.InterviewDAO;
import Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import Utilities.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.*;

/**
 * Created by vicky on 2017/2/28.
 */
public class Maintest {
//    static InterviewDAO interviewDAO = new InterviewDAOImpl();
//    static MemberDAO memberDAO = new MemberDAOImpl();
//    static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
//    static MinisterDAO ministerDAO = new MinisterDAOImpl();

    public static void main(String[] args) {

    Person person =new Person();
    person.setID("112");

    Register register = new Register(new Department(1),1);
        Member bob =new Member();
        bob.setID("2141601111");
        bob.setName("fireman");
//        Set<Member> set = new HashSet<Member>();
//        set.add(bob);
//    register.setMembers(set);
//
        Minister minister =new Minister();
        minister.setID("2141601111");
        minister.setName("carman");
        minister.setGender(false);
        Set<Minister> ministers= new  HashSet<Minister>();
        ministers.add(minister);

        Set<Register> registers= new  HashSet<Register>();
            registers.add(register);
        Department indepart =new Department();

        Department indepart1 =new Department();

//        indepart.setDeptID(1);
//            indepart.setDeptName("tatabu");
//            indepart.setEstablishTime(Calendar.getInstance());
//            indepart.setMinister(minister);
            indepart1.setDeptID(1);
            indepart1.setDeptName("futabu");
            indepart1.setMinister(ministers);
//            indepart1.setEstablishTime(Calendar.getInstance());
//            indepart1.setMinister(ministers);
//        Register register1= new Register(indepart,1);
//        Register register2= new Register(indepart1,2);
//        registers.add(register);

        bob.setRegisterRecords(registers);
//        registers.add(register2);
        Session session= HibernateUtils.getSession();
        try {
            Transaction tx=session.beginTransaction();
//            Department indepart =new Department();
//            Department indepart1 =new Department();

//            indepart.setDeptID(110);
//            indepart.setDeptName("tatabu");
//            indepart.setEstablishTime(Calendar.getInstance());
//            indepart.setMinister(minister);
//            indepart1.setDeptID(111);
//            indepart1.setDeptName("futabu");
//            indepart1.setEstablishTime(Calendar.getInstance());
//            indepart1.setMinister(minister);
//            session.update(bob);
//              indepart=session.get(Department.class,1);
//              indepart.getMinisters().add(minister);
//              session.saveOrUpdate(indepart);
//              indepart.getMinisters().add(minister);
//              session.saveOrUpdate(indepart);
//            session.delete(register);

//            session.delete(minister);


            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        HibernateUtils.closeSession(session);
        HibernateUtils.close();



    }
}


