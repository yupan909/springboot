package com.java.config.redis;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * redis枚举类
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/11 12:02
 */
public enum RedisEnum {
    // 系统
    交易系统("trd"),
    CRM("crm"),
    供应链("scm"),

    // 模块
    订单("oms"),
    库存("wms"),
    配送("tms");

    private String value;

    RedisEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 根据枚举值获取对应名称
     *
     * @param value
     * @return
     */
    public static String getEnumName(String value) {
        return value == null ? null : cache.get(value);
    }

    private static final Map<String, String> cache = EnumSet.allOf(RedisEnum.class).stream()
            .collect(Collectors.toMap(p -> p.value, p -> p.name()));
}
