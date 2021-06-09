import bean.UserDao;
import bean.UserService;
import org.junit.Test;
import top.zbsong.beans.PropertyValue;
import top.zbsong.beans.PropertyValues;
import top.zbsong.beans.factory.config.BeanDefinition;
import top.zbsong.beans.factory.config.BeanReference;
import top.zbsong.beans.factory.support.DefaultListableBeanFactory;


/**
 * Create By songzb on 2021/5/20
 *
 * @author songzb
 */
public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.UserDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        // 3.UserService 设置属性【uId、userDao】
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        // 4.UerService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 5.UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
