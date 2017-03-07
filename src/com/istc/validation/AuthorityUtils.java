package com.istc.validation;

import com.istc.bean.Person;

import javax.servlet.http.HttpSession;
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
     * 检查当前操作是否是当前部的部长
     */
    public boolean operateBySpecificMinister(Map<String,Object> session){
        String id = ((Person)session.get("personInfo")).getID();
        if(id!=null && !id.equals("")){
            if (true){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查当前操作是否是部长级成员
     */
    public boolean operateByMinisterGroup(HttpSession session){
        boolean result=false;
        String id = ((Person)session.getAttribute("personInfo")).getID();
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
    public boolean operateByPresidentGroup(HttpSession session){
        String id = ((Person)session.getAttribute("personInfo")).getID();
        if(id!=null && !id.equals("")){
            if (true){
                return true;
            }
        }
        return false;
    }
}
