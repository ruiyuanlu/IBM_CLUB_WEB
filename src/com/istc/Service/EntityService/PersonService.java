package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Person;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.PersonDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lurui on 2017/2/4 0004.
 */

/**
 * 每个实体类对应相应的Service类
 * Service类用于操作相应的DAO层
 * 完成数据库的增删改查
 * Service层是M层中最靠近C层的部分
 */

/**
 * 尝试用spring进行管理
 */

@Service("personService")
@Transactional(rollbackFor = Exception.class)
public class PersonService {
    /**
     * 由spring管理DAO
     */
    @Resource
    private transient PersonDAO personDAO;

    /**
     * 这段代码应该写在
     * 查找所有Person实体
     * @return 所有的Person对象列表
     */
    public List<Person> findAll(){
        return personDAO.findAll();
    }

    public Person get(Person person){
        return personDAO.get(person);
    }

    public Person get(String id){
        return  personDAO.get(id);
    }
    public void  update(Person person){
        personDAO.edit(person);
    }

    public Boolean exist(Person person){
        return personDAO.exist(person);
    }

    public Boolean exist(String id){
        return personDAO.exist(id);
    }

}
