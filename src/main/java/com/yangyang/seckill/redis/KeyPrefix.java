package com.yangyang.seckill.redis;

/**
 * 缓存key的封装接口
 */
public interface KeyPrefix {

    /**
     * 缓存时间
     * @return
     */
     int expireSeconds();


     String getPrefix();
}
