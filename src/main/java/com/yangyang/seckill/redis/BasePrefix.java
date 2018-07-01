package com.yangyang.seckill.redis;

/**
 * 公共key的头部
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        // 默认0代表永不过期
        this(0,prefix);
    }
    /**
     * 构造器
     * @param expireSeconds
     * @param prefix
     */
    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {

        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        // 获取当前类名
        String className = getClass().getSimpleName();

        return className +":"+prefix;
    }
}
