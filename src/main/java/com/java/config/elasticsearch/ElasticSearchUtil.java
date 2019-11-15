package com.java.config.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * ElasticSearch相关操作类
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/26 11:58
 */
@Component
public class ElasticSearchUtil {

    @Autowired
    private JestClient jestClient;

    /**
     * 添加索引
     *
     * @param index 索引 --类似数据库
     * @param type  类型  --类似表
     * @param obj   文档
     */
    public void index(String index, String type, Object obj) {
        // 构建一个索引
        Index idx = new Index.Builder(obj).index(index).type(type).build();
        try {
            jestClient.execute(idx);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索
     *
     * @param index 索引 --类似数据库
     * @param type  类型  --类似表
     * @param json  查询表达式json
     */
    public String search(String index, String type, String json) {
        // 构建一个搜索
        Search search = new Search.Builder(json).addIndex(index).addType(type).build();
        try {
            SearchResult result = jestClient.execute(search);
            return result.getJsonString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
