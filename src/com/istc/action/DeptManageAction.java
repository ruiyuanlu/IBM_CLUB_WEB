package com.istc.action;

import com.istc.Entities.Entity.Department;
import com.istc.Entities.Entity.Minister;
import com.istc.Service.EntityService.DepartmentService;
import com.istc.Utilities.TokenUtils;
import com.istc.Validation.RegisterCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by vicky on 2017/3/15.
 */
@Controller("deptManageAction")
@Scope("prototype")
@ParentPackage("ajax")
@AllowedMethods({"addDepartment",""})
public class DeptManageAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    //会话
    private Map<String, Object> session;
    private Map<String,Object> jsonresult=new HashMap<String,Object>();

    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    //表单传入值
    private String id;
    private int dept;
    private int deleteDept;

    private String introduction;
    private String deptName;
    private String establishTime;

    private String token;
    private String newToken;

    //工具
    private TokenUtils tokenUtil;
    private RegisterCheck registerUtil;
    //定义全局常量
    private final String loginKey = "member";
    private final String tokenKey = "token";
    private final String prePageKey = "prePage";

    public DeptManageAction() {
        tokenUtil = TokenUtils.getInstance();
        registerUtil = RegisterCheck.getInstance();
    }

    @Action(
            value="addDepartment",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addDepartment(){
        newToken = tokenUtil.tokenCheck(this, session, token);
        Department department=new Department();
        department.setDeptID(dept);
        department.setDeptName(deptName);
        Calendar calendar=Calendar.getInstance();
        department.setEstablishTime(calendar);
        department.setIntroduction(introduction);
        departmentService.add(department);
        return INPUT;
    }
    public void validateAddDepartment(){
        if (dept<=0){
            addFieldError("dept", "部门ID不合法！");
        }
        else if (departmentService.exist(dept)){
            addFieldError("dept", "此部门ID已被占用！");
        }
        if (!registerUtil.isValid(RegisterCheck.Type.NAME,deptName)){
            addFieldError("deptName", "部门名称不合法！");
        }
        if (introduction==null){
            introduction=new String("empty");
        }
        //部门介绍这里是否需要检验非法输入
        else if (introduction.length() > 500){
            addFieldError("introduction","部门介绍需短于500字");
        }
    }
    @Action(
            value="deleteDepartment",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deleteDepartment(){
        newToken = tokenUtil.tokenCheck(this, session, token);
        departmentService.remove(deleteDept);
        return INPUT;
    }
    public void validateDeleteDepartment(){
        System.out.println(deleteDept);
        if(!departmentService.exist(deleteDept)){
            addFieldError("deleteDept","该部门不存在");
        }
    }
    //getter and setter



    public void setJsonresult(Map<String, Object> jsonresult) {
        this.jsonresult = jsonresult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getDeleteDept() {
        return deleteDept;
    }

    public void setDeleteDept(int deleteDept) {
        this.deleteDept = deleteDept;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }
}
