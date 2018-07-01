package com.yangyang.seckill.redis;

/**
 * 订单模块缓存key的定义
 */
public class OrderKey extends BasePrefix{
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
