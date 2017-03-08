package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.Person;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public interface MemberDAO<E extends Member, PK extends Serializable> extends PersonDAO<Member, String> {
    void save(E[] members);
    Member get(String id);
    Member get(Member member);
    Member[] get(String[] ids);
    void delete(String id);

}
