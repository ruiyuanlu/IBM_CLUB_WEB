package com.istc.action;

import java.text.SimpleDateFormat;
import java.util.*;

import com.istc.Entities.Entity.Meeting;
import com.istc.validation.ValidationUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 部长管理例会信息发布
 * 特定部长权限
 */
@ParentPackage("needajax")
@AllowedMethods({"addMeeting","modifyMeeting","deleteMeeting","fetchAllMeeting"})
public class MeetingAction extends ActionSupport{

	private String meetingContent;
	private String location;
	private String startTime;
	private String deleted[];
	private int dept;
	private String meetingid;
	private static List<Meeting> meetinglist;
	private Map<String,Object> jsonresult=new HashMap<String,Object>();

	static {
	    addtemp();
    }

    @Action(
            value="addMeeting",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addMeeting(){
	    try{
	        //传过来的Datetime格式如下
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Calendar starttime = Calendar.getInstance();
            starttime.setTime(sdf.parse(startTime));
            Meeting m=new Meeting();
            m.setLocation(ValidationUtils.getInstance().replaceString(location));
            m.setMeetingContent(ValidationUtils.getInstance().replaceString(meetingContent));
            m.setStartTime(starttime);
            meetinglist.add(m);
        }
        catch (Exception e){
	        addFieldError("addMeeting","添加会议失败！");
        }
        return INPUT;
    }

    public void validateAddMeeting(){
        if (meetingContent == null || meetingContent.equals("")){
            addFieldError("meetingContent","请填写会议内容！");
        }
        else {
            if (meetingContent.length() > 100){
                addFieldError("meetingContent","请控制会议内容在100字符以内！");
            }
        }
        if (location == null || location.equals("")){
            addFieldError("location","请填写例会地点！");
        }
        else {
            if (location.length() > 30){
                addFieldError("location","请控制地点信息在30字符以内！");
            }
        }
        int daysdelay = 2;
        if (startTime == null || startTime.equals("")){
            addFieldError("startTime","请输入例会时间！");
        }
        else {
            if (!ValidationUtils.getInstance().checkIfDaysDelayThanNowAtLeast(startTime,daysdelay)){
                addFieldError("startTime","请确保提前至少"+daysdelay+"天通知部员！");
            }
        }
    }

    @Action(
            value="deleteMeeting",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deleteMeeting(){
        try {
            for (int i = 0; i < deleted.length; i++) {
                //在数据库中删除对应人员
                for (int j = 0; j < meetinglist.size(); j++) {
                    if(Integer.parseInt(deleted[i]) == meetinglist.get(j).getMeetingID().getTimes() && dept == meetinglist.get(j).getMeetingID().getDept().getDeptID()){
                        meetinglist.remove(j);
                        break;
                    }
                }
            }
            jsonresult.put("deleteresult",true);
        }
        catch (Exception e){
            addFieldError("deleteMeeting","删除人员失败！");
        }
        return INPUT;
    }

    @Action(
            value="modifyMeeting",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String modifyMeeting(){
        //注意：使用这个action需要表单，并且通过js将该例会原本的信息写入表单中以便于用户操作
        //隐藏域为该例会的id和部门id，变量名分别为meetingid和dept
        try{
            for (int i = 0; i < meetinglist.size(); i++) {
                if(Integer.parseInt(meetingid) == meetinglist.get(i).getMeetingID().getTimes() && dept == meetinglist.get(i).getMeetingID().getDept().getDeptID()){
                    Meeting m = meetinglist.get(i);
                    //修改这个对象，通过传到的信息，之后写会
                    meetinglist.set(i,m);
                    break;
                }
            }
        }
        catch (Exception e){
            addFieldError("modifyMeeting","添加会议失败！");
        }
        return INPUT;
    }

    @Action(
            value="fetchAllMeeting",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String fetchAllMeeting(){
        try {
            jsonresult.put("meetinglist",meetinglist);
        }
        catch (Exception e){
            addFieldError("fetchAllMeeting","获取例会列表失败！");
        }
        return INPUT;
    }

    public void validateModifyMeeting(){
        if (meetingContent == null || meetingContent.equals("")){
            addFieldError("meetingContent","请填写会议内容！");
        }
        else {
            if (meetingContent.length() > 100){
                addFieldError("meetingContent","请控制会议内容在100字符以内！");
            }
        }
        if (location == null || location.equals("")){
            addFieldError("location","请填写例会地点！");
        }
        else {
            if (location.length() > 30){
                addFieldError("location","请控制地点信息在30字符以内！");
            }
        }
        //这里和添加的稍有不同
        if (startTime == null || startTime.equals("")){
            addFieldError("startTime","请输入例会时间！");
        }
        else {
            if (ValidationUtils.getInstance().checkIfDatetimeBeforeThanNow(startTime)){
                addFieldError("startTime","修改的会议开始时间早于当前时间！");
            }
        }
    }

    public String getMeetingContent() {
        return meetingContent;
    }

    public void setMeetingContent(String meetingContent) {
        this.meetingContent = meetingContent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String[] getDeleted() {
        return deleted;
    }

    public void setDeleted(String[] deleted) {
        this.deleted = deleted;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(String meetingid) {
        this.meetingid = meetingid;
    }

    private static void addtemp(){
	    Meeting m=new Meeting();
	    Calendar starttime = Calendar.getInstance();
	    starttime.set(2017,2,3,14,5);
	    m.setStartTime(starttime);
	    m.setMeetingContent("算法基础");
	    m.setLocation("科技类社团活动中心");
	    meetinglist.add(m);
    }
}
