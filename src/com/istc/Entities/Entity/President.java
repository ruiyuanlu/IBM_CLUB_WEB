package Entities.Entity;

import javax.persistence.*;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class President extends Member {

    private String title;

    public President() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
