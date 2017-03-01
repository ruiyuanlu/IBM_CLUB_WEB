import Entities.Entity.Member;
import Entities.Entity.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;


/**
 * Created by lurui on 2016/11/21 0021.
 */
public class DatabaseController<E> {

    private static SessionFactory sessionFactory;

    public DatabaseController() {
    }

    public void setUp() {
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();

    }

    public void close(){
        if(sessionFactory.isOpen())
            sessionFactory.close();
    }

    public Session getCurrentSession(){
        if(sessionFactory.isClosed())setUp();
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }


    public boolean add (Object o){
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(o);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
        return true;
    }


    public boolean delete (Person person){
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete("name",person);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
        return true;
    }

    public int updateMember(String oldName,String newName){
        Session session = getCurrentSession();
//        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update Person p set p.name = :newName " +
                "where p.name = :oldName ")
                .setParameter("newName",newName)
                .setParameter("oldName",oldName);

        int res = executeUpdatebyQuery(session,query);
//        tx.commit();
        return res;
    }

    public List<Member> selectMembersbyName(String name){
        Session session = getCurrentSession();
        Query query = session.createQuery(
                        "from Person p where p.name = :Aname"
        ).setParameter("Aname",name);
       return (List<Member>)getResultListbyQuery(session,query);
    }

    public List<Member> selectAllMembers(){
        Session session = getCurrentSession();
        Query query = session.createQuery("from Person p");
        return (List<Member>)getResultListbyQuery(session,query);
    }

    public Member selectMemberbyID(int ID){
        Session session = getCurrentSession();
        Query query = session.createQuery(
                "from Person p where p.id =: id"
        ).setParameter("id",ID);
        return (Member)getSingleResultbyQuery(session,query);
    }

    /**
     * delete删除Person表中满足condition条件的记录
     * */
    public int delete(String condition){
        int NumofDelete = 0;
        if(condition.length()>10){
            Session session = getCurrentSession();
            Query query = session.createQuery("delete Person p where "+condition);
            NumofDelete = executeUpdatebyQuery(session,query);
        }
        return NumofDelete;
    }

    public boolean updatePerson(Person p){
        if(p == null) return false;
        Session session = getCurrentSession();
        session.saveOrUpdate(p);
        session.close();
        return true;
    }

/**
 * 元操作
 * 增删改查
 * */
    private List<E> getResultListbyQuery(Session session,Query query){
        Transaction txn = session.getTransaction();
        List<E> resList=null;
        try {
            txn.begin();
            resList = query.getResultList();
            txn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            txn.rollback();
        }finally {
//            session.close();
        }
        return resList;
    }

    private E getSingleResultbyQuery(Session session, Query query){
        Transaction txn = session.getTransaction();
        E res = null;
        try {
            txn.begin();
            res = (E)query.getSingleResult();
            txn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            txn.rollback();
        }finally {
//            session.close();
        }
        return res;
    }

    private int executeUpdatebyQuery(Session session, Query query){
        Transaction txn = session.getTransaction();
        int NumofUpdates = -1;
        try {
            txn.begin();
            NumofUpdates = query.executeUpdate();
            txn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            txn.rollback();
        }finally {
            session.close();
        }
        return NumofUpdates;
    }



}
