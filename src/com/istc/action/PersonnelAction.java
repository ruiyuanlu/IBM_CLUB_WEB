package com.istc.action;

import com.istc.Entities.Entity.*;
import com.istc.Service.EntityService.DepartmentService;
import com.istc.Service.EntityService.MemberService;
import com.istc.Service.EntityService.MinisterService;
import com.istc.Service.EntityService.PersonService;
import com.istc.Validation.*;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;
import com.istc.Utilities.Encoder;
import com.istc.Utilities.TokenUtils;
import com.istc.Utilities.CookieUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用于管理成员信息的增删改查，其中手动增加成员仅可以由部长级以上成员完成
 */

@ParentPackage("ajax")
@AllowedMethods({"addMember","changePassword","fetchAllPerson","deletePersonSubmit","resetPasswordSubmit","fetchPersonInfo","modifyInfo"})
public class PersonnelAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    //生日字符串模板格式
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //会话
    private Map<String, Object> session;


    @Resource(name = "ministerService")
    private MinisterService ministerService;
    @Resource(name = "personService")
    private PersonService personService;
    @Resource(name = "departmentService")
    private DepartmentService departmentService;
    @Resource(name = "memberService")
    private MemberService memberService;

    //表单传入值
    private String id;
    private String name;
    private String oldpassword;
    private String password;
    private String repassword;
    private int dept;
    private boolean gender;
    private String birthday;
    private String QQ;
    private String phoneNumber;
    private String token;
    private String newToken;
    //部长下属所有部员or部长下属部门id为dept的所有成员
    private List<Member> deptMember;

    private Map<String,Object> jsonresult=new HashMap<String,Object>();
    //默认重置密码为111111，用以部长重置部员
    public static final String defaultPassword="111111";

    private String[] deleted;
    private String needreset;

    //工具
    private TokenUtils tokenUtil;
    private CookieUtils cookieUtil;
    private RegisterCheck registerUtil;
    private Encoder encoder;

    //定义全局常量
    private final String loginKey = "member";
    private final String tokenKey = "token";
    private final String prePageKey = "prePage";
public PersonnelAction(){
    tokenUtil = TokenUtils.getInstance();
    cookieUtil = CookieUtils.getInstance();
    registerUtil = RegisterCheck.getInstance();
    encoder = Encoder.getInstance();
}
    @Action(
            value="addMember",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     * addMember为直接添加部门成员，不是从person转变为member
     */
    public String addMember(){
        try{
            /**
             * 检查token?
             */
            newToken = tokenUtil.tokenCheck(this, session, token);
            System.out.println(id+" "+password+" "+name+" "+dept);
            Member p=new Member();
            p.setID(id);
            p.setPassword(password);
            p.setName(name);
            p.setGender(gender);
            p.setQQ(QQ);
            p.setPhoneNumber(phoneNumber);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(birthday));
            p.setBirthday(calendar);
            //以更新部门的方式完成部员添加，建立了关系
            Department depart0=departmentService.get(dept);
            depart0.getMembers().add(p);
            departmentService.update(depart0);

        }
        catch (Exception e){
            addFieldError("addMember","添加用户失败！");
        }
        return INPUT;
    }

    public void validateAddPerson(){
        if (id==null || id.equals("")) {
            addFieldError("id", "请输入学号！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.ID,id)) {
                addFieldError("id", "学号输入有误，请检查并重新输入。");
            }
            else if (memberService.exist(id)) {
			addFieldError("id", "您的学号已经被注册过！请登录或尝试找回密码。");
		}
            else {

            }
        }
        if (password==null || password.equals("")) {
            addFieldError("password", "请设置密码！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.PASSWORD,password)) {
                addFieldError("password", "密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
            }
            else if (!password.equals(repassword)) {
                addFieldError("repassword", "两次输入的密码不一致！");
            }
            else {
            }
        }
        if (name==null || name.equals("")) {
            addFieldError("name", "请输入姓名！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.NAME,name)) {
                addFieldError("name", "请输入正确的姓名信息！");
            }
            else {
            }
        }
    }

    @Action(
            value="changePassword",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String changePassword(){
        try {
            /**
             * 假定struts已经把要修改的新密码放入private String password
             * 旧密码置入private string oldpassword
             */
            id= ((Person)session.get(loginKey)).getID();
            Minister minitemp=ministerService.get(id);
            oldpassword=minitemp.getPassword();
            minitemp.setPassword(password);
            ministerService.update(minitemp);
            System.out.println("修改前的密码："+oldpassword);
            System.out.println("修改后的密码："+password);
            this.session.clear();
            cookieUtil.clearCookie(request,response);//返回值为void，不会给response/

            System.out.println("session和cookie均被清除");
        }
        catch (Exception e){
            addFieldError("changePassword","密码修改失败！");
        }
        return INPUT;
    }

    public void validateChangePassword(){
        //此处有从数据库获取旧密码的步骤，这里先用假数据测试，用户ID的来源是session
        //已补充数据库操作，但前提是struts已能够通过session   GET到操作者ID
        this.id=((Person)session.get(loginKey)).getID();
        Minister minitemp=ministerService.get(id);
        String oldPasswordFromDatabase=minitemp.getPassword();
        if (oldpassword==null || oldpassword.equals("")){
            addFieldError("oldpassword","请输入旧密码！");
        }
        else {
            if (!oldpassword.equals(oldPasswordFromDatabase)){
                addFieldError("oldpassword","您输入的旧密码不正确！");
            }
        }
        if (password==null || password.equals("")){
            addFieldError("password","请键入新密码！");
        }
        else {
            if (oldPasswordFromDatabase.equals(password)){
                addFieldError("password","请键入与旧密码不同的新密码！");
            }
            if (!registerUtil.isValid(RegisterCheck.Type.PASSWORD,password)){
                addFieldError("password","密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
            }
            if (!password.equals(repassword)){
                addFieldError("repassword","两次输入的密码不一致！");
            }
        }
    }

    @Action(
            value="fetchAllPerson",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String fetchAllPerson(){
        /**
         * 需要注意的问题：部门对部长为多对多关系，可能一个部长下属多个部门
         * 这里将下属部门id为int dept的部门所有成员返回
         */
        if (!departmentService.exist(dept)){
            addFieldError("fetchPerson","并没有这个部门！");
            return INPUT;
        }
            deptMember = departmentService.getInsideMembers(dept);
        try {
            jsonresult.put("deptmember",deptMember);
            if (deptMember.size()==0){
                addFieldError("fetchPerson","该部门暂无成员！");
                jsonresult.put("hasmember","false");

            }
            return INPUT;
        }
        catch (Exception e){
            addFieldError("fetchPerson","成员获取失败！");
            return INPUT;
        }
    }

    @Action(
            value="deletePersonSubmit",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deletePersonSubmit(){
        try {
            //在数据库中删除对应人员。因为人员与部门为多对多双向管理，所以这里是用的方法是使用字符串拼接直接删除member
            memberService.remove(deleted);
        }
        catch (Exception e){
            addFieldError("deletePerson","删除人员失败！");
        }
        return INPUT;
    }

    @Action(
            value="resetPasswordSubmit",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     * 修改id为needreset的member密码为defaultPassword= "111111"
     */
    public String resetPasswordSubmit(){

        try {       Member curmember=memberService.get(needreset.trim());
                    System.out.println("修改前的密码："+curmember.getPassword());
                    curmember.setPassword(defaultPassword);
                    memberService.update(curmember);
                    System.out.println("重置后的密码："+memberService.get(needreset.trim()).getPassword());
                    jsonresult.put("resetresult",true);
        }
        catch (Exception e){
            addFieldError("resetPassword","密码重置失败！");
        }
        return INPUT;
    }

    @Action(
            value="fetchPersonInfo",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     * 取当前操作者的信息，即((Person)session.get(loginKey)).getID()的id
     */
    public String fetchPersonInfo(){
        //这里主要是数据库的代码
        try {
            this.id=((Person)session.get(loginKey)).getID();
            Member curMember=ministerService.get(id);
            jsonresult.put("curPerson",curMember);
            return INPUT;
        }
        catch (Exception e){
            addFieldError("fetchPersonInfo","成员获取失败！");
            return INPUT;
        }
    }

    @Action(
            value="modifyInfo",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     * 修改person类型对象并保存
     */
    public String modifyInfo(){
        try {//
            this.id=((Person)session.get(loginKey)).getID();
            Member member_on=new Member();
            member_on.setID(id);
             member_on=memberService.get(id);
             member_on.setPassword(password);
             member_on.setQQ(QQ);
            member_on.setGender(gender);
            member_on.setPhoneNumber(phoneNumber);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(birthday));
            member_on.setBirthday(calendar);
            personService.update(member_on);
            System.out.println(member_on);
        }
        catch (Exception e){
            addFieldError("fetchPersonInfo","修改用户信息失败！");
        }
        return INPUT;
    }

    public void validateModifyInfo(){
        if (name==null || name.equals("")) {//！！！修改错误信息返回，使用registeraction
            addFieldError("name", "请输入您的姓名！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.NAME,name)) {
                addFieldError("name", "请输入正确的姓名信息！");
            }
            else {
            }
        }
        if (phoneNumber==null || phoneNumber.equals("")) {
            addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您。");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.PHONE_NUMBER,phoneNumber)) {
                addFieldError("phoneNumber", "请输入有效的大陆手机号码！");
            }
            else {
            }
        }
        if (QQ==null || QQ.equals("")) {
            addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动。");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.QQ,QQ)) {
                addFieldError("QQ", "您的QQ号输入有误，请检查并重新输入!");
            }
            else {
            }
        }
        //西安交通大学招生简章规定，少年班的入学年龄不得低于14岁
        Calendar curtime = Calendar.getInstance();
        if (birthday.equals("")) {
            addFieldError("birthday", "请输入您的生日以便社团成员为您庆祝生日!");
        }
        else {
            if(!registerUtil.isValid(RegisterCheck.Type.BIRTHDAY,birthday)){
                addFieldError("birthday", "您输入的生日已经超越极限啦!您是来逗逼的吧!");
            }
            else {
                //curPerson.setAge(birthday);
            }
        }
        return;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JSON(serialize = false)
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

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    @JSON(serialize = false)
    public String[] getDeleted() {
        return deleted;
    }

    public void setDeleted(String[] deleted) {
        this.deleted = deleted;
    }

    public Map<String, Object> getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(Map<String, Object> jsonresult) {
        this.jsonresult = jsonresult;
    }

    public String getNeedreset() {
        return needreset;
    }

    public void setNeedreset(String needreset) {
        this.needreset = needreset;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }

//    public static void addtemp() {
//        //原则上是从数据库中获取数据，这里为了测试用假数据
//        Person curPerson = new Person();
//        curPerson.setID("2141601033");
//        curPerson.setPassword("102030");
//        curPerson.setName("张三");
//        curPerson.setAge(18);
//        deptmember.add(curPerson);
//
//        curPerson=new Person();
//        curPerson.setID("2141601022");
//        curPerson.setPassword("qwerty");
//        curPerson.setName("李四");
//        curPerson.setAge(18);
//        deptmember.add(curPerson);
//
//        curPerson=new Person();
//        curPerson.setPassword("asdfgh");
//        curPerson.setID("2141601011");
//        curPerson.setName("王五");
//        curPerson.setAge(18);
//        deptmember.add(curPerson);
//
//        curPerson=new Person();
//        curPerson.setID("2141601044");
//        curPerson.setPassword("567890");
//        curPerson.setName("赵六");
//        curPerson.setAge(18);
//        deptmember.add(curPerson);
//    }
}
