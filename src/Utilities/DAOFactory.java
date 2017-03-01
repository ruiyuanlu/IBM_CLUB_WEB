package Utilities;

/**
 * Created by lurui on 2017/2/4 0004.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * DAO工厂类
 * 通过读取DAO.properties获取实现类的类型
 */
public class DAOFactory {

    private static Properties p = new Properties();
    //用于缓存的Map
    private static HashMap<String, Object> cache = new HashMap<String, Object>();

    /***
     * 加载DAO.properties
     */
    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config\\hibernate\\DAO.properties");
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 首先查询缓存中是否存在该类型的DAO
     * 存在则返回该对象
     * 不存在则创建，但此处不加入缓存
     * 后面重载的getInstance方法会将其加入缓存
     * @param daoName
     * @return 一个Object对象
     */
    public static Object getInstance(String daoName){
        Object obj = cache.get(daoName);
        if(obj == null){
            String clazz = p.getProperty(daoName);
            if(clazz !=null && !clazz.equals("")){
                try {
                    obj = Class.forName(clazz).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public synchronized static <T> T getInstance(String daoName, Class daoClass){
        T obj = (T)cache.get(daoName);

        if(obj == null){
            String className = p.getProperty(daoName);
            if (className != null && !className.equals("")){
                try {
                    //加载指定名称的字节码到虚拟机中
                    Class clazz = Class.forName(className);
                    //调用无参构造产生一个对象
                    obj = (T)daoClass.cast(clazz.newInstance());
                    //存入缓冲池
                    cache.put(daoName, obj);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
