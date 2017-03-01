package com.istc.action;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityService.MemberService;
import com.istc.Validation.InjectionCheck;
import com.istc.Validation.TokenCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by lurui on 2017/2/16 0016.
 */

@Controller("loginAction")
@Scope(scopeName = "prototype")
//以下两个注解在struts2-convention-plugin.jar中
@Namespace("")
@ParentPackage("ajax")
/**
 * 注意,Struts有个坑，是所有的action必须放在action, actions, struts2, struts, xwork报名下(区分大小写), 否则不认识Action
 * 会显示"404 error" 找不到对应的action
 * ServletResponseAware 接口可以自动设置 httpServletResponse 从而获取 http 上下文的 cookie
 * SessionAware 接口可以自动设置 sessionMap 从而获取 Session 上下文的 session
*/
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware,ServletResponseAware {
    private static final long serialVersionUID = 6473585621724667329L;

    @Resource(name = "memberService")
    private MemberService memberService;

    //配置 Session
    private SessionMap<String, Object> sessionMap;
    private String id;
    private String password;

    //配置 token
    private HttpServletResponse httpServletResponse;
    private String token;
    private String remember;//是否记忆登陆，前端表单提交

    //自定义的 token 检查器，实现在 Utilities 中
    private TokenCheck tokenChecker;

    //定义全局常量
    private final String loginKey = "member";
    private final String tokenKey = "token";

    /**
     * 产生登陆动作时检查浏览器的token
     * 这样可以只检查一遍token
     * 并且可以通过token自动登陆
     */
    public LoginAction(){
        tokenChecker = TokenCheck.getInstance();
    }

    /**
     * 登陆
     * @return 返回操作结果的字符串(名称)
     * 与Action中的results的name属性相对应
     */
    @Action(
            value = "login", results = {
            // 1.如果location中不加'/', 则会使用Spring默认路径前缀: /WEB-INF/content
            //    例如location = "jsp/success.jsp" 实际路径指向:web(Eclipse 中为webRoot)/WEB-INF/content/jsp/success.jsp
            // 2.如果location中加'/', 则不会增加任何前缀, 实际路径与location中的一致
            //    例如location = "/jsp/success.jsp" 实际路径指向:web(Eclipse 中为webRoot)/jsp/success.jsp
            // 3.由于有validateLogin方法, 所有重写的validate方法内置为会返回INPUT
            @Result(name = "success", location = "welcome", type="redirect"),
            @Result(name = "fail", location = "jsp/fail.jsp"),
            @Result(name = INPUT, type = "json", params = {"ignoreHierarchy", "false"})
//            @Result(name = INPUT, location = "/index.jsp")
    }
    )
    public String login() throws Exception{
        System.out.println("login 启动");
        tokenCheck();// token 登陆检查
        Member member = (Member) sessionMap.get(loginKey);
        if(member == null){
            member = new Member();
            member.setID(id);
            member.setPassword(password);
        }
        member = memberService.get(member);
        if(member == null){
            addActionError("学号或密码错误!");
            return "fail";
        }
        sessionMap.put(loginKey, member);
        return "success";
    }


    @Action(value = "logout", results = {@Result(name="exit",location="jsp/logout.jsp")})
    public String logout()throws Exception {
        if (sessionMap != null)
            sessionMap.invalidate();
        return "exit";
    }

    /**
     * 验证器: 验证登陆有效性
     * 理论上自动被Struts根据方法名调用
     * 验证失败时自动返回 INPUT，成功时为 void
     */
    public void validateLogin(){
        if (id == null || id.equals("")) {
            addFieldError("id", "请输入学号！");
        }
        if (password == null || password.equals("")) {
            addFieldError("password", "请输入密码！");
        }
        InjectionCheck injectionChecker = InjectionCheck.getInstance();
        if (!injectionChecker.isValid(id)) {
            addFieldError("id", "请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
        }
        if (!injectionChecker.isValid(password)) {
            addFieldError("password","请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
        }
    }

    public void tokenCheck(){
        if(tokenChecker.isResubmit(sessionMap, token)){
            addFieldError(tokenKey, "请勿重复提交信息哦(●'◡'●)");
        }
        String currentToken = tokenChecker.generateNewToken();
        sessionMap.put(loginKey, currentToken);
        addActionMessage(currentToken);
    }

    /**
     * SessionAware 接口
     * ServletResponse 接口
     */
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * 属性只提供 setter，不提供 getter
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }
}
