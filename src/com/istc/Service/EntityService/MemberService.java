package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("memberService")
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    @Resource(name = "memberDAO")
    private transient MemberDAO memberDAO;

    public Member get(String id){
        if (id!=null)
        return memberDAO.get(id);
        else
            return null;
    }
    public void  update(Member member){
        if (member!=null&&member.getID()!=null)
        memberDAO.edit(member);
    }
    public void save(Member member){
        if (member!=null&&member.getID()!=null)
            memberDAO.save(member);
    }

    public Member get(Member member){
        if (member!=null&&member.getID()!=null)
        return memberDAO.get(member);
        else return null;

    }
    public void remove(String id){
        if (id!=null)
        memberDAO.delete(id);
    }
    public void remove(String[] ids){
        if (ids!=null&&ids[0]!=null)
            memberDAO.deleteMembers(ids);
    }

    public boolean exist(Member member){
        if (member!=null&&member.getID()!=null)
        return member == null ? false : get(member) != null;
        else return false;
    }
    public boolean exist(String id){
        return id == null || "".equals(id) ? false : get(id) != null;
    }
}
