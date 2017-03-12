import com.istc.Entities.Entity.Member;
import org.junit.jupiter.api.Test;

/**
 * Created by vicky on 2017/3/12.
 */
public class convert_test {

    @Test
    public void test(){
        try {
            Object target = Class.forName(Member.class.getName()).newInstance();
            Member jim=(Member)target;
            System.out.println("jim.getClass()==Member.class");
            System.out.println(jim.getClass()==Member.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
