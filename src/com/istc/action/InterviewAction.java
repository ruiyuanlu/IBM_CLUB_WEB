package com.istc.action;

import java.util.*;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Service.EntityService.IntervieweeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 面试模块
 */
@Controller("interviewAction")
@Scope(scopeName = "prototype")
@AllowedMethods({"intervieweeGet", "intervieweeCheck"})
@ParentPackage("ajax")
@Result(name="input",location="interview.jsp")
public class InterviewAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 12343251L;

	@Resource(name = "intervieweeService")
    private IntervieweeService intervieweeService;

	private String[] passedIDs;
	private Map<String, Object> session;

	/**
	 * 用于获得用户提交的面试结果信息，并对数据库进行相应的处理
	 */
	@Action(value="intervieweeCheck")
	public String intervieweeCheck() throws Exception{
		try {
		    //将数据库中面试通过的人删除并加入成员列表
			intervieweeService.setIntervieweesToMembers(passedIDs);
			//从数据库中重新获取剩余对象的List
            List<Interviewee> restIntrviewees = intervieweeService.getRestInterviewees();
			session.put("interviewList", restIntrviewees);
			if (restIntrviewees.size() <= 0)
				addFieldError("getIntervieweeError", "面试已结束！");
		} catch (Exception e) {
			addFieldError("getIntervieweeError", "获取面试人员信息失败！");
			return INPUT;
		}
        return INPUT;
    }
	
	/**
	 * 从数据库获取面试人员列表
	 * 最后返回一个能用json解析的对象数组
	 */
	@Action(value="intervieweeGet",results = {@Result(name = INPUT, type = "json", params = {"IgnoreHierarchy","false"})})
	public String intervieweeGet() throws Exception{
		try {
			session.put("interviewList", intervieweeService.getRestInterviewees());
		} catch (Exception e) {
			addFieldError("getIntervieweeError", "获取面试人员信息失败！");
            return INPUT;
        }
		return INPUT;
	}

	public String[] getPassedIDs(){
		return passedIDs;
	}

	public void setPassedIDs(String[] passedIDs){
		this.passedIDs = passedIDs;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
}
