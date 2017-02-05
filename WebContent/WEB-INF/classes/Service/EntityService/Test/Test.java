package Service.EntityService.Test;


import Entities.Entity.Person;
import Service.EntityService.PersonService;
import Utilities.HibernateUtils;

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

    public static void main(String[] args){
        Test test = new Test();
        test.findAll();

        HibernateUtils.close();
    }
}
