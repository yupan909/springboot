package com.java.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * redis操作类
 * <p>
 * Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)
 * （1）string 是 redis 最基本的类型，一个 key 对应一个 value，string 类型的值最大能存储 512MB；
 * （2）hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象；
 * （3）list 是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）；
 * （4）Set 是string类型的无序集合，集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)；
 * （5）zset 和 set 一样也是string类型元素的集合,且不允许重复的成员，不同的是每个元素都会关联一个double类型的分数，
 * redis正是通过分数来为集合中的成员进行从小到大的排序，zset的成员是唯一的,但分数(score)却可以重复。
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/6 10:45
 */
@Component
public class RedisService<T> {

    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();
    private static final Jackson2JsonRedisSerializer VALUE_SERIALIZER = new Jackson2JsonRedisSerializer(Object.class);

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOps;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 30;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    @PostConstruct
    public void init() {
        // 设置序列化
        redisTemplate.setKeySerializer(STRING_SERIALIZER);
        redisTemplate.setHashKeySerializer(STRING_SERIALIZER);
        redisTemplate.setValueSerializer(VALUE_SERIALIZER);
        redisTemplate.setHashValueSerializer(VALUE_SERIALIZER);
    }

    /**
     * 检查给定key是否存在
     *
     * @param key
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除一个或者多个key
     *
     * @param keys
     */
    public Long del(String... keys) {
        Set<String> kSet = Stream.of(keys).map(String::valueOf).collect(Collectors.toSet());
        return redisTemplate.delete(kSet);
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param time 单位：秒
     */
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 设置key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 返回key的剩余过期时间
     *
     * @param key
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 移除给定key的过期时间，使得key永不过期
     *
     * @param key
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 修改key的名称
     *
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    //============================String=============================

    /**
     * 获取指定key的值
     *
     * @param key
     * @return
     */
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置指定key的值
     *
     * @param key
     * @param t
     */
    public void set(String key, T t) {
        redisTemplate.opsForValue().set(key, t);
    }

    /**
     * 为指定的key设置值及其过期时间
     *
     * @param key
     * @param t
     * @param time 过期时间：秒
     */
    public void setEx(String key, T t, long time) {
        redisTemplate.opsForValue().set(key, t, time, TimeUnit.SECONDS);
    }

    /**
     * 获取所有(一个或多个)给定key的值
     *
     * @param keys
     * @return
     */
    public List<T> mget(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 同时设置一个或多个key-value对
     *
     * @param map
     */
    public void mset(Map<String, T> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    //================================Hash=================================

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key   键
     * @param field 字段
     * @return
     */
    public Object hget(String key, Object field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 将哈希表key中的字段field的值设为value
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     * @return
     */
    public void hset(String key, Object field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key    键
     * @param fields 多个字段
     * @return 对应的多个键值
     */
    public List<Object> hmget(String key, List<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表key中
     *
     * @param key 键
     * @param map 对应多个键值
     * @return
     */
    public void hmset(String key, Map<Object, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表key中，同时设置过期时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return
     */
    public void hmset(String key, Map<Object, Object> map, long time) {
        try {
            hmset(key, map);
            if (time > 0) {
                expire(key, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hgetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key   键
     * @param field 字段
     */
    public void hdel(String key, Object... field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 查看哈希表key中，指定的字段是否存在
     *
     * @param key   键
     * @param field 字段
     * @return true 存在 false不存在
     */
    public Boolean hexists(String key, Object field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    //===============================List=================================

    /**
     * 将一个或多个值插入到列表头部(最左边)
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void lpush(String key, T... value) {
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key 键
     * @return
     */
    public T lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 将一个或多个值插入到列表的尾部(最右边)
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void rpush(String key, T... value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 移除列表的最后一个元素
     *
     * @param key 键
     * @return
     */
    public T rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 通过索引来设置元素的值
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public void lset(String key, long index, T value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 通过索引获取列表中的元素
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public T lindex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<T> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取列表长度
     *
     * @param key 键
     * @return
     */
    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 移除列表元素，移除列表中与参数 VALUE 相等的元素
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long lrem(String key, long count, T value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    //============================Set=============================

    /**
     * 返回集合中的所有成员
     *
     * @param key 键
     * @return
     */
    public Set<T> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 向集合添加一个或多个成员
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sadd(String key, T... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 向集合添加一个或多个成员，同时设置过期时间
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sadd(String key, long time, T... values) {
        Long count = sadd(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 判断元素是否是集合key的成员
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean sismember(String key, T value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取集合的成员数
     *
     * @param key 键
     * @return
     */
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除集合中一个或多个成员
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long srem(String key, T... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }
}
