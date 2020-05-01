package com.soft1851.music.admin.service;

/**
 * @ClassName RedisService
 * @Description RedisTemplate中key的默认的序列化方案是JdkSerializationRedisSerializer
 * StringRedisTemplate中，key的默认序列化方案StringRedisSerializer
 * @Author Yujie_Zhao
 * @Date 2020/4/21
 * @Version 1.0
 */
public interface RedisService {
    /**
     * 添加 Redis术语中 string 类型的数据
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value);

    /**
     * 添加 Redis术语中 string 类型的数据,并设置超时
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime);

    /**
     * 判断 key 是否存在
     *
     * @param key
     * @return
     */
    public boolean existsKey(final String key);

    /**
     * 根据( Redis 术语中 string 类型的) key 获取值,如果出现异常则返回null
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getValue(final String key, Class<T> type);

    /**
     * 删除对应的value
     *
     * @param key
     */

    public void removeKey(final String key);

    /**
     * 批量删除对应的value
     *
     * @param keys
     */

    public void remove(final String... keys);

    /**
     * 批量删除key
     *
     * @param pattern
     */

    public void removePattern(final String pattern);
}
