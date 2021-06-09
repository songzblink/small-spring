package bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By songzb on 2021/6/9
 *
 * @author songzb
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小明");
        hashMap.put("10002", "小红");
        hashMap.put("10003", "小刚");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
