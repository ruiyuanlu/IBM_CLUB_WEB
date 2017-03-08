package com.istc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import Entities.Department;

import java.util.*;

/**
 * Created by Morn Wu on 2017/3/4.
 * 用于部门的增删改查
 * 除修改简介外，全部是主席级权限
 */

@ParentPackage("needajax")
@AllowedMethods({"addDept","modifyDept","deleteDept","fetchDept"})
public class DepartmentAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private int deptID;
    private String introduction;
    private String deptName;
    private Calendar establishTime;
    private boolean usecurrenttime; //用户选择是否使用当前时间作为部门创建时间
    public static List<Department> deptlist=new ArrayList<Department>();
    private Map<String,Object> jsonresult=new HashMap<String,Object>();
    private String deptdeleted;
    private String deptMinisterID;

    @Action(
            value="addDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addDept(){
        try {
            Department d=new Department();
            d.setDeptID(deptID);
            d.setDeptName(deptName);
            d.setEstablishTime(establishTime);
            d.setIntroduction(introduction);
            System.out.println(deptID+" "+introduction+" "+establishTime+" "+deptMinisterID);
            deptlist.add(d);
        }
        catch (Exception e){
            addFieldError("addDept","部门添加失败！");
        }
        return INPUT;
    }

    public void validateAddDept(){

    }

    @Action(
            value="modifyDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String modifyDept(){
        System.out.println("修改后的部门简介："+introduction);
        return INPUT;
    }

    public void validateModifyDept(){

    }

    @Action(
            value="deleteDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deleteDept(){
        return INPUT;
    }

    @Action(
            value="fetchDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String fetchDept(){
        try {
            jsonresult.put("dept",deptlist);
        }
        catch (Exception e){
            addFieldError("fetchDept","获取部门信息失败！");
        }
        return INPUT;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
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

    public boolean isUsecurrenttime() {
        return usecurrenttime;
    }

    public void setUsecurrenttime(boolean usecurrenttime) {
        this.usecurrenttime = usecurrenttime;
    }

    public String getDeptdeleted() {
        return deptdeleted;
    }

    public void setDeptdeleted(String deptdeleted) {
        this.deptdeleted = deptdeleted;
    }
}
