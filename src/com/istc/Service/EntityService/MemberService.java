package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.Minister;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MinisterDAO;
import com.istc.Utilities.ClassTypeConverter;
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
    @Resource(name = "ministerDAO")
    private transient MinisterDAO ministerDAO;

    public Member get(String id) {
        if (id != null)
            return memberDAO.get(id);
        else
            return null;
    }

    public void update(Member member) {
        if (member != null && member.getID() != null)
            memberDAO.edit(member);
    }

    public void save(Member member) {
        if (member != null && member.getID() != null)
            memberDAO.save(member);
    }

    public Member get(Member member) {
        if (member != null && member.getID() != null)
            return memberDAO.get(member);
        else return null;

    }

    public void remove(Member member) {
        if (member != null)
            memberDAO.delete(member);
    }

    public void remove(String[] ids) {
        if (ids != null && ids[0] != null)
            memberDAO.deleteMembers(ids);
    }

    public boolean exist(Member member) {
        if (member != null && member.getID() != null)
            return member == null ? false : get(member) != null;
        else return false;
    }

    public boolean exist(String id) {
        return id == null || "".equals(id) ? false : get(id) != null;
    }

    public void setMemberToMinister(String readyID) {
        if (readyID != null) {
            Member readyMember = memberDAO.get(readyID);
            ClassTypeConverter converter = ClassTypeConverter.getInstance();
            Object target = null;
            try {
                target = Minister.class.newInstance();
                converter.convert(readyMember, target);
            } catch (Exception e) {
                e.printStackTrace();
            }
            memberDAO.delete(readyMember);
            ministerDAO.save(target);


        }
    }
}
