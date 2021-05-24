package top.zbsong.beans.factory.support;

import top.zbsong.beans.BeansException;
import top.zbsong.beans.factory.BeanFactory;
import top.zbsong.beans.factory.config.BeanDefinition;

/**
 * Create By songzb on 2021/5/24
 * BeanDefinition 注册表接口
 *
 * @author songzb
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 获取单例Bean对象
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        // 获取不到时需要根据Bean的定义做相应Bean的实例化操作
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * bean实例化
     *
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 获取Bean的定义
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
