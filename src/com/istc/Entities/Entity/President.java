package com.istc.Entities.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lurui on 2016/11/21 0021.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class President extends Member implements Serializable{

    private String title;

    public President() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString()+"President{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof President)) return false;
        if (!super.equals(o)) return false;

        President president = (President) o;

        return title != null ? title.equals(president.title) : president.title == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
