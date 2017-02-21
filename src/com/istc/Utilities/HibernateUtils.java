package com.istc.Utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by lurui on 2017/2/3 0003.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateUtils(){
    }

//    交给Spring管理后, 不需要进行hibernate的sessionFactory的管理
//    否则用一下的代码对hibernate进行初始化
//    static {
//        Configuration cfg = new Configuration().configure(new File("src\\com.istc.config\\hibernate.cfg.xml"));
//        sessionFactory = cfg.buildSessionFactory();
//    }

    /**
     * 获取数据库Session对象
     */
    public static Session getSession(){
        //如果当前线程有session则返回该session
        //如果没有则创建，且自动关闭session，必须要代码中显式关闭session
        return sessionFactory.getCurrentSession();
    }

    /**
     * 关闭数据库Session对象
     */
    public static void closeSession(){
        if (session != null && session.isOpen())
            session.close();
    }

    /***
     * 关闭传入的数据库Session对象
     */
    public static void closeSession(Session session){
        if (session != null && session.isOpen())
            session.close();
    }

    /**
     * 关闭整个hibernate的sessionFactory
     */
    public static void close(){
        if(sessionFactory !=null && sessionFactory.isOpen())
            sessionFactory.close();
    }
}
