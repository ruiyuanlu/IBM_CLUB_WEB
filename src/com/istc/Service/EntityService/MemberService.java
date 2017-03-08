package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import com.istc.Utilities.DAOFactory;
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
        return memberDAO.get(id);
    }
    public void  update(Member member){
        memberDAO.edit(member);
    }

    public Member get(Member member){
        if(member == null)return null;
        return memberDAO.get(member);
    }
    public void remove(String id){
        memberDAO.delete(id);
    }

    public boolean exist(Member member){
        return member == null ? false : get(member) != null;
    }
    public boolean exist(String id){
        return id == null || "".equals(id) ? false : get(id) != null;
    }
}
