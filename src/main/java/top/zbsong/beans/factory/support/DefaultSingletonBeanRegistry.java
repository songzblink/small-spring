package top.zbsong.beans.factory.support;

import top.zbsong.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By songzb on 2021/5/24
 *
 * @author songzb
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册bean对象
     * protected修饰，此方法可以被继承此类的其它类调用，如AbstractBeanFactory以及DefaultListableBeanFactory 调用。
     *
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
