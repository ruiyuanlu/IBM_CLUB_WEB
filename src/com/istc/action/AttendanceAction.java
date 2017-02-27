package com.istc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * 部长及以上成员管理考勤信息
 */
@Action(
        value="Sign",
        results={
                @Result(name="success", location = "meetingsignsuccess.jsp"),
                @Result(name="fail", location="meetingsignfail.jsp")
        }
)
public class AttendanceAction extends ActionSupport{
    static boolean signavailable=true;

    @Override
    public String execute() {
        //这里写从数据库获取当前时候可以签到的变量
        if (signavailable){
            return SUCCESS;
        }
        else {
            return "fail";
        }
    }

}
