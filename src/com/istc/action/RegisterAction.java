package com.istc.action;

import com.istc.Entities.Entity.Interviewee;
import com.istc.Service.EntityService.IntervieweeService;
import com.istc.Service.EntityService.PersonService;
import com.istc.Utilities.CookieUtils;
import com.istc.Utilities.Encoder;
import com.istc.Utilities.TokenUtils;
import com.istc.Validation.RegisterCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import com.istc.Validation.RegisterCheck.Type;

/**
 * Created by lurui on 2017/3/3 0003.
 */

@Controller("registerAction")
@Scope("prototype")
@ParentPackage("ajax")
@AllowedMethods({"register"})
public class RegisterAction extends ActionSupport implements SessionAware{

    private static final long serialVersionUID = 187387589387L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Resource(name = "personService")
    private PersonService personService;
    @Resource(name = "intervieweeService")
    private IntervieweeService intervieweeService;

    //表单提交的属性
    private String id;
    private String birthday;
    private String name;
    private String password;
    private String repassword;
    private String QQ;
    private String phoneNumber;
    private boolean gender;
    private String token;
    private String newToken;

    //会话
    private Map<String, Object> session;

    // 工具
    private TokenUtils tokenUtil;
    private CookieUtils cookieUtil;
    private RegisterCheck registerUtil;
    private Encoder encoder;

    public RegisterAction(){
        tokenUtil = TokenUtils.getInstance();
        cookieUtil = CookieUtils.getInstance();
        registerUtil = RegisterCheck.getInstance();
        encoder = Encoder.getInstance();
    }

    @Action(value = "register", results = {@Result(name = INPUT, type = "json", params = {"IgnoreHierarchy", "false"})})
    public String register(){
        newToken = tokenUtil.tokenCheck(this, session, token);
        Interviewee interviewee = new Interviewee(id, false);
        interviewee.setPassword(encoder.encodeSHA512(password.getBytes()));
        interviewee.setName(name);
        interviewee.setQQ(QQ);
        interviewee.setPhoneNumber(phoneNumber);
        interviewee.setGender(gender);
        Calendar birth = Calendar.getInstance();
        try {
            birth.setTime(sdf.parse(birthday));
        } catch (ParseException e) {
        }
        interviewee.setBirthday(birth);//设置年龄
        //存入数据库
        intervieweeService.add(interviewee);
        return INPUT;
    }

    public void validateRegister(){
        // id 检查
        if (id == null || id.equals(""))addFieldError("id", "请输入您的学号！");
        else if(!registerUtil.isValid(Type.ID, id))addFieldError("id", "您的学号输入有误，请检查并重新输入!");
        else if(personService.exist(id))addFieldError("id", "您的学号已经被注册过! 请登录或尝试找回密码!");
        // 密码检查
        else if(password == null || password.equals(""))addFieldError("password", "请输入您的密码!");
        else if(!registerUtil.isValid(Type.PASSWORD, password))addFieldError("password", "密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位!");
        else if(!password.equals(repassword))addFieldError("repassword", "两次输入的密码不一致!");
        // 姓名检查
        else if(name == null || name.equals(""))addFieldError("name", "请输入您的姓名!");
        else if(!registerUtil.isValid(Type.NAME, name))addFieldError("name", "请输入正确的姓名信息!");
        // 号码检查
        else if(phoneNumber == null || phoneNumber.equals(""))addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您!");
        else if(!registerUtil.isValid(Type.PHONE_NUMBER, phoneNumber))addFieldError("phoneNumber", "请输入有效的手机号码!");
        // QQ 号检查
        else if(QQ == null || QQ.equals(""))addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动!");
        else if(!registerUtil.isValid(Type.QQ, QQ))addFieldError("QQ", "您的QQ号输入有误，请检查并重新输入!");
        // 生日检查
        else if(birthday == null || birthday.equals(""))addFieldError("birthday", "请输入您的生日以便社团成员为您庆祝生日!");
        else if(!registerUtil.isValid(Type.BIRTHDAY, birthday))addFieldError("birthday", "您输入的生日已经超越极限啦!您是来逗逼的吧!");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean setMale() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }
}
