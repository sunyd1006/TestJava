import com.sun.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test(){
        ApplicationContext contenxt = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = contenxt.getBean("userService", UserService.class);
        userService.getUser();
    }
}
