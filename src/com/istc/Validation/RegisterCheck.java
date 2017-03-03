package com.istc.Validation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.regex.Pattern;

/**
 * Created by lurui on 2017/3/3 0003.
 */

/**
 * 本类实现了注册相关数据的后端验证
 */
public class RegisterCheck {

    private static RegisterCheck self;
    private static EnumMap<Type, Method> typeMethodEnumMap;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final Pattern ChinaPhoneRegex = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
    private static final Pattern HongKongPhoneRegex = Pattern.compile("^(5|6|8|9)\\d{7}$");
    private static final Pattern IDRegex = Pattern.compile("[2-4]\\d{9}");
    private static final Pattern NAMERegex = Pattern.compile("[\\u4e00-\\u9fa5a-zA-Z]{2,20}");
    private static final Pattern PASSWORDRegex = Pattern.compile("[a-zA-Z\\d_]{6,30}");
    private static final Pattern QQRegex = Pattern.compile("[1-9]\\d{4,15}");

    /**
     * 使用 枚举 来限制调用时的常量选择
     */
    public enum Type{
        ID, BIRTHDAY, NAME, PASSWORD, QQ, PHONE_NUMBER
    }

    /**
     * 双重校验锁
     * @return
     */
    public static RegisterCheck getInstance(){
        if(self == null)
            synchronized (RegisterCheck.class){
                if(self == null) {
                    self = new RegisterCheck();
                    Class clazz = RegisterCheck.class;
                    try {
                        for(Type t: Type.values())
                            typeMethodEnumMap.put(t, clazz.getDeclaredMethod(t + "Check", String.class));
                    } catch (NoSuchMethodException e) {
                    }
                }
            }
        return self;
    }

    public boolean isValid(Type type, String value){
        if(value == null || value.equals("")) return false;
        Method method = typeMethodEnumMap.get(type);
        method.setAccessible(true);//设置 private 方法可访问
        try {
        return (Boolean)method.invoke(self, value);//利用反射调用单例的相应检查方法
        } catch (IllegalAccessException|InvocationTargetException e) {
            System.out.print("invoke 失败");
        }
        return false;
    }

    private boolean IDCheck(String id){
        //此处需要参考学号规则, 目前已知：
        //共10位
//        第一位 2本科生 3研究生 4博士生 1大概是教职工
//        二三位 入学年份
//        四五位 学院代码
//        六七位 专业代码
//        最后三位 从001开始排的
        return IDRegex.matcher(id).matches();
    }

    private boolean BIRTHDAYCheck(String birthday){ // 传入的日期指定为 yyyy-MM-dd格式
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        try {
            birth.setTime(simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            return false;
        }// 学校少年班最小14岁，出生年份应在两者之间
        return birth.get(Calendar.YEAR) >= 1970 && birth.get(Calendar.YEAR) <= now.get(Calendar.YEAR) - 14;
    }

    private boolean PASSWORDCheck(String password){
        return PASSWORDRegex.matcher(password).matches();// 以字母或者开头，并且可以在后面使用下划线
    }

    private boolean NAMECheck(String name){
        return NAMERegex.matcher(name).matches();//姓名支持中英文混合，由于是字符串中，因此unicode转义用双斜线，否则报告：非法的unicode转义错误
    }

    private boolean QQCheck(String qq){
        return QQRegex.matcher(qq).matches();
    }

    private boolean PHONE_NUMBERCheck(String phoneNumber){
        return ChinaPhoneRegex.matcher(phoneNumber).matches() || HongKongPhoneRegex.matcher(phoneNumber).matches();
    }

}
