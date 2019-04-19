package util;

import java.util.UUID;

/**
 * @author 高山潍
 */
public class UuidTaskId {
    // 自动生成32位的UUid，对应数据库的主键id进行插入用
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
