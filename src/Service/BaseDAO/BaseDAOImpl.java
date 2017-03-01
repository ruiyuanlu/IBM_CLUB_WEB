package Service.BaseDAO;

/**
 * Created by lurui on 2017/2/3 0003.
 */

import Utilities.HibernateUtils;
import Utilities.PageModel;

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

    private Class<E> eClass;
    private Class<PK> pkClass;

    public BaseDAOImpl() {
        //通过反射机制获取子类传递来的实体类的类型 E 以及实体类ID的类型 PK
        Type[] types = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.eClass = (Class<E>)types[0];
        this.pkClass = (Class<PK>)types[1];
    }

    @Override
    public void delete(E entity) {
        HibernateUtils.getSession().delete(entity);
    }

    @Override
    public void edit(E entity) {
        HibernateUtils.getSession().merge(entity);
    }

    @Override
    public List<E> findAll() {
        String hql = "select t from " + eClass.getSimpleName() + " t";
        return (List<E>)HibernateUtils.getSession().createQuery(hql).list();
    }

    @Override
    public PageModel<E> findByPage(int pageNum, int pageSize) {
        if(pageNum < 1 || pageSize < 0) return null;
        String hql = "select t from " + eClass.getSimpleName() + " t";
        PageModel<E> pm = new PageModel(pageNum, pageSize);
        pm.setDatas(HibernateUtils.getSession().createQuery(hql).setFirstResult(pageNum).setMaxResults(pageSize).list());
        pm.setAllRecordCount(totalCount());
        return pm;
    }

    @Override
    public E findUnique(String sql) {
        return (E)HibernateUtils.getSession().createQuery(sql).uniqueResult();
    }

    @Override
    public E get(PK id) {
        //只有接口Base继承了Serializable才能使得查询方法更通用
        return (E)HibernateUtils.getSession().get(eClass, id);
    }

    @Override
    public E load(PK id) {
        return (E)HibernateUtils.getSession().load(eClass, id);
    }

    @Override
    public void save(E entity) {
        HibernateUtils.getSession().save(entity);
    }

    @Override
    public int totalCount() {
        String hql = "select count(t) from " + eClass.getSimpleName() + " t";
        Long count = (Long)HibernateUtils.getSession().createQuery(hql).uniqueResult();
        return count == null? -1 : count.intValue();
    }

    @Override
    public void update(String sql) {
        HibernateUtils.getSession().createQuery(sql).executeUpdate();
    }
}
