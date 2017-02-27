package com.istc.validation;

/**
 * Created by Morn Wu on 2017/2/25.
 * 这是一个cookie拦截器
 * struts有自带的cookie拦截器，这里是为了增加灵活性，所以用自定义的cookie拦截器
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.istc.bean.Person;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * cookie的增加、删除、查询
 */
public class CookieUtils{
    public static final String USER_COOKIE = "cookie";
    private Cookie[] cookies;
    private  Person personincookie=new Person();

    public CookieUtils(HttpServletRequest request){
        getCookieParameter(request);
    }

    private void getCookieParameter(HttpServletRequest request){
        cookies = request.getCookies();
        System.out.println("cookies已获取: " + cookies);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookie: " + cookie.getName());
                if ("ID".equals(cookie.getName())) {
                    System.out.println(cookie.getValue());
                    personincookie.setID(cookie.getValue());
                }
                if ("password".equals(cookie.getName())){
                    personincookie.setPassword(cookie.getValue());
                    System.out.println(cookie.getValue());
                }
            }
        }
    }


    public Cookie[] generateCookie(String ID,String password) {
        Cookie[] cookies=new Cookie[2];
        cookies[0] = new Cookie("ID",ID);
        cookies[1] = new Cookie("password",password);
        //System.out.println("添加cookie");
        cookies[0].setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
        cookies[1].setMaxAge(60 * 60 * 24 * 14);
        return cookies;
    }



    // 得到cookie
    public boolean checkCookie() {
        try {
            if (personincookie.getID().equals("2141601033")){
                if (personincookie.getPassword().equals(Crypto.toSHA1("456789"))){
                    return true;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public Cookie[] clearCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ID".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                }
                if ("password".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                }
            }
        }
        return cookies;
    }

    public Cookie[] updateCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ID".equals(cookie.getName())) {
                    cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
                }
                if ("password".equals(cookie.getName())){
                    cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
                }
            }
        }
        return cookies;
    }

    public Cookie[] getCookies(){
        return this.cookies;
    }

    public Person getPersonincookie() { return this.personincookie; }
}
