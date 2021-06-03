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
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 重载一个含有入参信息args的getBean方法，这样就可以方便地传递入参给构造函数实例化了
     *
     * @param beanName
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName, Object... args) throws BeansException;
}
