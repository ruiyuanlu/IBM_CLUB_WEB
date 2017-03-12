package com.istc.Validation;

/**
 * Created by lurui on 2017/2/24 0024.
 */

import com.istc.Entities.Entity.Person;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 一个超时登录的拦截器
 */

@SuppressWarnings("serial")

public class SessionCheck extends MethodFilterInterceptor {

    private static final String loginKey = "member";
    private final String prePageKey = "prePage";

    @SuppressWarnings("unchecked")
    @Override
    public String doIntercept(ActionInvocation invocation) throws Exception {
        System.out.println("全局 SessionCheck 拦截器启动。");
        //获取上下文
        ActionContext context = invocation.getInvocationContext();
        //获取session对象
        Map<String, Object> session = context.getSession();
        //验证是否登陆
        Person userinfo = (Person) session.get(loginKey);
        //验证Session是否过期
        if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
            //session过期,转向session过期提示页,最终跳转至登录页面
            System.out.println("session已过期。转入loginRedirect");
            return "loginRedirect";
        }
        if (userinfo == null) {
            //尚未登录,跳转至登录页面
            //获取 httpservletRequest 请求
            HttpServletRequest req = ServletActionContext.getRequest();
            //获取请求路径
            String path = req.getRequestURI().substring(5);//根据需要，去除application name, 由于intellij idea 没有工程名为 /jsp/signRedirect，
            // 所以去掉 /jsp/, 只保留 signRedirect
            System.out.println("path 为;"+path);
            //获取请求中的参数
            String queryString = req.getQueryString();
            if(queryString == null)queryString = "";
            //地址拼凑
            String reaPath = path + "?" + queryString;
            //存入session供登陆使用
            session.put(prePageKey, reaPath);

            System.out.println("session非法。转入loginRedirect");
            return "loginRedirect";
        }
        System.out.println("session正常。退出");
        return invocation.invoke();
    }
}
