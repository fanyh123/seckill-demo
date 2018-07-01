package com.yangyang.seckill.redis;
/**
 * 用户模块缓存key的定义
 */
public class UserKey extends BasePrefix{

    /**
     * 用户信息设置永不过期
     * @param prefix
     */
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
