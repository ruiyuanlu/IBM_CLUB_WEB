package com.istc.validation;

import java.util.Map;

/**
 * Created by Morn Wu on 2017/2/23.
 * 这是一个检查token是否匹配的类
 */
public class TokenCheck {

    public static boolean checkFormToken(Map<String,Object> session, String token) throws NullPointerException{
        String sessiontoken=(String)session.get("token");
        if (sessiontoken.equals(token)) return true;
        else return false;
    }

    public static String generateNewToken(){
        String a=""+Math.random();
        String result= Crypto.toSHA1(a);
        return result;
    }
}
