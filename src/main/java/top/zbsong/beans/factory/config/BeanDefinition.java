package top.zbsong.beans.factory.config;

/**
 * Create By songzb on 2021/5/24
 *
 * @author songzb
 */
public class BeanDefinition {
    // 利用Class，让Bean的实例化操作放到容器中处理
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
