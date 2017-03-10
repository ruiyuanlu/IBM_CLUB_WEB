package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Member;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lurui on 2017/2/4 0004.
 */
@Repository("memberDAO")
public class MemberDAOImpl<E extends Member, PK extends Serializable> extends BaseDAOImpl<Member, String> implements MemberDAO<E, PK>{
    @Override
    public void save(E[] members) {
        this.save(members);
    }

    @Override
    public Member get(String id){
        if(id == null)return null;
        StringBuilder stringBuilder=new StringBuilder("from Member m where m.id =:id");
        return (Member)this.getSession().createQuery(stringBuilder.toString()).setParameter("id", id).uniqueResult();
    }

    @Override
    public Member get(Member member){
        Member member1 = this.get(member.getID());
        return member1 == null || !member1.getPassword().equals(member.getPassword())? null : member1;
    }

    @Override
    public void deleteMembers(String[] ids) {
        StringBuilder strb = new StringBuilder("delete from Member m where m.ID = ").append(ids[0]);
        for(int i = 1; i < ids.length; i++)
            strb.append(" or m.ID = ").append(ids[i]);
        this.getSession().createQuery(strb.toString()).executeUpdate();
    }

    @Override
    public Member[] get(String[] ids){
        StringBuilder strb = new StringBuilder("from Member m where m.ID = ").append(ids[0]);
        for(int i = 1; i < ids.length; i++)
            strb.append(" or m.ID = ").append(ids[i]);
        List<Member> list = (List<Member>)this.getSession().createQuery(strb.toString()).list();
        if(list == null || list.size() <= 0)return null;
        Member[] members = new Member[list.size()];
        for(int i = 0;i < members.length; i++)
            members[i] = list.get(i);
        return members;
    }

    @Override
    public void delete(Member member){
        getSession().createQuery("delete from Member m where m.ID = " + member.getID()).executeUpdate();
    }
}
