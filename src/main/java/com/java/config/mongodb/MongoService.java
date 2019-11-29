package com.java.config.mongodb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * mongodb操作类
 *
 * @author yupan@yijiupi.cn
 * @date 2019-11-21 17:19
 */
@Component
public class MongoService<T>{

    @Autowired
    private MongoTemplate mongoTemplate;

    private final static String ID = "id";

    /**
     * 单个新增
     * @param t 待插入的对象，注意必须有id字段
     */
    public void insert(T t) {
        mongoTemplate.insert(t);
    }

    /**
     * 批量新增
     * @param t 待插入的对象集合，注意不要包含重复的id
     */
    public void insert(List<T> t) {
        mongoTemplate.insertAll(t);
    }

    /**
     * 根据id更新对象
     */
    public void updateById(String id, T t) {
        Query query = new Query(Criteria.where(ID).is(id));
        Update update = new Update();
        String str = JSON.toJSONString(t);
        JSONObject jQuery = JSON.parseObject(str);
        jQuery.forEach((key, value) -> {
            // 排除id主键
            if (!Objects.equals(key, ID)) {
                update.set(key, value);
            }
        });
        mongoTemplate.updateMulti(query, update, t.getClass());
    }

    /**
     * 根据id删除对象
     */
    public void deleteById(String id, Class<T> clazz) {
        Query query = new Query(Criteria.where(ID).is(id));
        mongoTemplate.remove(query, clazz);
    }

    /**
     * 查询单个
     * @return
     */
    public T getById(String id, Class<T> clazz) {
        return mongoTemplate.findById(id, clazz);
    }

    /**
     * 分页查询列表
     * @return
     */
    public List<T> list(T t, Integer pageNum, Integer pageSize, Class<T> clazz) {
        Query query = new Query();
        // 分页
        if (!ObjectUtils.isEmpty(pageNum) && !ObjectUtils.isEmpty(pageNum)) {
            query.skip(pageSize * (pageNum - 1));
            query.limit(pageSize);
        }
        // 条件查询
        if (t != null) {
            String str = JSON.toJSONString(t);
            JSONObject jQuery = JSON.parseObject(str);
            jQuery.forEach((key, value) -> {
                // 排除id主键
                if (!Objects.equals(key, ID)) {
                    query.addCriteria(Criteria.where(key).is(value));
                }
            });
        }
        return mongoTemplate.find(query, clazz);
    }
}
