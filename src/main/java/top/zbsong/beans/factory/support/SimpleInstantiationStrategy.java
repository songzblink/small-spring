package top.zbsong.beans.factory.support;

import top.zbsong.beans.BeansException;
import top.zbsong.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Create By songzb on 2021/6/3
 * 反射机制实例化
 * @author songzb
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,
                              Object[] args) throws BeansException {
        // 通过beanDefinition获取Class信息，这个Class信息是在Bean定义的时候传递进去的
        Class clazz = beanDefinition.getBeanClass();
        try {
            // 如果指定了构造器【需要有参构造函数的实例化】
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 无参构造实例化
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instance [" + clazz.getName() + "]", e);
        }
    }
}
