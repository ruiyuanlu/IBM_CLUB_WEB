package club.istc.bean;

import javax.persistence.*;

/**
 * Created by lurui on 2016/11/21 0021.
 */
//@Entity
public class President extends Member{
    //@Column
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
