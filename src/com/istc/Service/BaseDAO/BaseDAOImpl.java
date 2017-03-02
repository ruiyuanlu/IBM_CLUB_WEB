package com.istc.Service.BaseDAO;

/**
 * Created by lurui on 2017/2/3 0003.
 */

import com.istc.Utilities.HibernateUtils;
import com.istc.Utilities.PageModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.lang.reflect.*;

/**
 * @author luruiyuan
 * 通用DAO接口的实现类
 * eClass 对应实体类的类型
 * pkClass 对应实体类主键的类型
 */
public class BaseDAOImpl<E, PK extends Serializable> implements BaseDAO<E, PK> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    private Class<E> eClass;
    private Class<PK> pkClass;

    public BaseDAOImpl() {
        //通过反射机制获取子类传递来的实体类的类型 E 以及实体类ID的类型 PK
        Type[] types = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.eClass = (Class<E>)types[0];
        this.pkClass = (Class<PK>)types[1];
    }

    /**
     * 使用session工厂获取session
     * @return Session
     */
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void delete(PK id) {
        getSession().delete(id);
    }

    @Override
    public void edit(E entity) {
        getSession().merge(entity);
    }

    @Override
    public List<E> findAll() {
        String hql = "select t from " + eClass.getSimpleName() + " t";
        return (List<E>)getSession().createQuery(hql).list();
    }

    @Override
    public List<E> findAll(Query query) {
        return (List<E>)query.list();
    }

    @Override
    public PageModel<E> findByPage(int pageNum, int pageSize) {
        if(pageNum < 1 || pageSize < 0) return null;
        String hql = "select t from " + eClass.getSimpleName() + " t";
        PageModel<E> pm = new PageModel(pageNum, pageSize);
        pm.setDatas(getSession().createQuery(hql).setFirstResult(pageNum).setMaxResults(pageSize).list());
        pm.setAllRecordCount(totalCount());
        return pm;
    }

    @Override
    public E findUnique(String sql) {
        return (E)getSession().createQuery(sql).uniqueResult();
    }

    @Override
    public E findUnique(Query query) {
        return (E)query.uniqueResult();
    }

    @Override
    public E get(PK id) {
        //只有接口Base继承了Serializable才能使得查询方法更通用
        return (E)getSession().get(eClass, id);
    }

    @Override
    public E load(PK id) {
        return (E)getSession().load(eClass, id);
    }

    @Override
    public void save(E entity) {
        getSession().save(entity);
    }

    @Override
    public void save(E[] entities) {
        Session session = getSession();
        for(int i = 0; i < entities.length; i++){
            session.save(entities[i]);
            if( i % 20 == 0){
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public int totalCount() {
        String hql = "select count(t) from " + eClass.getSimpleName() + " t";
        Long count = (Long)getSession().createQuery(hql).uniqueResult();
        return count == null? -1 : count.intValue();
    }

    @Override
    public int count(Query query) {
        Long count = (Long)query.uniqueResult();
        return count == null ? -1 : count.intValue();
    }

    @Override
    public void excuteUpdate(String sql) {
        getSession().createQuery(sql).executeUpdate();
    }

}
