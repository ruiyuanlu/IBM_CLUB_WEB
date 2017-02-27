package com.istc.validation;

/**
 * Created by Morn Wu on 2017/2/25.
 * 这是一个包含编辑cookie编辑工具的工具类
 * struts有自带的cookie拦截器，这里是为了增加灵活性，所以进行了自定义
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.istc.bean.Person;

/**
 * cookie的增加、删除、查询
 */
public class CookieUtils{

    public CookieUtils(){

    }


    public static HttpServletResponse generateCookie(Person p,HttpServletResponse response) {
        Cookie[] cookies=new Cookie[2];
        cookies[0] = new Cookie("ID",p.getID());
        cookies[1] = new Cookie("password",p.getPassword());
        //System.out.println("添加cookie");
        cookies[0].setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
        cookies[1].setMaxAge(60 * 60 * 24 * 14);
        response.addCookie(cookies[0]);
        response.addCookie(cookies[1]);
        return response;
    }



    // 得到cookie
    public static boolean checkCookie(HttpServletRequest request) {
        Person personincookie=CookieUtils.getPersonInCookie(request);
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


    public static Person getPersonInCookie(HttpServletRequest request){
        Person personincookie=new Person();
        Cookie[] cookies = request.getCookies();
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
        return personincookie;
    }

    public static HttpServletResponse clearCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ID".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if ("password".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return response;
    }

    public static HttpServletResponse updateCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ID".equals(cookie.getName())) {
                    cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
                    response.addCookie(cookie);
                }
                if ("password".equals(cookie.getName())){
                    cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
                    response.addCookie(cookie);
                }
            }
        }
        return response;
    }

    public static Cookie[] updateCookie(HttpServletRequest request){
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
}
