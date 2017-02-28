package com.istc.action;

import com.istc.bean.Person;
import com.istc.validation.TokenCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * 用于部员的签到
 */
@Action(
        value="Sign",
        results={
                @Result(name="success" ,location = "meetingsign.jsp"),
        }
)
public class SignAction extends ActionSupport implements SessionAware{
    Map<String, Object> session;
    String tokenfetch; //扫码后传递给后端的token值

    @Override
    public String execute() {
        //首先从数据库中获取Signtoken的值，这里省略
        //获取当前会议信息，这里省略
        if (tokenfetch.equals(AttendanceAction.signtoken)){
            session.put("sign",true);
            //向数据库写入一个签到对象，这里省略
            //这个删除只是方便测试，具体需要从数据库生成信息
            for (int i=0;i<AttendanceAction.deptmember.size();i++){
                if (AttendanceAction.deptmember.get(i).getID().equals(((Person)session.get("personInfo")).getID())){
                    System.out.println("已删除："+AttendanceAction.deptmember.get(i).getID());
                    AttendanceAction.deptmember.remove(i);
                    break;
                }
            }
            AttendanceAction.signtoken= TokenCheck.generateNewToken();
            return SUCCESS;
        }
        else {
            session.put("sign",false);
            //向数据库写入一个签到对象，这里省略
            AttendanceAction.signtoken= TokenCheck.generateNewToken();
            return SUCCESS;
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    public String getTokenfetch() {
        return tokenfetch;
    }

    public void setTokenfetch(String tokenfetch) {
        this.tokenfetch = tokenfetch;
    }
}
