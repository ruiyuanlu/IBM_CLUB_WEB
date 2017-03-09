package com.istc.action;

import com.istc.validation.ValidationUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.istc.Entities.Entity.Department;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Morn Wu on 2017/3/4.
 * 用于部门的增删改查
 * 除修改简介外，全部是主席级权限
 */

@ParentPackage("needajax")
@AllowedMethods({"addDept","modifyDept","deleteDept","fetchAllDept"})
public class DepartmentAction extends ActionSupport{

    private int dept;
    private String introduction;
    private String deptName;
    private String establishTime;
    public static List<Department> deptlist=new ArrayList<Department>();
    private Map<String,Object> jsonresult=new HashMap<String,Object>();
    private String deptdeleted;

    static {
        addtemp();
    }

    @Action(
            value="addDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addDept(){
        try {
            Department d=new Department();
            d.setDeptID(dept);
            d.setDeptName(ValidationUtils.getInstance().replaceString(deptName));
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Calendar set = Calendar.getInstance();
            set.setTime(sdf.parse(establishTime));
            d.setEstablishTime(set);
            d.setIntroduction(ValidationUtils.getInstance().replaceString(introduction));
            System.out.println(dept +" "+introduction+" "+establishTime);
            deptlist.add(d);
        }
        catch (Exception e){
            addFieldError("addDept","部门添加失败！");
        }
        return INPUT;
    }

    public void validateAddDept(){
        if (dept < 1 || dept > 100){
            addFieldError("dept","请选择1-100以内的任意整数");
        }
        else {
            //以下if判断条件需要在数据库中搜索当前id是否已经存在
/*        if (){
            addFieldError("dept","当前ID已被使用，请选择其它ID！");
        }*/
        }
        if (deptName == null || deptName.equals("")){
            addFieldError("deptName","请键入部门名称！");
        }
        else {
            if (deptName.length() < 3 || deptName.length() > 10){
                addFieldError("deptName","控制长度在3-10字符之间");
            }
        }
        if (introduction == null || introduction.equals("")){
            addFieldError("introduction","请键入该部门简介！");
        }
        else {
            //之后可以任意控制简介的最大长度
            if (introduction.length() > 100){
                addFieldError("introduction","请控制简介在100字符以内");
            }
        }
        if (establishTime == null || establishTime.equals("")){
            addFieldError("establishTime","请填写部门创建时间！");
        }
        else {
            if(ValidationUtils.getInstance().checkIfDateAfterThanNow(establishTime)){
                addFieldError("establishTime","您输入的时间不正确，请重新检查后输入！");
            }
        }

    }

    @Action(
            value="modifyDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String modifyDept(){
        //通过deptID搜索
        try {
            //如果用户提交的新简介为空那么认为不需要修改
            if(!(introduction == null || introduction.equals(""))){
                System.out.println("修改后的部门简介："+introduction);
            }
        }
        catch (Exception e){
            addFieldError("modifyDept","修改失败！");
        }
        return INPUT;
    }

    @Action(
            value="deleteDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deleteDept(){
        //通过deptID搜索
        try {
            for (int i=0;i<deptlist.size();i++){
                if (deptlist.get(i).getDeptID()==Integer.parseInt(deptdeleted.trim())){
                    deptlist.remove(i);
                    jsonresult.put("deleteresult",true);
                    break;
                }
            }
        }
        catch (Exception e){
            addFieldError("deleteDept","删除失败！");
        }
        return INPUT;
    }

    @Action(
            value="fetchAllDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String fetchAllDept(){
        //部长级则返回其所管辖的所有部门的信息，主席级则返回所有部门信息，拒绝部员级用户执行
        try {
            jsonresult.put("deptlist",deptlist);
        }
        catch (Exception e){
            addFieldError("fetchDept","获取部门信息失败！");
        }
        return INPUT;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Map<String, Object> getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(Map<String, Object> jsonresult) {
        this.jsonresult = jsonresult;
    }

    public String getDeptdeleted() {
        return deptdeleted;
    }

    public void setDeptdeleted(String deptdeleted) {
        this.deptdeleted = deptdeleted;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    private static void addtemp(){
        Department d=new Department();
        d.setEstablishTime(Calendar.getInstance());
        d.setDeptName("Java部");
        d.setIntroduction("这个是Java部");
        d.setDeptID(1);
        deptlist.add(d);
        d=new Department();
        d.setEstablishTime(Calendar.getInstance());
        d.setDeptName("python部");
        d.setIntroduction("这个是python部");
        d.setDeptID(2);
        deptlist.add(d);
    }
}
