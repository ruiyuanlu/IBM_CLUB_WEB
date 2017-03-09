package com.istc.action;

import com.istc.validation.ValidationUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.istc.Entities.Entity.HomeWorkIssue;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 部长及以上成员管理作业信息发布
 * 特定部长权限
 */
@ParentPackage("needajax")
@AllowedMethods({"addHomework","modifyHomework","deleteHomework","fetchAllHomework"})
public class HomeworkAction extends ActionSupport{

    private String homeworkRequirement;
    private int id;
    private String starttime;
    private String endtime;
    private int dept;
    private Map<String,Object> jsonresult=new HashMap<String, Object>();
    public static List<HomeWorkIssue> homeworklist=new ArrayList<HomeWorkIssue>();
    public String deleted[];

    static {
        addtemp();
    }

    @Action(
            value="addHomework",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addHomework(){
        try{
            System.out.println(homeworkRequirement+" "+starttime+" "+endtime+" "+dept);
            HomeWorkIssue h=new HomeWorkIssue();
            System.out.println(homeworklist.size());
            int lastid=homeworklist.get(homeworklist.size()-1).getId();
            h.setId(++lastid);
            h.setHomeWorkRequirement(ValidationUtils.getInstance().replaceString(homeworkRequirement));
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Calendar endtimeset = Calendar.getInstance();
            Calendar starttimeset = Calendar.getInstance();
            endtimeset.setTime(sdf.parse(endtime.trim()));
            starttimeset.setTime(sdf.parse(starttime.trim()));
            h.setStartTime(starttimeset);
            h.setEndTime(endtimeset);
            homeworklist.add(h);
        }
        catch (Exception e){
            addFieldError("addHomework","添加作业信息失败！");
        }
        return INPUT;
    }

    public void validateAddHomework(){
        if (homeworkRequirement == null || homeworkRequirement.equals("")){
            addFieldError("homeworkRequirement","请填写作业要求！");
        }
        else {
            if (homeworkRequirement.length()>200){
                addFieldError("homeworkRequirement","请控制作业要求不超过200字符。");
            }
        }
        if(starttime.equals("") || starttime == null){
            addFieldError("starttime","请设置开始日期！");
        }
        if (endtime.equals("") || endtime==null){
            addFieldError("endtime","请设置结束日期！");
        }
        else {
            int daysdelay=7;
            if (ValidationUtils.getInstance().checkIfDatetimeBeforeThanNow(endtime)){
                addFieldError("endtime","结束时间早于当前时间！");
            }
            if (!ValidationUtils.getInstance().checkIfDaysDelayAtLeast(starttime,endtime,daysdelay)){
                addFieldError("endtime","请为部员至少留"+daysdelay+"天完成作业！");
            }
            int submitdelay=2;
            if (!ValidationUtils.getInstance().checkIfDaysDelayThanNowAtLeast(endtime,2)){
                addFieldError("endtime","请至少为部员留"+submitdelay+"天时间上传作业！");
            }
        }
    }

    @Action(
            value="modifyHomework",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String modifyHomework(){
        //已过有效提交日期的作业不能修改
        try {
            for(int i=0;i<homeworklist.size();i++){
                if (homeworklist.get(i).getId() == id){
                    if (!homeworklist.get(i).getEndTime().after(Calendar.getInstance())){
                        addFieldError("modifyHomework","该作业有效期已过，不可修改！");
                    }
                    else {
                        HomeWorkIssue h=homeworklist.get(i);
                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                        Calendar endtimeset = Calendar.getInstance();
                        Calendar starttimeset = Calendar.getInstance();
                        endtimeset.setTime(sdf.parse(endtime.trim()));
                        starttimeset.setTime(sdf.parse(starttime.trim()));
                        h.setStartTime(starttimeset);
                        h.setEndTime(endtimeset);
                        homeworklist.add(i,h);
                    }
                    break;
                }
            }
        }
        catch (Exception e){
            addFieldError("modifyHomework","修改失败！");
        }
        return INPUT;
    }

    public void validateModifyHomework(){
        validateAddHomework();
    }

    @Action(
            value="deleteHomework",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deleteHomework(){
        try {
            for (int i = 0; i < deleted.length; i++) {
                //在数据库中删除对应人员
                for (int j = 0; j < homeworklist.size(); j++) {
                    if(Integer.parseInt(deleted[i]) == homeworklist.get(j).getId()){
                        //这段代码测试系统能否判定当前的作业对象是否可以被修改
/*                        if (!homeworklist.get(i).getEndTime().after(Calendar.getInstance())){
                            break;
                        }*/
                        homeworklist.remove(j);
                        break;
                    }
                }
            }
            jsonresult.put("deleteresult",true);
        }
        catch (Exception e){
            addFieldError("deletePerson","删除人员失败！");
        }
        return INPUT;
    }

    @Action(
            value="fetchAllHomework",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String fetchAllHomework(){
        try {
            jsonresult.put("homeworklist",homeworklist);
        }
        catch (Exception e){
            addFieldError("fetchAllHomework","获取所有作业信息失败！");
        }
        return INPUT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getDeletedid() {
        return deleted;
    }

    public void setDeletedid(String[] deletedid) {
        this.deleted = deletedid;
    }

    public String getHomeworkRequirement() {
        return homeworkRequirement;
    }

    public void setHomeworkRequirement(String homeworkRequirement) {
        this.homeworkRequirement = homeworkRequirement;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public Map<String, Object> getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(Map<String, Object> jsonresult) {
        this.jsonresult = jsonresult;
    }

    private static void addtemp(){
        HomeWorkIssue h=new HomeWorkIssue();
        h.setId(1);
        h.setHomeWorkRequirement("冒泡排序");
        Calendar start=Calendar.getInstance();
        start.set(2017,2,6,14,16);
        h.setStartTime(start);
        Calendar end=Calendar.getInstance();
        end.set(2017,2,8,00,00);
        h.setEndTime(end);
        homeworklist.add(h);
        h=new HomeWorkIssue();
        h.setId(2);
        h.setHomeWorkRequirement("拉斯维加斯算法");
        h.setStartTime(start);
        h.setEndTime(end);
        homeworklist.add(h);
    }
}
