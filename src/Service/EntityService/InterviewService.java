package Service.EntityService;

import Entities.Entity.*;
import Service.EntityDAO.EntityDAOImpl.DepartmentDAOImpl;
import Service.EntityDAO.EntityDAOImpl.InterviewDAOImpl;
import Service.EntityDAO.EntityDAOImpl.MemberDAOImpl;
import Service.EntityDAO.EntityDAOImpl.MinisterDAOImpl;
import Service.EntityDAO.EntityDAOInterfaces.DepartmentDAO;
import Service.EntityDAO.EntityDAOInterfaces.InterviewDAO;
import Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import Utilities.HibernateUtils;
import org.hibernate.Transaction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Created by lurui on 2017/2/25 0025.
 */
public class InterviewService {

    static InterviewDAO interviewDAO = new InterviewDAOImpl();
    static MemberDAO memberDAO = new MemberDAOImpl();
    static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    static MinisterDAO ministerDAO = new MinisterDAOImpl();

    public static void main(String[] args){

//        Scanner in = new Scanner(System.in);


//        进行数据库操作
        //创建一个成员
        Member member1 = new Member();
        member1.setID("1");
        member1.setAge(12);
        member1.setDescription("我是一个面试者");
//        member1.setPassed(true);
        member1.setName("岳怡");
        member1.setPhoneNumber("电话号码");
        member1.setQQ(null);//留下一个null 测试
        //创建2个部门
        Department dept = new Department(1);
        dept.setDeptName("一个部门");
        Department dept1 = new Department(2);
        dept1.setDeptName("2个部门");
        //作为一对多关系, 应该由多方管理关系表
        //所以我们首先应该测试在一方（部长）添加部门，是否影响关系表
        //其次测试在多方（部门）添加部长，是否影响关系表
        //然后测试在一方（部门）删除部门，如何影响关系表
        //然后测试在多方（部长）删除部长，如何影响关系表

        //创建一个部长
        Minister minister = new Minister();
        minister.setID("2");
        minister.setName("阿鲁");
        Set<Department> depts = new HashSet<>();
        depts.add(dept);
        depts.add(dept1);
        minister.setManageDepts(depts);

        Transaction tx = HibernateUtils.getSession().beginTransaction();

        //我们先测试用一方管理多方
//        ministerDAO.save(minister);
        /**
         * Hibernate:
         *
         select
         department_.deptID,
         department_.deptName as deptName2_0_,
         department_.deptVersion as deptVers3_0_,
         department_.establishTime as establis4_0_,
         department_.introduction as introduc5_0_,
         department_.minister_id as minister6_0_
         from
         Department department_
         where
         department_.deptID=?

         Hibernate:
         select
         department_.deptID,
         department_.deptName as deptName2_0_,
         department_.deptVersion as deptVers3_0_,
         department_.establishTime as establis4_0_,
         department_.introduction as introduc5_0_,
         department_.minister_id as minister6_0_
         from
         Department department_
         where
         department_.deptID=?

         */
//        in.next();
        //观察结果
        //
        //创建两个例会
//        Meeting meeting = new Meeting();


        member1.addDepartment(new Department(1));//将人员添加到部门
        memberDAO.save(member1);//存储人员
//        memberDAO.edit(member1);//修改人员
        tx.commit();
        /**
         * 提交时, hibernate 才会在 member 会插入一条member记录
         * 将部门添加到Minister的Set时，部门本身的记录不存在，但是可以创建部门的记录信息
         * hibernate 首先进行了查询，然后创建了该记录
         * 但是由于一对多关系中，多方管理关系，所以部长方作为一方，并没有在实际的数据库外键列进行操作
         *
         * 结论：非管理方插入一条set记录，可以导致另一方创建新记录，但是不能操作关系表或者外键列
         *
         *
         * Hibernate:
         insert
         into
         Person
         (qq, age, description, gender, name, peopleVersion, phone, ID)
         values
         (?, ?, ?, ?, ?, ?, ?, ?)
         Hibernate:
         insert
         into
         Member
         (authority, ID)
         values
         (?, ?)
         Hibernate:
         insert
         into
         Minister
         (ID)
         values
         (?)
         Hibernate:
         insert
         into
         Department
         (deptName, deptVersion, establishTime, introduction, minister_id, deptID)
         values
         (?, ?, ?, ?, ?, ?)
         Hibernate:
         insert
         into
         Department
         (deptName, deptVersion, establishTime, introduction, minister_id, deptID)
         values
         (?, ?, ?, ?, ?, ?)
         Hibernate:
         insert
         into
         Person
         (qq, age, description, gender, name, peopleVersion, phone, ID)
         values
         (?, ?, ?, ?, ?, ?, ?, ?)
         Hibernate:
         insert
         into
         Member
         (authority, ID)
         values
         (?, ?)
         Hibernate:
         insert
         into
         Minister_Department
         (Minister_ID, manageDepts_deptID)
         values
         (?, ?)
         Hibernate:
         insert
         into
         Minister_Department
         (Minister_ID, manageDepts_deptID)
         values
         (?, ?)

         */
        //现在我们用多方插入一方，根据之前的测试，显然应该是由部门表管理关系
        //首先将部门的管理者加入部门的Minister
//        dept.setMinister(minister);
//        dept1.setMinister(minister);
        //因为已经有了对象，所以我们用edit方法的merge进行合并
//        departmentDAO.edit(dept);
//        System.out.println("hehe");
//        in.next();
//        departmentDAO.edit(dept1);
//
//        in.next();
//        System.out.println("zoule");
//
//        tx.commit();//提交事务，观察结果
//        System.out.println("wancheng");


        //hibernate 首先查询了记录是否存在，然后修改了存在的记录
        //然后添加了关系表或者说外键的记录


        //现在我们要删除部门的一个成员, 我们希望不删除部门，直接使用hibernate自己的delete就可以, 但是这里的delete是针对对象的，而不是针对主键的
//        Transaction tx1 = HibernateUtils.getSession().beginTransaction();
//        memberDAO.delete(member1);
//        System.out.println("输入");
//        in.next();
//        System.out.println("xixi");
//        tx.commit();
        //删除多方 直接操作成功，解除了关联关系，同时又不影响部门本身

//        //现在我们要删除一个部门，我们希望部门的成员信息不被删除，但是解除和部门的关系
//        System.out.println("删除开始");
        Transaction tx1 = HibernateUtils.getSession().beginTransaction();
//        in.next();
        departmentDAO.delete(dept);
        tx1.commit();
        //结果 在CascadeType == cascade.ALL 的情况下, 删除失败，会报告外键限制导致删除失败的异常
        //结果 在CascadeType == merge 下，情况同上
        //结果 在CascadeType == refresh 下




        //删除一个部门, 我们不希望删除成员
//        Transaction transaction = HibernateUtils.getSession().beginTransaction();
//        departmentDAO.delete(new Department(1));
//        transaction.commit();




    }


}
