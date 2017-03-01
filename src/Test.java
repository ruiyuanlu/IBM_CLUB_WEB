import Entities.Entity.Person;
import Service.EntityService.PersonService;
import Utilities.HibernateUtils;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created by lurui on 2017/2/5 0005.
 */

public class Test {

    public void createTable(){

    }

    public void findAll(){
        PersonService ps = new PersonService();
        List<Person> list = ps.findAll();
        for (Person p: list)System.out.println(p);
    }

    public void testFind(){
        PersonService ps = new PersonService();
        Transaction tx = HibernateUtils.getSession().beginTransaction();
        System.out.println(ps.findMeeting());
        tx.commit();
    }

    public static void main(String[] args){
        Test test = new Test();
        test.testFind();

        HibernateUtils.close();
    }
}
