package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Member;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public interface MemberDAO<E extends Member, PK extends Serializable> extends PersonDAO<Member, String> {
    void save(E[] members);
}
