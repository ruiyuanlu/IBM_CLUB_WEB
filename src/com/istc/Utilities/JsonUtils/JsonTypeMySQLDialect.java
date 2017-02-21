//package com.istc.Utilities.JsonUtils;
//
//import org.hibernate.dialect.MySQL5Dialect;
//
//import java.sql.Types;
//
///**
// * Created by lurui on 2017/2/21 0021.
// */
//
///**
// * 注册 hibernate 定义 Json 的数据库中属性类型为Varchar，在hibernate层我们用自定义的类型名 json 与之对应
// * 这就是我们实现自己的 JsonUtils 到 Varchar 的转换
//*/
//public class JsonTypeMySQLDialect extends MySQL5Dialect {
//    public JsonTypeMySQLDialect() {
//        this.registerColumnType(Types.VARCHAR, "json");
//    }
//}
