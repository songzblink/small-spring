package top.zbsong.beans.factory.support;

import top.zbsong.beans.factory.config.BeanDefinition;

/**
 * Create By songzb on 2021/5/24
 *
 * @author songzb
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
