package top.zbsong.beans.factory;

import top.zbsong.beans.BeansException;

/**
 * Create By songzb on 2021/5/24
 *
 * @author songzb
 */
public interface BeanFactory {
    /**
     * 获取bean实例
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;
}
