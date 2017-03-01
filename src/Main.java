import Entities.Entity.Department;
import Entities.Entity.Member;
import Entities.Entity.Minister;
import Entities.Entity.Person;

import java.util.List;




//import Entities.Entity.Department;
//import Entities.Entity.Member;
//import Entities.Entity.Minister;
//import Entities.Entity.Person;
//
//import java.util.List;
//
///**
// * Created by lurui on 2016/11/18 0018.
// */
//public class Main {
//
//    public static void main(String args[])throws Exception{
//        DatabaseController dbc = new DatabaseController();
//        dbc.setUp();
//
//        Minister p = new Minister();
//        Person stu = new Person();
//        stu.setName("一个学生");
//        stu.setID("2141601033");
//        stu.setName("无尘");
//        p.setName("呵呵大");
//        p.setID("2141601065");
//        p.setAuthority(7);
//        p.setAge(45);
//        dbc.add(p);
//        String newName = "部长p";
//        dbc.updateMember(p.getName(),newName);
//        /**添加member*/
//        dbc.add(stu);
//
//        /**
//         * 调用部长和部门的存储关系测试
//         * */
//        testMinisterAndDept(dbc);
//
////        List<Member> members = dbc.selectMembersbyName(newName);
//        List<Member> members = dbc.selectAllMembers();
//        for(Person m: members){
//            System.out.println(m.getName());
////            System.out.println(m.getAge());
////            System.out.println("authority: "+m.getAuthority());
////            if (!(m instanceof Member)){
////                Minister minister = (Minister) m;
////                System.out.print("这是部长的输出  ");
////                System.out.println("部长所辖部门个数 "+minister.getDepartments().size());
////            }
//        }
//
//
//
//        dbc.close();
//    }
//
//    public static boolean testMinisterAndDept(DatabaseController dbc){
//        //创建2个部长
//        Minister m = new Minister();
//        m.setName("部长1");
//        m.setID("2141601000");
//        m.setGender(true);
//
//        Minister m2 = new Minister();
//        m2.setName("部长2");
//        m2.setID("2141601023");
//        m2.setGender(false);
//
//        //创建部门，并与部长关联
//        Department[] depts = newDepartments(1,5,m);
//        Department[] depts1 = newDepartments(6,10,m2);
////        m.addDepartments(depts);
////        m2.addDepartments(depts1);
//
//        //遍历检查部长的属性
//        print_all_depts(m);
//        print_all_depts(m2);
//
//        //遍历检查部门成员
////        print_all_members(depts);
////        print_all_members(depts1);
//
//        try {
//            //尝试存储部长
////            dbc.add(m);
////            print_all_depts(m2);
//            System.out.println("呵呵：m已存储");
////            dbc.add(m2);
//
////
////            /**
////             * 如果多对多映射，以部门为主时，下面的代码可以存储
////             * 即要从负责管理该关系的类型(department)存储
////             * 否则就不能正确存储
////             */
////            //正确代码
////            Department dept = new Department();
////            dept.setDeptName("测试部门1");
////            Member m1 = new Member();
////            m1.setID("cao");
////            m1.setName("caonima");
//////            dept.addMember(m1);
//////            dept.addMember(m2);
//////            dbc.add(dept);
////            //错误代码
////            m1.addDepartment(dept);
////            m2.addDepartment(dept);
////            dbc.add(m1);
////            dbc.add(m2);
//
//            /**
//             * 以Member为主时应该从Member入手进行存储
//             * */
//            Department dept = new Department();
//            dept.setDeptName("测试部门1");
//            Member m1 = new Member();
//            m1.setID("cao");
//            m1.setName("caonima");
////            m1.addDepartment(dept);
////            m2.addDepartment(dept);
//            dbc.add(m1);
//            dbc.add(m2);
//
////            saveDepts(depts,dbc);
///**
// * 存储了部长后不需要再存储部门了
// * */
//            //尝试存储部门
////            saveDepts(depts, dbc);
////            saveDepts(depts1, dbc);
//
//        } catch (Exception e) {
//
//            System.out.print("存储失败\n\n\n");
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//    /**
//     * 批量添加成员，创建部门
//     * */
//    public static Department[] newDepartments(int start ,int num, Minister minister){
//        if (num <= 0 || start <=0 ) return null;
//        DatabaseController dbc2 = new DatabaseController();
//        Department[] depts = new Department[num];
//        for (int i=0;i<num;i++) {
//            depts[i] = new Department();
//            depts[i].setDeptName("第"+(start+i)+"个部门");
//            depts[i].setMinister(minister);
////            depts[i].setDepartment(start+i);不适用set来初始化变量，可以避免报错，因为注解中声明了部门ID为自动生成
//
//            int mem_num = 3;
//            for(int j=0;j<mem_num;j++){
//                Member m = new Member();
//                m.setID(String.format("部门%d, 成员%d",start+i,j+1));
//                m.addDepartment(depts[i]);
////                depts[i].addMember(m);
////                dbc2.add(m);//插入数据库-不要存储可以避免报错,因为一个Set中的元素不可以和两个Session关联
//            }
//        }
//        return depts;
//    }
//
//    public static void saveDepts(Department[] depts, DatabaseController dbc){
//        for (Department dept: depts)dbc.add(dept);
//    }
//
//    public static void print_all_depts(Minister m){
//        if (m == null || m.getManageDepts() == null){
//            System.out.println(m);
//            return ;
//        }
//        StringBuilder strb = new StringBuilder();
//        System.out.println("输出部长的部门属性\nID: "+m.getID());
//        for (Department dept: m.getManageDepts()){
//            if(dept == null){
//                System.out.println("部门为NULL");
//                continue;
//            }
//            strb.append(String.format("\t%15s\t%3d\n",dept.getDeptName(),dept.getDeptID()));
//        }
//        strb.append("输出部长属性结束");
//        System.out.println(strb);
//    }
//
//
//    public static void print_all_members(Department[] dept){
//        StringBuilder strb = new StringBuilder();
//
//        for (Department d: dept){
//            strb.append("部门名称："+d.getDeptName());
//            strb.append("\t部门开始\t当前成员数目:"+d.getMembers().size()+"\n");
//            for (Member m: d.getMembers()){
//                strb.append("\t\t成员ID:"+m.getID()+"\n");
//            }
//            strb.append("\t部门结束");
//        }
//
//        strb.append("输出成员结束");
//        System.out.println(strb);
//    }
//}
