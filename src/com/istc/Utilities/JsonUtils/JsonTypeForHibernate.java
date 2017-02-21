package com.istc.Utilities.JsonUtils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by lurui on 2017/2/21 0021.
 */
public class JsonTypeForHibernate implements UserType {
    /**
     * JsonType 只占据一个属性即数据库表占据一列，且其属性注册为Varchar (注册见 JsonTypeMySQLDialect 类)
     * @return int[] 每种属性的类型值
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    /**
     * 返回的自定义类型
     * @return Class<JsonType> 自定义的 Json 类型
     */
    @Override
    public Class<JsonType> returnedClass() {
        return JsonType.class;
    }

    /**
     * JsonType 必须实现的 Serilizable 接口
     * @param o
     * @param o1
     * @return
     * @throws HibernateException
     */
    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {

    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return null;
    }
}
