package com.yangyang.seckill.redis;

/**
 * 秒杀用户模块缓存key的定义
 */
public class SeckillUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    private SeckillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE,"token");
}