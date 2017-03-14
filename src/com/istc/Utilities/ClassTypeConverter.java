package com.istc.Utilities;

/**
 * Created by lurui on 2017/2/25 0025.
 */

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Stack;

/**
 * 此工具类实现了含有公共父类的子类对象之间的类型和数据自动转换
 * 即 含有公共父类的对象可以自动转换, 并保留从相同父类中
 * 继承的 field 的值
 */
public class ClassTypeConverter {
    private volatile static ClassTypeConverter self;
    //两个存储类型信息的栈
    private Stack<Class<?>> stackFrom = new Stack();
    private Stack<Class<?>> stackTarget = new Stack();
    private ClassTypeConverter(){
        stackFrom = new Stack();
        stackTarget = new Stack();
    }

    /**
     * 双重校验锁
     * @return
     */
    public static ClassTypeConverter getInstance(){
        if(self == null)
            synchronized(ClassTypeConverter.class){
                if(self == null)
                    self = new ClassTypeConverter();
            }
        return self;
    }

    /**
     * froms 不能为空 通过getClass() 方法可以获得目标元素的类型
     * 如 targetClazz 传入 target.getClass()
     * @param froms
     * @param targetClazz
     * @return 返回目标类型的对象数组
     * @throws Exception
     */
    public Object convert(List froms, Class<?> targetClazz)throws Exception{
        Object array = Array.newInstance(targetClazz, froms.size());
        for(int i = 0; i < froms.size(); i++){
            Object target = Class.forName(targetClazz.getName()).newInstance();
            convert(froms.get(i), target);
            Array.set(array, i, target);
        }
        return array;
    }

    /**
     * 将传入的对象从form类型转换为target类型
     * target 不能为 null
     * @param from
     * @param target
     * @throws Exception
     */
    public void convert(Object from, Object target) throws Exception{
        //清理内存并初始化
        clear(stackFrom, stackTarget);
        init(stackFrom, from);
        init(stackTarget, target);
        //判断类型是否相等，如果相等就转换类型并保留数据
        excuteConvert(from, target, stackFrom, stackTarget);
    }

    private void init(Stack stack, Object obj){
        for(Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
            stack.push(clazz);
    }

    private void excuteConvert(Object from, Object target, Stack<Class<?>> s1, Stack<Class<?>> s2) throws IllegalAccessException{
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
                if(!isFinal(fieldFrom.getModifiers())){
                    //不是final类型，则赋值
                    fieldFrom.setAccessible(true);
                    fieldTarget.setAccessible(true);
                    fieldTarget.set(target, fieldFrom.get(from));
                }
            }
        }
    }

    private static boolean isFinal(int modifier){
        return (modifier & Modifier.FINAL) == Modifier.FINAL;
    }

    private static void clear(Stack s1, Stack s2){
        s1.clear();
        s2.clear();
    }
}
