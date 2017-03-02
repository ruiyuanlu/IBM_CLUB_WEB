package com.istc.action;

import com.istc.bean.Person;
import com.istc.validation.TokenCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Morn Wu on 2017/2/28.
 * 部长管理考勤信息
 */
@ParentPackage("needajax")
@AllowedMethods({"fetchRestPerson", "manualSign", "qrcodeSign"})
public class AttendanceAction extends ActionSupport implements SessionAware,ServletRequestAware {

    public static List<Person> deptmember =new ArrayList<Person>();
    Map<String, Object> session;
    public static String signtoken;
    private HttpServletRequest request;


    static {
        addtemp();
    }

    @Action(
            value="qrcodeSign",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String qrcodeSign(){
        signtoken= TokenCheck.generateNewToken();
        System.out.println("token变化："+signtoken);
        return INPUT;
    }


    @Action(
            value="manualSign",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String manualSign(){
        try {
            System.out.println("人数："+deptmember.size());
            int restmembersize=deptmember.size();
            for (int i=0;i<restmembersize;i++){
                //根据获得的situation相关信息写入数据库相应的签到对象
                //Situation有4种值：attend（出席），absent（缺席），leave（请假），late（迟到）
                String situation=(String) request.getParameter(deptmember.get(deptmember.size()-1).getID());
                System.out.println(situation);
                deptmember.remove(deptmember.get(deptmember.size()-1));
            }
        }
        catch (Exception e){
            addFieldError("manualSign","手动签到失败！");
            return INPUT;
        }
        return fetchRestPerson();
    }

    @Action(
            value="fetchRestPerson",
            results={
                    @Result(name="input", location = "attendance.jsp"),
            }
    )
    public String fetchRestPerson(){
        //这里主要是数据库的代码
        try {
            session.put("signlist",deptmember);
            if (deptmember.size()==0){
                addActionError("所有人员的签到状态已经更新完毕！");
            }
            return INPUT;
        }
        catch (Exception e){
            addFieldError("manualSign","成员获取失败！");
            return INPUT;
        }
    }

    public static void addtemp() {
        //原则上是从数据库中获取数据，这里为了测试用假数据
        Person curPerson = new Person();
        curPerson.setID("2141601033");
        curPerson.setName("张三");
        deptmember.add(curPerson);

        curPerson=new Person();
        curPerson.setID("2141601022");
        curPerson.setName("李四");
        deptmember.add(curPerson);

        curPerson=new Person();
        curPerson.setID("2141601011");
        curPerson.setName("王五");
        deptmember.add(curPerson);

        curPerson=new Person();
        curPerson.setID("2141601044");
        curPerson.setName("赵六");
        deptmember.add(curPerson);
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setSession(Map<String, Object> arg0) {
        // TODO Auto-generated method stub
        this.session=arg0;
    }

    public static String getSigntoken() {
        return signtoken;
    }
}
