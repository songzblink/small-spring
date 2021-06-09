package top.zbsong.beans.factory.config;

/**
 * Create By songzb on 2021/6/9
 *
 * @author songzb
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
