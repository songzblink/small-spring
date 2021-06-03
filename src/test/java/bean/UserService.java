package bean;

/**
 * Create By songzb on 2021/5/20
 *
 * @author songzb
 */
public class UserService {
    private String info;

    public UserService(String info) {
        this.info = info;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息:" + info);
    }

    @Override
    public String toString() {
        return "UserService{" + "info='" + info + '\'' + '}';
    }
}
