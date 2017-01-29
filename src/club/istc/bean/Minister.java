package club.istc.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lurui on 2016/11/21 0021.
 */
//@Entity
public class Minister extends Member implements Serializable{
    //@Id
    private Integer deptID;
    //@Id
    private String ministerID;
    Minister(){
        this.ministerID = super.ID;
    }
}
