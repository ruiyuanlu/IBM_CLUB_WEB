//package com.istc.Utilities.JsonUtils;
//
//import java.io.Serializable;
//
///**
// * Created by lurui on 2017/2/21 0021.
// */
//public class Json implements Serializable{
//    private String jsonString;
//
//    public String getJsonString() {
//        return jsonString;
//    }
//
//    public void setJsonString(String jsonString) {
//        this.jsonString = jsonString;
//    }
//
//    public Json() {
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Json)) return false;
//
//        Json that = (Json) o;
//
//        return jsonString != null ? jsonString.equals(that.jsonString) : that.jsonString == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        return jsonString != null ? jsonString.hashCode() : 0;
//    }
//}
