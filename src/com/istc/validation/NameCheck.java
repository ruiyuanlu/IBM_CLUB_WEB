package com.istc.validation;

/**
 * Created by Morn Wu on 2017/3/1.
 */
public class NameCheck {
    String name;
    boolean result;

    public NameCheck(String name) {
        // TODO Auto-generated constructor stub
        this.name =name.trim();
        result= checkName();
    }

    /**
     * 检查姓名是否合法
     */
    private boolean checkName() {
        try {
            String regex = "[\\u4e00-\\u9fa5a-zA-Z]{2,20}";
            result = name.matches(regex);
        } catch (Exception e) {
            result=false;
        }
        return result;
    }

    public boolean getResult(){
        return result;
    }

}