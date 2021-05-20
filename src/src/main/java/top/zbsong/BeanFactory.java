package src.main.java.top.zbsong;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create By songzb on 2021/5/20
 * Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取。
 *
 * @author songzb
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * Bean 的获取
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     *  Bean 的注册
     * @param name
     * @param beanDefinition
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
