package com.istc.action;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

import com.istc.bean.Meeting;
import com.istc.validation.InjectionCheck;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 部长及以上成员管理例会信息发布
 */

public class MeetingAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	private String summary;
    private String location;
    private Integer times;
    private Integer deptID;
    private LocalDateTime meetingDateTime;
    private LocalDateTime currentTime;
    private Meeting curMeeting=new Meeting();
	private Meeting lastMeeting;
	private Map<String, Object> session;
	
	public MeetingAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	/**
	 * 添加会议信息
	 */
	public String add(){
		try {
			//获取上一次例会信息
			times = lastMeeting.getTimes() + 1;
		} catch (NullPointerException e) {
			// TODO: handle exception
			this.times = 1;
		}
		summary="请于"+meetingDateTime.getMonthValue()+"月"+meetingDateTime.getDayOfMonth()+"日("+meetingDateTime.getDayOfWeek()+")"+meetingDateTime.getHour()+":"+meetingDateTime.getMinute()+"准时到会，本次例会主要内容是："+new InjectionCheck(summary).replaceString();
		location=new InjectionCheck(location).replaceString();
		curMeeting.setDeptID(deptID);
		curMeeting.setSummary(summary);
		curMeeting.setTimes(times);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("addmeetingerror", "例会创建失败！");
			return "adderror";
		}
		return "addsuccess";
	}
	
	public void validateAdd(){
		currentTime=LocalDateTime.now();
		if (!meetingDateTime.truncatedTo(ChronoUnit.SECONDS).isAfter(currentTime.truncatedTo(ChronoUnit.SECONDS))) {
			addFieldError("dateerror", "您设定的时间已过，请重新设定！");
		}
	}
	/**
	 * 删除会议信息
	 */
	public String delete(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("deletemeetingerror", "例会删除失败！");
			return "deleteerror";
		}
		return "deletesuccess";
	}
	
	/**
	 * 通修改会议信息
	 */
	public String modify(){
		try {
			if (summary!=null && meetingDateTime!=null) {
				summary="请于"+meetingDateTime.getMonthValue()+"月"+meetingDateTime.getDayOfMonth()+"日("+meetingDateTime.getDayOfWeek()+")"+meetingDateTime.getHour()+":"+meetingDateTime.getMinute()+"准时到会，本次例会主要内容是："+new InjectionCheck(summary).replaceString();
				curMeeting.setSummary(summary);
			}
			else {
				addFieldError("modifymeetingerror", "例会修改失败！");
				return "modifyerror";
			}
			if (location!=null) {
				location=new InjectionCheck(location).replaceString();
				curMeeting.setSummary(location);
			}
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("modifymeetingerror", "例会修改失败！");
			return "modifyerror";
		}
		return "modifysuccess";
	}
	
	public void validateModify(){
		//从数据库中取数据
//		curMeeting=
		currentTime=LocalDateTime.now();
		if (!meetingDateTime.truncatedTo(ChronoUnit.SECONDS).isAfter(currentTime.truncatedTo(ChronoUnit.SECONDS))) {
			addFieldError("dateerror", "您设定的时间已过，请重新设定！");
		}
	}
	/**
	 * 获得之前的会议清单
	 */
	public String get(){
		try {
			ArrayList<Meeting> meetinglist = null;
			session.put("meetinglist", meetinglist);
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("getmeetingerror", "获取例会记录失败！");
			return "geterror";
		}
		return "getsuccess";
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getDeptID() {
		return deptID;
	}

	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	public LocalDateTime getMeetingDateTime() {
		return meetingDateTime;
	}

	public void setMeetingDateTime(LocalDateTime meetingDateTime) {
		this.meetingDateTime = meetingDateTime;
	}

}
