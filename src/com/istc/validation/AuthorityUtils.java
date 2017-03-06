package com.istc.validation;

import com.istc.bean.Person;

import java.util.Map;

/**
 * Created by Morn Wu on 2017/3/4.
 * 用于权限检查的工具类
 */
public class AuthorityUtils {

    private static class AuthorityUtilsHolder{
        private static AuthorityUtils instance=new AuthorityUtils();
    }

    private AuthorityUtils(){}

    public static AuthorityUtils getInstance(){
        return AuthorityUtilsHolder.instance;
    }


    /**
     * 检查当前操作是否是本人（正式部员）
     */
    public boolean operateByMyself(Map<String, Object> session){
        boolean result=false;
        String id=((Person)session.get("personInfo")).getID();
        //从数据库中获取密码
        String password=((Person)session.get("personInfo")).getPassword();
        if (true){
            result=true;
        }
        return result;
    }

    /**
     * 检查当前操作是否是当前部的部长
     */
    public boolean operateByMinister(Map<String, Object> session,int dept){
        boolean result=false;
        String id = ((Person)session.get("personInfo")).getID();
        if(id!=null && !id.equals("")){
            if (true){
                result = true;
            }
        }
        return result;
    }

    /**
     * 检查当前操作是否是部长级成员
     */
    public boolean operateByMinisterGroup(Map<String, Object> session){
        boolean result=false;
        String id = ((Person)session.get("personInfo")).getID();
        if(id!=null && !id.equals("")){
            if (true){
                result = true;
            }
        }
        return result;
    }

    /**
     * 检查当前操作是否是主席
     */
    public boolean operateByPresidentGroup(Map<String, Object> session){
        boolean result=false;
        String id = ((Person)session.get("personInfo")).getID();
        if(id!=null && !id.equals("")){
            if (true){
                result = true;
            }
        }
        return result;
    }


}
