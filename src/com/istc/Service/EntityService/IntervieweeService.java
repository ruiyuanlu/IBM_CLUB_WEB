package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Entities.Entity.Member;
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
public class IntervieweeService {
    @Resource(name = "intervieweeDAO")
    private transient IntervieweeDAO intervieweeDAO;
    @Resource(name = "memberDAO")
    private transient MemberDAO memberDAO;

    public boolean exist(String id){
        if (id==null)
            return false;
        return intervieweeDAO.exist(id);
    }

    public List<Interviewee> getRestInterviewees(){
        return intervieweeDAO.findAll();
    }

    public void setIntervieweesToMembers(String[] intervieweeIDs) {
        if (intervieweeIDs[0] != null) {
            List<Interviewee> list = intervieweeDAO.get(intervieweeIDs);
            intervieweeDAO.delete(intervieweeIDs);
            ClassTypeConverter converter = ClassTypeConverter.getInstance();
            Member[] members = null;
            try {
                members = (Member[]) converter.convert(list, Member.class);
            } catch (Exception e) {
                System.out.println("IntervieweeService.java 文件中的成员类型转换失败\n" +
                        "设定converter设定阈值时访问异常");
                e.printStackTrace();
                return;
            }
            memberDAO.save(members);
        }
    }
//即使是申请面试也需要基本id（primarily key）
    public void add(Interviewee interviewee){
        if (interviewee!=null&&interviewee.getID()!=null)
        intervieweeDAO.save(interviewee);
    }
}
