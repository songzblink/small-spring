package top.zbsong.beans;

/**
 * Create By songzb on 2021/5/24
 * 定义 Bean 异常
 *
 * @author songzb
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
