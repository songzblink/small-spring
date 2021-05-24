package top.zbsong.beans.factory.config;

/**
 * Create By songzb on 2021/5/24
 * 单例注册表
 *
 * @author songzb
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
