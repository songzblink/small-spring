手撸 Spring，跟着虫洞栈学。

## 1.实现一个简单的 Bean 容器

### 目标

Spring 容器是对象的容器，我们可以配置每个 Bean 对象是如何被创建的，以及它们是如何相互关联创建和使用的。

 Bean 对象会以类似零件的方式被拆解后存放到 Bean 的定义中，当一个 Bean 对象被定义存放以后，再由 Spring 统一进行装配，这个过程包括 Bean 的初始化、属性填充等，最终我们就可以完整的使用一个 Bean 实例化后的对象了。

我们当前的目标是实现一个简单的 Spring 容器，用于定义、存放和获取 Bean 对象。

### 设计

凡是可以存放数据的具体数据结构实现，都可以称之为容器。比如 ArrayList、LinkedList、HashSet 等，但是在 Spring Bean 容器的场景下，我们需要一种名称索引式的数据结构，所以 HashMap 比较合适。

一个简单的 Bean 容器，需要实现 Bean 的定义、注册和获取三个基本步骤。

![](https://bugstack.cn/assets/images/spring/spring-2-01.png)

- 定义：BeanDefinition，我们定义的各个 Bean 会转换成一个个的 BeanDefinition 存在于 Spring 的 BeanFactory 中。BeanDefinition 保存了 Bean 的信息，比如这个 Bean 指向的是哪个类、是否是单例的、是否懒加载、这个 Bean 以来了哪些 Bean 等等。我们初步的实现会更加简单，只定义一个 Object 类型用于存放对象。
- 注册：这个过程相当于把数据存放到 HashMap 中，只不过现在 HashMap 存放的式定义了的 Bean 的对象信息。
- 获取：获取对象，Bean 的名字就是 key，Spring 容器初始化好 Bean 以后，就可以直接获取了。

### 代码

见仓库：[small-spring/small-spring-step-01: Spring Bean 容器创建 (github.com)](https://github.com/small-spring/small-spring-step-01)

### 总结

目前 Spring Bean 容器的一个雏形已经实现完成了。之后会在此基础上进行扩充。

来源：[《Spring 手撸专栏》第 2 章：小试牛刀，实现一个简单的Bean容器 - bugstack虫洞栈](https://bugstack.cn/spring/2021/05/20/第2章-小试牛刀-实现一个简单的Bean容器.html)

## 2.实现 Bean 的定义、注册、获取

### 目标

上一个章节初步依照 Spring Bean 容器的概念，实现了一个粗糙版本的代码实现。本章节对其功能进行完善，实现 Bean 容器关于 Bean 对象的注册和获取。

### 设计

首先，Bean 注册的时候只注册一个类信息，而不会直接把实例化信息注册到 Spring 容器中。那么就需要修改 BeanDefinition 中的属性 Object 为 Class，接下来再需要做的就是在获取 Bean 对象时需要处理 Bean 对象的实例化操作，以及判断当前单例对象在容器中是否已经缓存起来了。

![](https://bugstack.cn/assets/images/spring/spring-3-01.png)

- 使用模板设计模式，统一通用核心方法的调用逻辑和标准定义，能够控制后续的实现者不用关心调用逻辑，按照统一的方式执行。类的继承者只需要关心具体方法的逻辑实现即可。定义 BeanFactory 这样一个 Bean 工厂，提供 Bean 的获取方法 `getBean(String name)`，这个 Bean 工厂接口由抽象类 AbstractBeanFactory 实现。
- 那么在继承抽象类 AbstractBeanFactory 后的 AbstractAutowireCapableBeanFactory 就可以实现相应的抽象方法了，因为 AbstractAutowireCapableBeanFactory 本身也是一个抽象类，所以它只会实现属于自己的抽象方法，其他抽象方法由继承 AbstractAutowireCapableBeanFactory 的类实现。这里就体现了类实现过程中的各司其职，你只需要关心属于你的内容，不是你的内容，不要参与。
- 另外这里还有块非常重要的知识点，就是关于单例 SingletonBeanRegistry 的接口定义实现，而 DefaultSingletonBeanRegistry 对接口实现后，会被抽象类 AbstractBeanFactory 继承。现在 AbstractBeanFactory 就是一个非常完整且强大的抽象类了，也能非常好的体现出它对模板模式的抽象定义。【这里不太理解】

### 代码

见仓库：[small-spring/small-spring-step-02: Spring Bean 的定义和注册 (github.com)](https://github.com/small-spring/small-spring-step-02)

Spring Bean 容器类关系

![](https://bugstack.cn/assets/images/spring/spring-3-02.png)

虽然和源码还有一定的差距，但是目前已经具备了一定的设计复杂性。这些复杂的类关系设计在各个接口定义和实现以及在抽象类继承中都有所体现，例如：

- BeanFactory 接口的定义 getBean 方法由 AbstractBeanFactory 抽象类实现。
- 而 AbstractBeanFactory 又继承了实现了 SingletonBeanRegistry 的DefaultSingletonBeanRegistry 类。这样 AbstractBeanFactory 抽象类就具备了单例 Bean 的注册功能。
- AbstractBeanFactory 中又定义了两个抽象方法：getBeanDefinition(String beanName)、createBean(String beanName, BeanDefinition beanDefinition) ，而这两个抽象方法分别由 DefaultListableBeanFactory、AbstractAutowireCapableBeanFactory 实现。
- 最终 DefaultListableBeanFactory 还会继承抽象类 AbstractAutowireCapableBeanFactory 也就可以调用抽象类中的 createBean 方法了。

### 总结

在 Spring Bean 容器的实现类中要重点关注类之间的职责和关系，几乎所有的程序功能设计都离不开接口、抽象类、实现、继承，而这些不同特性类的使用就可以非常好的隔离开类的功能职责和作用范围。

来源：[《Spring 手撸专栏》第 3 章：初显身手，运用设计模式，实现 Bean 的定义、注册、获取 - bugstack虫洞栈](https://bugstack.cn/spring/2021/05/23/第3章-初显身手-运用设计模式-实现-Bean-的定义-注册-获取.html)

## 3.基于Cglib实现含有参构造函数的类实例化策略

### 目标

在上一个章节我们扩充了 Bean 容器的功能，把实例化对象交给容器来统一处理，但是在实例化对象的代码里并没有考虑对象类是否含有参构造函数，也就是说如果我们去实例化一个有有参构造函数的对象就会抛异常。

验证：把 UserService 添加一个含入参信息的构造函数，如下：

```java
public class UserService {
    private String name;
    public UserService(String name) {
        this.name = name;
    }  
    // ...
}
```

报错如下：

```java
java.lang.InstantiationException: bean.UserService
	at java.base/java.lang.Class.newInstance(Class.java:598)
	at top.zbsong.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:17)
	at top.zbsong.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:24)
	at ApiTest.test_BeanFactory(ApiTest.java:21)
    ...
```

发生这一现象的主要原因就是因为在 AbstractAutowireCapableBeanFactory 类中的 `beanDefinition.getBeanClass().newInstance();` 实例化方式并没有考虑构造函数带参数的情况，那么现在我们要做的就是解决这个问题。

### 设计

如何解决这个问题主要考虑两部分，一个是串流程，从哪儿合理的把构造函数的入参信息传递到实例化操作中，另一个是怎么去实例化含有参构造函数的对象。

![](https://mmbiz.qpic.cn/sz_mmbiz_png/zTfAIs5rNXiaKafsTUIKlmjZrCkwvew5BJ3uWPic8CKF5dEzc59sxGZwfA32xgPkqVBCMIn1uUlk1rJTf3FYgKnQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

- 参考 Spring Bean 容器源码的实现方式，在 BeanFactory 中添加 `Object getBean(String name, Object... args)` 接口，这样就可以在获取 Bean 时把构造函数的入参信息传递进去了。
- 另外一个核心内容是使用什么方式来创建含有参构造函数的 Bean 对象呢？这里有两种方式可以选择，一个是基于 Java 本身自带的方法 `DeclaredConstructor`，另外一个是使用 Cglib 来动态创建 Bean 对象。

### 代码

![](https://mmbiz.qpic.cn/sz_mmbiz_png/zTfAIs5rNXiaKafsTUIKlmjZrCkwvew5BezTy0Cg4MzWXo3MUJxu40UibdX8yVHrybeD8aXewKgWiadcwp8vxu55g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



见仓库：[small-spring/small-spring-step-03: Spring Bean 构造函数实例化策略以及Cglib动态生成使用 (github.com)](https://github.com/small-spring/small-spring-step-03)

### 总结

完善了实例化操作，增加了 InstantiationStrategy 实例化策略接口，新增了两个实例化类。

从不断地完善增加需求可以看到，到代码结构设计的较为合理时，就可以非常容易方便地进行扩展不同属性地类职责，而不会因为需求地增加导致类结构混乱。所以在我们自己业务需求实现的过程中，也要尽可能的去考虑一个良好的扩展性以及拆分好类的职责。