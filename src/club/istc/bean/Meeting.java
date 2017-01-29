package club.istc.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lurui on 2016/11/21 0021.
 */
//@Entity
public class Meeting implements Serializable{

    //@Column
    private String summary;
    //@Column
    private String location;
    //@Id
    private Integer times;
    //@Id
    private Integer deptID;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }
}
