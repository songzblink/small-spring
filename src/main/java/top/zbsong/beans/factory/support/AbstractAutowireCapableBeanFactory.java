package top.zbsong.beans.factory.support;

import top.zbsong.beans.BeansException;
import top.zbsong.beans.factory.config.BeanDefinition;

/**
 * Create By songzb on 2021/5/24
 *
 * @author songzb
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            // 通过反射实例化bean对象，【需要考虑有构造参数时的做法】
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        // 注册bean对象
        addSingleton(beanName, bean);
        return bean;
    }
}
