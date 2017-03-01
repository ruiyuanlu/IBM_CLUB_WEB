package Entities.Entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by lurui on 2017/2/25 0025.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Interview extends Person {
    Boolean passed;

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
