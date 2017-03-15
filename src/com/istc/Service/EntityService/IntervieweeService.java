package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Entities.Entity.Member;
import com.istc.Entities.PropertyInterface.Authority;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.IntervieweeDAO;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import com.istc.Utilities.ClassTypeConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lurui on 2017/2/25 0025.
 */
@Service("intervieweeService")
@Transactional(rollbackFor = Exception.class)
public class IntervieweeService implements Authority{
    @Resource(name = "intervieweeDAO")
    private transient IntervieweeDAO intervieweeDAO;
    @Resource(name = "memberDAO")
    private transient MemberDAO memberDAO;

    public boolean exist(String id){
        if (id==null)
            return false;
        return intervieweeDAO.exist(id);
    }

    public Interviewee[] getRestInterviewees(){
        List<Interviewee> interviewees = intervieweeDAO.findAll();
        Interviewee[] rest = new Interviewee[interviewees.size()];
        for(int i = 0; i< rest.length; i++) rest[i] = interviewees.get(i);
        return rest;
    }


    public void setIntervieweesToMembers(String[] intervieweeIDs) {
        if (intervieweeIDs[0] != null) {
            //vicky认为需要检查这些intervieweeIDs是否存在，但会影响该方法性能
            List<Interviewee> list = intervieweeDAO.get(intervieweeIDs);

            ClassTypeConverter converter = ClassTypeConverter.getInstance();
            Member[] members = null;
            try {
                members = (Member[]) converter.convert(list, Member.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            intervieweeDAO.delete(intervieweeIDs);
            for(Member m : members)m.setAuthority(memberAuthority);
            memberDAO.save(members);

        }
    }
//即使是申请面试也需要基本id（primarily key）
    public void add(Interviewee interviewee){
        if (interviewee!=null&&interviewee.getID()!=null)
        intervieweeDAO.save(interviewee);
    }
}
