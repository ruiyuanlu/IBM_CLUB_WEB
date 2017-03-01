package com.istc.Utilities;

/**
 * Created by lurui on 2017/3/1 0001.
 */

import com.istc.Entities.Entity.Member;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 属性 操作工具类
 * Cookies 从 request 中获取Cookie 数组
 */
public class CookieUtils {
    private static CookieUtils self;
    private CookieUtils(){}

    private static final Integer OneDay = 24 * 60 * 60;
    private static final Integer OneWeek = 7 * OneDay;
    private static final Integer ValidTime = 2 * OneWeek; //cookie 内容保存 2 周

    private static final String id = "ID";
    private static final String password = "password";

    public static CookieUtils getInstance(){
        if(self == null)
            synchronized (CookieUtils.class){
                if(self == null) self = new CookieUtils();
            }
        return self;
    }

    /**
     * 生成本次登陆的 cookie 记录并加入到 response中
     * 修改自 generateCookie方法
     * @param member
     * @param response
     */
    public void addCookieToResponse(Member member, HttpServletResponse response){
        Cookie[] cookies = new Cookie[2];
        cookies[0] = new Cookie(id, member.getID());
        cookies[1] = new Cookie(password, member.getPassword());
        updateValidTime(cookies, response, ValidTime, id, password);//cookie 内容保存 2 周
    }

    /**
     * 将 cookie 中的用户名和密码清除
     * @param request
     * @param response
     */
    public void clearCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0)return;
        updateValidTime(cookies, response, 0, id, password);//清除 cookie 内容
    }

    /**
     * 从 Cookie中获取Member对象的 ID 和密码
     * @param request
     * @return 含有 ID 和密码的 Member 对象
     */
    public Member getMemberIDAndPasswordFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0)return null;
        Member member = new Member();
        for(Cookie cookie: cookies){
            String name = cookie.getName();
            if(id.equals(name))
                member.setID(cookie.getValue());
            else if(password.equals(name))
                member.setPassword(cookie.getValue());
        }
        return member;
    }

    /**
     * 更新 cookie 的有效期，此方法不可为 public
     * 使用此方法前应先检查 cookies 不为 null 或 length == 0
     * @param cookies
     * @param validTime cookie 有效期, 单位秒
     * @param cookieNames 需要设定有效期的 cookie 的名称
     */
    private void updateValidTime(Cookie[] cookies, HttpServletResponse response, int validTime, String... cookieNames){
        for(Cookie cookie: cookies)
            for(String attr: cookieNames)
                if(attr.equals(cookie.getName())){
                    cookie.setMaxAge(validTime);//更新指定 cookie 的有效时间为从现在开始2周
                    response.addCookie(cookie);
                    break;
                }
    }
}
