package com.istc.Service.BaseDAO;

/**
 * Created by lurui on 2017/2/3 0003.
 */

import com.istc.Utilities.PageModel;

import org.hibernate.query.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @author luruiyuan
 * 基于泛型的通用DAO接口
 */
public interface BaseDAO<E, PK extends Serializable> {

    /**
     * 新增一个实例
     * @param entity 实例
     */
    public void save(E entity);

    public void save(E[] entities);
    /**
     * 根据主键删除一个实例
     * @param id 主键，类型可以是int，也可以是泛型: ID
     */
    public void delete(PK id);

    /**
     * 编辑指定实例的属性
     * @param entity 实例
     */
    public void edit(E entity);

    /**
     * 根据主键查询对应实例
     * @type PK
     * @param id 主键
     * @return 如果查询成功，返回符合条件的实例，否则返回null
     */
    public E get(PK id);

    /**
     * 根据主键查询多个实例
     * @param ids
     * @return 符合条件的实例数组
     * 这个方法画蛇添足，影响效率
     */
//    public List<E> get(PK[] ids);

    /**
     * 根据主键获取对应实例
     * @param id 主键
     * @return 如果查询成功，返回符合条件的实例，否则抛出空指针异常
     */
    public E load(PK id);

    /**
     * 获取所有实例对象的List
     * @return 符合条件的实例的List
     */
    public List<E> findAll();

    /**
     * 查找指定条件的对象列表
     * @param query 指定条件的hql的query
     * @return 符合条件的示例列表
     */
    public List<E> findAll(Query query);

    /**
     * 统计总体实例的数量
     * @return 实例总数量
     */
    public int totalCount();

    /**
     * 统计一定条件下的实例总数量
     * 条件由传入的Query指定
     * @return 符合条件的实例总数量
     */
    public int count(Query query);

    /**
     * 获取分页列表
     * @param pageNum 当前页码, 从1开始
     * @param pageSize 每个页面要显示的记录数目
     * @return PageModel 如果查询成功
     * @return NULL 如果查询失败
     */
    public PageModel<E> findByPage(int pageNum, int pageSize);

    /**
     * 根据指定SQL进行数据更新
     * @param sql 指定的SQL语句
     */
    public void excuteUpdate(String sql);

    /**
     * 根据指定SQL进行单个对象的查询操作
     * @param sql 指定的SQL语句
     */
    public E findUnique(String sql);

    /**
     * 根据指定SQL进行单个对象的查询操作
     * @param query 指定的SQL语句
     * 传入的query应该将参数预先填写
     */
    public E findUnique(Query query);
}
