//package com.istc.action;
//
//import com.istc.Entities.Entity.Person;
//import com.istc.Service.EntityService.PersonService;
//import com.opensymphony.xwork2.ActionSupport;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.ParentPackage;
//import org.apache.struts2.convention.annotation.Result;
//import org.apache.struts2.dispatcher.SessionMap;
//import org.apache.struts2.interceptor.SessionAware;
//import org.springframework.context.annotation.*;
//import org.springframework.stereotype.*;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
///**
// * Created by lurui on 2017/2/16 0016.
// */
//@Controller("loginAction")
//@Scope(scopeName = "prototype")
////以下两个注解在struts2-convention-plugin.jar中
//@Namespace("/")
//@ParentPackage("struts-default")
///**
// * 注意,Struts有个坑，是所有的action必须放在action, actions, struts2, struts, xwork报名下(区分大小写), 否则不认识Action
// * 会显示"404 error" 找不到对应的action
// */
//public class LoginAction extends ActionSupport implements SessionAware{
//    private static final long serialVersionUID = 6473585621724667329L;
//
//    @Resource(name = "personService")
//    private PersonService personService;
//    private SessionMap<String, Object> sessionMap;
//    private String id;
//    private String password;
//
//    /**
//     * 登陆
//     * @return 返回操作结果的字符串(名称)
//     * 与Action中的results的name属性相对应
//     */
//    @Action(
//            value = "login", results = {
//            //      1.如果location中不加'/', 则会使用Spring默认路径前缀: /WEB-INF/content
//            //          例如location = "jsp/success.jsp" 实际路径指向:web(Eclipse 中为webRoot)/WEB-INF/content/jsp/success.jsp
//            //      2.如果location中加'/', 则不会增加任何前缀, 实际路径与location中的一致
//            //          例如location = "/jsp/success.jsp" 实际路径指向:web(Eclipse 中为webRoot)/jsp/success.jsp
//                    @Result(name = "success", location = "jsp/success.jsp"),
//                    @Result(name = "fail", location = "jsp/fail.jsp")
//            }
//    )
//    public String login() throws Exception{
//        Person person = (Person) sessionMap.get("person");
//        if(person == null){
//            person = new Person();
//            person.setID(id);
//            person.setPassword(password);
//        }
//        person = personService.get(person);
//        if(person == null)
//            return "fail";
//        sessionMap.put("person", person);
//        return "success";
//    }
//
//    @Action(value = "logout", results = { @Result(name = "logout", location = "jsp/logout.jsp") })
//    public String logout()throws Exception {
//        if (sessionMap != null)
//            sessionMap.invalidate();
//        return "logout";
//    }
//
//    //这是需要重写的方法
//    /**
//     * demo如下
//     * http://www.javatpoint.com/struts-2-SessionAware-interface-example
//     * @param map
//     */
//    @Override
//    public void setSession(Map<String, Object> map) {
//        this.sessionMap = (SessionMap<String, Object>) map;
//    }
//
//    public SessionMap getSessionMap(){
//        return this.sessionMap;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
