package com.istc.Utilities;

/**
 * Created by lurui on 2017/2/25 0025.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Stack;

/**
 * 此工具类实现了含有公共父类的子类对象之间的类型和数据自动转换
 * 即 含有公共父类的对象可以自动转换, 并保留从相同父类中
 * 继承的 field 的值
 */
public class ClassTypeConverter {
    public static ClassTypeConverter self;
    private ClassTypeConverter(){

    }

    public ClassTypeConverter getInstance(){
        return self == null ? self = new ClassTypeConverter() : self;
    }

    public void classTypeConvert (Object from, Object target) throws Exception{

        //两个存储类型信息的栈
        Stack<Class<?>> stackFrom = new Stack();
        Stack<Class<?>> stackTarget = new Stack();

        //初始化
        init(stackFrom, from);
        init(stackTarget, target);
        //判断类型是否相等，如果相等就转换类型并保留数据
        convert(from, target, stackFrom, stackTarget);
        //转换结束,清理内存
        clear(stackFrom, stackTarget);
    }

    private void init(Stack stack, Object obj){
        for(Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
            stack.push(clazz);
    }

    private void convert(Object from, Object target, Stack<Class<?>> s1, Stack<Class<?>> s2) throws IllegalAccessException{
        while( !s1.empty() && !s2.empty()){
            Class<?> clazz1 = s1.pop();
            Class<?> clazz2 = s2.pop();
            //类型不相等, 直接退出
            if( !clazz1.getSimpleName().equals(clazz2.getSimpleName()))return;
            //相等，获取当前属性，且属性的数目一定相同
            Field[] fields1 = clazz1.getDeclaredFields();
            Field[] fields2 = clazz2.getDeclaredFields();
            for(int i = 0;i < fields1.length; i++){
                Field fieldFrom = fields1[i];
                Field fieldTarget = fields2[i];
                Boolean is = isStaticOrFinal(fieldFrom.getModifiers());
                if(!isStaticOrFinal(fieldFrom.getModifiers())){
                    //不是静态或final，则赋值
                    fieldFrom.setAccessible(true);
                    fieldTarget.setAccessible(true);
                    fieldTarget.set(target, fieldFrom.get(from));
                }
            }
        }
    }

    private static boolean isStaticOrFinal (int modifier){
        return (modifier & Modifier.FINAL) == Modifier.FINAL || (modifier & Modifier.STATIC) == Modifier.STATIC;
    }

    private static void clear(Stack s1, Stack s2){
        s1.clear();
        s2.clear();
    }
}
