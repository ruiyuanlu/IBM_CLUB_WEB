//package com.istc.Utilities.JsonUtils;
//
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.usertype.UserType;
//
//import java.io.*;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//
///**
// * Created by lurui on 2017/2/21 0021.
// */
//public class JsonTypeImpl implements UserType {
//    /**
//     * Json 只占据一个属性即数据库表占据一列，且其属性注册为Varchar (注册见 JsonTypeMySQLDialect 类)
//     * @return int[] 每种属性的类型值
//     */
//    @Override
//    public int[] sqlTypes() {
//        return new int[]{Types.VARCHAR};
//    }
//
//    /**
//     * 返回的自定义类型
//     * @return Class<Json> 自定义的 Json 类型
//     */
//    @Override
//    public Class<Json> returnedClass() {
//        return Json.class;
//    }
//
//    /**
//     * Json 必须实现的 Serilizable 接口
//     * @param o 传入的第一个对象
//     * @param o1 传入的第二个对象
//     * @return 比较对象是否相等, 如果都为null或者equals方法方true, 则为true，反之为false
//     * @throws HibernateException
//     */
//    @Override
//    public boolean equals(Object o, Object o1) throws HibernateException {
//        return o != null ? o.equals(o1) : o1 == null;
//    }
//
//    /**
//     * @param o
//     * @return 对象的hashCode方法
//     * @throws HibernateException
//     */
//    @Override
//    public int hashCode(Object o) throws HibernateException {
//        return o.hashCode();
//    }
//
//    @Override
//    public Object nullSafeGet(final ResultSet resultSet, final String[] names, final SharedSessionContractImplementor sharedSessionContractImplementor, final Object owner) throws HibernateException, SQLException {
////        String cellContent = resultSet.getString(names[0]);
////        if (cellContent == null) return null;
//        return null;
//    }
//
//    @Override
//    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
//
//    }
//
//    /**
//     * 深拷贝:
//     * 首先创建一个字节数组输出流 byteArrayOutputStream 和一个对象输出流 objectOutputStream
//     * 对象序列化后会被写入字节输出流 byteArrayOutputStream 中
//     * 然后将字节输出流 byteArrayOutputStream 的内容存入字节输入流 byteArrayInputStream
//     * 通过字节输入流反序列化创建相同的对象, 实现深拷贝
//     * @param originalObject
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Object deepCopy(Object originalObject) throws HibernateException {
//        Object obj = null;//待返回的JsonType对象
//        try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//创建字节输出流
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);//创建对象输出流, 字节流作为存储容器
//            objectOutputStream.writeObject(originalObject);//将对象序列化到对象输出流, 结果存储在字节流对象中
//            objectOutputStream.close();//关闭输出流并flush, 但是序列化结果不会丢失
//            byteArrayOutputStream.close();//关闭字节流并flush, 但是字节流存储的结果不会丢失
//
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());//将字节输入流中的内容传入字节数组输入流
//            obj = new ObjectInputStream(byteArrayInputStream).readObject();//生成JsonType对象
//            byteArrayInputStream.close();//关闭输入流
//        } catch (ClassNotFoundException | IOException e) {
//            throw new HibernateException(e);
//        }
//        return obj;
//    }
//
//    @Override
//    public boolean isMutable() {
//        return true;
//    }
//
//    /**
//     * 装配分解，与下面的装配方法是互逆过程
//     * @param value 待分解对象
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Serializable disassemble(Object value) throws HibernateException {
//        return (Serializable)this.deepCopy(value);
//    }
//
//    /**
//     * 装配对象
//     * @param cached
//     * @param owner
//     * @return 装配后的对象
//     * @throws HibernateException
//     */
//    @Override
//    public Object assemble(Serializable cached, Object owner) throws HibernateException {
//        return this.deepCopy(cached);
//    }
//
//    /**
//     * 深度拷贝
//     * 此接口了解不多
//     * @param original
//     * @param target
//     * @param owner
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Object replace(Object original, Object target, Object owner) throws HibernateException {
//        return this.deepCopy(original);
//    }
//}
