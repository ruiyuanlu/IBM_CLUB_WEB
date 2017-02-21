package com.istc.Utilities.JsonUtils;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/21 0021.
 */
public class JsonType implements Serializable{
    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public JsonType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonType)) return false;

        JsonType that = (JsonType) o;

        return jsonString != null ? jsonString.equals(that.jsonString) : that.jsonString == null;

    }

    @Override
    public int hashCode() {
        return jsonString != null ? jsonString.hashCode() : 0;
    }
}
