package com.istc.action;

import com.istc.Entities.Entity.Department;
import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.Person;
import com.istc.Entities.Entity.Register;
import com.istc.Entities.ID.RegisterID;
import com.istc.Service.EntityService.MemberService;
import com.istc.Service.EntityService.RegisterService;
import com.istc.Utilities.ClassTypeConverter;
import com.istc.Utilities.TokenUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lurui on 2017/3/6 0006.
 */
@Controller("signAction")
@Scope("prototype")
@ParentPackage("ajax")
@AllowedMethods({"refreshQRCode"})
public class SignAction extends ActionSupport implements SessionAware {

    private static final String loginKey = "member";

    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "registerService")
    private RegisterService registerService;

    private Map<String, Object> session;
    private String token;

    //前端传入的信息
    private int deptID;
    private int times;

    //工具
    private TokenUtils tokenUtils;
    private ClassTypeConverter typeConverter;

    public SignAction(){
        tokenUtils = TokenUtils.getInstance();
        typeConverter = ClassTypeConverter.getInstance();
    }

    @Action(value = "refreshQRCode", results = {@Result(name = INPUT,type = "json", params = {"ignoreHierarchy","false"})})
    public String refreshQRCode(){
        token = tokenUtils.generateNewToken();
        addActionMessage(token);
        System.out.println("deptID:"+deptID+"\ttimes:"+times);
        deptID = deptID;
        times = times;
        return INPUT;
    }

    @Action(value = "memberSign", results = {@Result(name = INPUT, type = "json", params = {"ignoreHierarchy","false"}),
            @Result(name = "login", location = "loginRedirect", type = "redirect"),
            @Result(name = "QRcodeOutOfRange", location = "jsp/QRcodeOutOfRange.jsp")
    })
    public String memberSign(){
        Person person =(Person) session.get(loginKey);
        if(person == null)return "login";
        if(tokenUtils.isResubmit(session, token)){
            addActionMessage("你的二维码过期了哦，快去扫描新的吧～～");
            return "QRcodeOutOfRange";
        }
        Member member = new Member();
        try {
            typeConverter.convert(person, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加签到记录
        Register register = new Register();
        register.setRegisterID(new RegisterID(new Department(deptID), times));
        register.addMember(member);
        registerService.add(register);

        System.out.println("签到一次\n"+member);
        return INPUT;
    }

    //getter and stter
    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
