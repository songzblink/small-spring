import bean.UserService;
import org.junit.Test;
import src.main.java.top.zbsong.BeanDefinition;
import src.main.java.top.zbsong.BeanFactory;


/**
 * Create By songzb on 2021/5/20
 *
 * @author songzb
 */
public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
