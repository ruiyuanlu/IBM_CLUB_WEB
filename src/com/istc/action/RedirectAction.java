package com.istc.action;

/**
 * Created by lurui on 2017/2/24 0024.
 */
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于实现页面的重定向
 */
@ParentPackage("all")
//有的部署时没有 @AllowedMethods 时无法找到注册的方法
//@AllowedMethods({"mainpage","welcome","login","register","fileupload"})
public class RedirectAction extends ActionSupport{

    private static final long serialVersionUID = 1L;

    @Action(value="welcome", results={@Result(name="welcome",location="jsp/welcome.jsp")})
    public String welcome() {
        return "welcome";
    }


}
