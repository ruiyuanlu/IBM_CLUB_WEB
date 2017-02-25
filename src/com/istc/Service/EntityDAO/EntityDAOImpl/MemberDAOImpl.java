package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
@Repository("memberDAO")
public class MemberDAOImpl<E extends Member, PK extends Serializable> extends PersonDAOImpl<Member, String> implements MemberDAO<E, PK>{
    @Override
    public void save(E[] members) {
        this.save(members);
    }
}
