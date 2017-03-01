package com.istc.Utilities;

import org.apache.struts2.dispatcher.SessionMap;

/**
 * Created by lurui on 2017/3/1 0001.
 */
public class TokenUtils {
    private static TokenUtils self;
    private TokenUtils(){}

    private static final String tokenKey = "token";
    /**
     * 双重校验锁
     * @return
     */
    public static TokenUtils getInstance(){
        if(self == null)
            synchronized (TokenUtils.class){
                if(self == null)
                    self = new TokenUtils();
            }
        return self;
    }

    /**
     * t检查表单是否重复提交
     * 如果 Session 中 token 值与传入 token 不同则说明是重复提交
     * @param session 当前会话
     * @param token 当前的 token
     * @return true 如果 session 或 token 为 null
     * @return true 如果 session 中的 token 与传入的 token 不相等
     * @return false 如果 session 和 token 均不为空且 session 中的 token 与传入的 token 相等
     */
    public boolean isResubmit(SessionMap<String, Object> session, String token){
        return session == null || token == null ? true : !token.equals((String)session.get(tokenKey));
    }

    public String generateNewToken(){
        String digist = String.valueOf(Math.random());
        return Encoder.getInstance().encodeSHA512(digist.getBytes());
    }
}
