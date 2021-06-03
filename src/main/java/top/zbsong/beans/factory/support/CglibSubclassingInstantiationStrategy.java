package top.zbsong.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import top.zbsong.beans.BeansException;
import top.zbsong.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Create By songzb on 2021/6/3
 * Cglib动态代理实例化
 *
 * @author songzb
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,
                              Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        // 设置被代理类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置方法拦截器
        // 正常这里是传入一个自定义的 MethodInterceptor 方法拦截器去实现AOP
        // 这里不涉及AOP，为了简单起见这里是定义了一个空操作
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // 无参构造创建代理类
        if (null == ctor) {
            return enhancer.create();
        }
        // 有参构造创建代理类
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
