package com.istc.action;

import com.istc.Entities.Entity.*;
import com.istc.Service.EntityService.*;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * 用于管理成员信息的增删改查，其中手动增加成员仅可以由部长级以上成员完成
 */
@Controller("personnelAction")
@Scope("prototype")
@ParentPackage("ajax")
@AllowedMethods({"addMember","changePassword","personUpgrade","chooseDept","fetchAllPerson","getRestInterviewees","deleteMemberSubmit","resetPasswordSubmit","fetchMemberInfo","modifyInfo"})
public class PersonalAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    //生日字符串模板格式
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //会话
    private Map<String, Object> session;


    @Resource(name = "intervieweeService")
    private IntervieweeService intervieweeService;
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
    //要升级为部员成员id组，字符串
    private String readyIntervieweeIdLine;
    //要升级为部员成员id组,action中分割得到
    private String[] readyIntervieweeId;




    private Map<String,Object> jsonresult=new HashMap<String,Object>();
    //默认重置密码为111111，用以部长重置部员
    public static final String defaultPassword="111111";
    private String deletedLine;
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
    public PersonalAction(){
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
     * addMember为直接添加部门成员，不是从person转变为member，通过添加含有所属部门的部员来建立关系
     */
    public String addMember(){
        newToken = tokenUtil.tokenCheck(this, session, token);
        System.out.println(id+" "+password+" "+name+" "+dept);
        Member p=new Member();
        p.setID(id);
        password= encoder.encodeSHA512(password.getBytes());
        p.setPassword(password);
        p.setName(name);
        p.setGender(gender);
        p.setQQ(QQ);
        p.setPhoneNumber(phoneNumber);
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(p.toString());
        p.setBirthday(calendar);
        Department depart0=departmentService.get(dept);
        p.addDepartment(depart0);
        memberService.save(p);
        return INPUT;
    }

    public void validateAddMember(){
        if (dept==0||!departmentService.exist(dept)){
            addFieldError("dept", "不存在该部门！");
        }
        if (id==null || id.equals("")) {
            addFieldError("id", "请输入学号！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.ID,id)) {
                addFieldError("id", "学号输入有误，请检查并重新输入。");
            }
            if (personService.exist(id)){
                addFieldError("id","该学号仍为preson或member，请删除后添加");
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
        }
        if (name==null || name.equals("")) {
            addFieldError("name", "请输入姓名！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.NAME,name)) {
                addFieldError("name", "请输入正确的姓名信息！");
            }
        }
    }


    @Action(
            value="getRestInterviewees",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     *取出所有面试者，以   形式置于json（可以改为使用isPassed参数有选择的取人，
     * 但因为目前通过面试方法为删去interviewee故暂为取所有interviewee）
     */
    public String getRestInterviewees(){
        List<Interviewee> interviewees=intervieweeService.getRestInterviewees();
        if(interviewees==null||interviewees.get(0)==null){
            addFieldError("getRestInterviewees","已无未面试人员，面试终了！");
            return INPUT;
        }

        jsonresult.put("interviewees",interviewees);
        return INPUT;
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
            Person person= personService.get(id);
            oldpassword=person.getPassword();
            person.setPassword(password);
            personService.update(person);
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
        //此处有从数据库获取旧密码的步骤，用户ID的来源是session
        //struts能够通过session   GET到操作者ID
        this.id = ((Person)session.get(loginKey)).getID();
        Person person = personService.get(id);
        if(!personService.exist(id))addFieldError("id", "当前用户不存在");
        String passwordFD = person.getPassword();
        //密码合法性检查
        if (oldpassword==null || oldpassword.equals("")){
            addFieldError("oldpassword","请输入旧密码！");
        }
        if (password==null || password.equals("")){
            addFieldError("password","请键入新密码！");
        }
        else {

            if (!registerUtil.isValid(RegisterCheck.Type.PASSWORD,password)){
                addFieldError("password","密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
            }
            if (!password.equals(repassword)){
                addFieldError("repassword","两次输入的密码不一致！");
            }
        }
        //后台加密并检验密码正确性
        password= encoder.encodeSHA512(password.getBytes());
        oldpassword= encoder.encodeSHA512(oldpassword.getBytes());
        System.out.println("user input:"+oldpassword);
        System.out.println("DB input  :"+passwordFD);
        if (passwordFD.equals(password)){
            addFieldError("password","请键入与旧密码不同的新密码！");
        }
        if (!oldpassword.equals(passwordFD)){
            addFieldError("oldpassword","您输入的旧密码不正确！");
        }
    }

    @Action(
            value="chooseDept",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public  String chooseDept(){
        Member tommy;
        tommy=memberService.get(id);
        tommy.getEnterDepts().add(departmentService.get(dept));
        memberService.update(tommy);
        return INPUT;
    }
    public  void  validateChooseDept(){
        id = ((Person)session.get(loginKey)).getID();
        if (!departmentService.exist(dept))addFieldError("dept","您要加入的部门不存在");
        System.out.println("memberService.exist(id):   "+memberService.exist(id));
        if (id==null||!memberService.exist(id))addFieldError("id","您不是member类成员！");
        else   for (Department department:memberService.get(id).getEnterDepts()){
            if (department.getDeptID()==dept)
                addFieldError("dept","您已加入该部门");
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
        System.out.println("deptMember.toString()"+deptMember.toString());
        System.out.println("deptMember.size()    "+deptMember.size());
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
            value="personUpgrade",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     * 将传入interviwee等人升级为部门为dept的member
     */
    public String personUpgrade(){
        intervieweeService.setIntervieweesToMembers(readyIntervieweeId);
        return INPUT;
    }
    public void  validatePersonUpgrade(){
        //要通过人员的id的分割
        if (readyIntervieweeIdLine==null)
            addFieldError("personUpgrade","请输入面试者ID！");
        readyIntervieweeId=readyIntervieweeIdLine.split(",");
        if (readyIntervieweeId==null||readyIntervieweeId[0]==null){
            addFieldError("personUpgrade","输入面试者ID格式有误");
        }
        else  for (int i=0;i<readyIntervieweeId.length;i++){
            if (!intervieweeService.exist(readyIntervieweeId[i])){
                addFieldError("personUpgrade","所选成员中有的不是interviewee!");
                break;
            }
        }
    }

    @Action(
            value="deleteMemberSubmit",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String deleteMemberSubmit(){
        try {
            //在数据库中删除对应人员。因为人员与部门为多对多双向管理，所以这里是用的方法是使用字符串拼接直接删除member
            memberService.remove(deleted);
        }
        catch (Exception e){
            addFieldError("deleteMember","删除人员失败！");
        }
        return INPUT;
    }
    public void  validateDeleteMemberSubmit(){
        if (deletedLine==null)
            addFieldError("deleted","请输入要删除者ID！");
        else {
            deleted = deletedLine.split(",");
            if (deleted == null | deleted[0] == null) {
                addFieldError("deleted", "输入删除者ID格式有误");
            }
            else for (String id:deleted){
                if (!memberService.exist(id)){
                    addFieldError("deleted","被删除者有的不为member");
                    break;
                }
            }
        }
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
            System.out.println("修改前的密码加密："+curmember.getPassword());
            curmember.setPassword(defaultPassword);
            memberService.update(curmember);
            System.out.println("重置后的密码加密："+memberService.get(needreset.trim()).getPassword());
            jsonresult.put("resetresult",true);
        }
        catch (Exception e){
            addFieldError("resetPassword","密码重置失败！");
        }
        return INPUT;
    }

    @Action(
            value="fetchMemberInfo",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    /**
     * 取当前操作者的信息，即((Person)session.get(loginKey)).getID()的id
     */
    public String fetchMemberInfo(){

        try {
            this.id=((Person)session.get(loginKey)).getID();
            Member curMember= memberService.get(id);
            jsonresult.put("curPerson",curMember);
            return INPUT;
        }
        catch (Exception e){
            addFieldError("fetchMemberInfo","成员获取失败！");
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
     * 修改Member类型对象并保存
     */
    public String modifyInfo(){
        try {
            id= ((Person)session.get(loginKey)).getID();
            Member member_on;
            member_on=memberService.get(id);
            if (member_on==null){
                addFieldError("fetchPersonInfo","您的id不存在！");
                return INPUT;}
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
            e.printStackTrace();
            addFieldError("fetchPersonInfo","修改用户信息失败！");
        }
        return INPUT;
    }

    public void validateModifyInfo(){
        if (name==null || name.equals("")) {
            addFieldError("name", "请输入您的姓名！");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.NAME,name)) {
                addFieldError("name", "请输入正确的姓名信息！");
            }
        }
        if (phoneNumber==null || phoneNumber.equals("")) {
            addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您。");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.PHONE_NUMBER,phoneNumber)) {
                addFieldError("phoneNumber", "请输入有效的大陆手机号码！");
            }
        }
        if (QQ==null || QQ.equals("")) {
            addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动。");
        }
        else {
            if (!registerUtil.isValid(RegisterCheck.Type.QQ,QQ)) {
                addFieldError("QQ", "您的QQ号输入有误，请检查并重新输入!");
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

    public String getDeletedLine() {
        return deletedLine;
    }

    public void setDeletedLine(String deletedLine) {
        this.deletedLine = deletedLine;
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

    public String getReadyIntervieweeIdLine() {
        return readyIntervieweeIdLine;
    }

    public void setReadyIntervieweeIdLine(String readyIntervieweeIdLine) {
        this.readyIntervieweeIdLine = readyIntervieweeIdLine;
    }

    public String[] getReadyIntervieweeId() {
        return readyIntervieweeId;
    }

    public void setReadyIntervieweeId(String[] readyIntervieweeId) {
        this.readyIntervieweeId = readyIntervieweeId;
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
