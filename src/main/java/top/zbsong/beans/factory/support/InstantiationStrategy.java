package top.zbsong.beans.factory.support;

import top.zbsong.beans.BeansException;
import top.zbsong.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Create By songzb on 2021/6/3
 * 实例化策略接口
 *
 * @author songzb
 */
public interface InstantiationStrategy {
    /**
     *
     * @param beanDefinition
     * @param beanName
     * @param ctor java.lang.reflect包下的Constructor类，包含了一些必要的类信息【这个参数可以拿到符合入参信息相对应的构造函数】
     * @param args 具体的入参信息
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,
                       Object[] args) throws BeansException;
}
