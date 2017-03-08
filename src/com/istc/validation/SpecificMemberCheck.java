package com.istc.validation;

import com.istc.bean.Person;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Morn Wu on 2017/3/8.
 */
public class SpecificMemberCheck extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        HttpSession session= ServletActionContext.getRequest().getSession();
        String id=((Person)session.getAttribute("personInfo")).getID();
        Map<String, String[]> map=actionInvocation.getInvocationContext().getParameters().toMap();
        String[] deptarray=map.get("dept");
        int dept=0;
        try {
            if(deptarray!=null)
                dept=Integer.parseInt(deptarray[0].trim());
            System.out.println("特定部员拦截器启动，当前请求的部门ID："+dept+"，当前部员ID为："+id);
            return actionInvocation.invoke();
        }
        catch (Exception e){
            return "exit";
        }
    }
}
