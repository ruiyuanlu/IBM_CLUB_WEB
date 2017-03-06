package com.istc.action;

import java.util.*;

import com.istc.bean.Person;
import com.istc.validation.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于管理成员信息的增删改查，其中手动增加成员仅可以由部长级以上成员完成
 */
@ParentPackage("needajax")
@AllowedMethods({"addPerson","changePassword","fetchAllPerson","deletePersonSubmit","resetPasswordSubmit","fetchPersonInfo","modifyInfo"})
public class PersonnelAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;
    private String id;
    private String name;
    private String oldpassword;
    private String password;
    private String repassword;
    private String dept;
    private boolean gender;
    private String birthday;
    private String QQ;
    private String phoneNumber;
    private Map<String,Object> jsonresult=new HashMap<String,Object>();
    public static List<Person> deptmember =new ArrayList<Person>();
    private String[] deleted;
    private String needreset;

    static {
        addtemp();
    }

    @Action(
            value="addPerson",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addPerson(){
        try{
            System.out.println(id+" "+password+" "+name+" "+dept);
            Person p=new Person();
            p.setID(id);
            p.setPassword(password);
            p.setName(name);
            p.setAge(18);
            deptmember.add(p);
        }
        catch (Exception e){
            addFieldError("addPerson","添加用户失败！");
        }
        return INPUT;
    }

    public void validateAddPerson(){
        if (id==null || id.equals("")) {
            addFieldError("id", "请输入学号！");
        }
        else {
            if (!new IDCheck(id).getResult()) {
                addFieldError("id", "学号输入有误，请检查并重新输入。");
            }
/*            else if (condition) {
			addFieldError("id", "您的学号已经被注册过！请登录或尝试找回密码。");
		}*/
            else {

            }
        }
        if (password==null || password.equals("")) {
            addFieldError("password", "请设置密码！");
        }
        else {
            if (!new PasswordCheck(password).getResult()) {
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
            if (!new NameCheck(name).getResult()) {
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
            System.out.println("修改前的密码："+oldpassword);
            System.out.println("修改后的密码："+password);
            this.session.clear();
            response=CookieUtils.clearCookie(request,response);
            System.out.println("session和cookie均被清除");
        }
        catch (Exception e){
            addFieldError("changePassword","密码修改失败！");
        }
        return INPUT;
    }

    public void validateChangePassword(){
        //此处有从数据库获取旧密码的步骤，这里先用假数据测试，用户ID的来源是session
        String oldpasswordfromdatabase="456789";
        if (oldpassword==null || oldpassword.equals("")){
            addFieldError("oldpassword","请输入旧密码！");
        }
        else {
            if (!oldpassword.equals(oldpasswordfromdatabase)){
                addFieldError("oldpassword","您输入的旧密码不正确！");
            }
        }
        if (password==null || password.equals("")){
            addFieldError("password","请键入新密码！");
        }
        else {
            if (oldpasswordfromdatabase.equals(password)){
                addFieldError("password","请键入与旧密码不同的新密码！");
            }
            if (!new PasswordCheck(password).getResult()){
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
        //这里主要是数据库的代码
        try {
            jsonresult.put("deptmember",deptmember);
            if (deptmember.size()==0){
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
            for (int i = 0; i < deleted.length; i++) {
                //在数据库中删除对应人员
                for (int j = 0; j < deptmember.size(); j++) {
                    if(deleted[i].trim().equals(deptmember.get(j).getID().trim())){
                        deptmember.remove(j);
                        break;
                    }
                }
            }
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
    public String resetPasswordSubmit(){
        try {
            for (int  i= 0; i < deptmember.size(); i++) {
                if(needreset.trim().equals(deptmember.get(i).getID().trim())){
                    Person curPerson=deptmember.get(i);
                    System.out.println("修改前的密码："+curPerson.getPassword());
                    curPerson.setPassword("111111");
                    deptmember.set(i,curPerson);
                    System.out.println("重置后的密码："+deptmember.get(i).getPassword());
                    jsonresult.put("resetresult",true);
                    break;
                }
            }
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
    public String fetchPersonInfo(){
        //这里主要是数据库的代码
        try {
            this.id=((Person)session.get("personInfo")).getID();
            Person p=new Person();
            p.setID(id);
            p.setName("张三");
            p.setGender(false);
            p.setAge(18);
            p.setPhoneNumber("15291590790");
            p.setQQ("838000479");
            jsonresult.put("curPerson",p);
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
    public String modifyInfo(){
        try {
            System.out.println(name+" "+gender+" "+birthday+" "+phoneNumber+" "+QQ);
        }
        catch (Exception e){
            addFieldError("fetchPersonInfo","修改用户信息失败！");
        }
        return INPUT;
    }

    public void validateModifyInfo(){
        if (name==null || name.equals("")) {
            addFieldError("name", "请输入您的姓名！");
        }
        else {
            if (!new NameCheck(name).getResult()) {
                addFieldError("name", "请输入正确的姓名信息！");
            }
            else {
            }
        }
        if (phoneNumber==null || phoneNumber.equals("")) {
            addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您。");
        }
        else {
            if (!new PhoneNumberCheck(phoneNumber).getResult()) {
                addFieldError("phoneNumber", "请输入有效的大陆手机号码！");
            }
            else {
            }
        }
        if (QQ==null || QQ.equals("")) {
            addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动。");
        }
        else {
            if (!new QQCheck(QQ).getResult()) {
                addFieldError("QQ", "请输入正确的QQ号码！");
            }
            else {
            }
        }
        //西安交通大学招生简章规定，少年班的入学年龄不得低于14岁
        Calendar curtime = Calendar.getInstance();
        curtime.setTime(new Date());
        if (birthday.equals("")) {
            addFieldError("birthday", "请输入您的生日！");
        }
        else {
            if(!new DateCheck(birthday).getResult()){
                addFieldError("birthday", "输入的出生日期不得晚于"+(curtime.get(Calendar.YEAR)-14)+"年1月1日，不得早于1970年1月1日");
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
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

    public static void addtemp() {
        //原则上是从数据库中获取数据，这里为了测试用假数据
        Person curPerson = new Person();
        curPerson.setID("2141601033");
        curPerson.setPassword("102030");
        curPerson.setName("张三");
        curPerson.setAge(18);
        deptmember.add(curPerson);

        curPerson=new Person();
        curPerson.setID("2141601022");
        curPerson.setPassword("qwerty");
        curPerson.setName("李四");
        curPerson.setAge(18);
        deptmember.add(curPerson);

        curPerson=new Person();
        curPerson.setPassword("asdfgh");
        curPerson.setID("2141601011");
        curPerson.setName("王五");
        curPerson.setAge(18);
        deptmember.add(curPerson);

        curPerson=new Person();
        curPerson.setID("2141601044");
        curPerson.setPassword("567890");
        curPerson.setName("赵六");
        curPerson.setAge(18);
        deptmember.add(curPerson);
    }
}
