package com.java.config.redis;

import com.google.common.base.Strings;

/**
 * redis中key生成器
 * 规则（系统名:模块名:实体:Id...）
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/11 11:12
 */
public class RedisKeyGenerator {

    /**
     * 分割符
     */
    private static final String KEY_SPLIT = ":";

    /**
     * 生成key
     *
     * @param system 系统名
     * @param module 模块名
     * @param entity 实体
     * @param id     唯一标识
     * @param args   自定义
     * @return
     */
    public static String generator(RedisEnum system, RedisEnum module, String entity, String id, String... args) {
        StringBuilder key = new StringBuilder();
        key.append(system.getValue()).append(KEY_SPLIT).append(module.getValue());
        if (!Strings.isNullOrEmpty(entity)) {
            key.append(KEY_SPLIT).append(entity);
        }
        if (!Strings.isNullOrEmpty(id)) {
            key.append(KEY_SPLIT).append(id);
        }
        for (String s : args) {
            key.append(KEY_SPLIT).append(s);
        }
        return key.toString();
    }

}
