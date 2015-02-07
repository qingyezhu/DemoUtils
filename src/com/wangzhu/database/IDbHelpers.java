package com.wangzhu.database;

import java.util.List;
import java.util.Map;

public interface IDbHelpers {

    /**
     * 使用预处理数据库查询
     * 
     * @param sql
     *            查询语句
     * @param paramList
     *            绑定变量的值列表
     * @return 查询结果
     */
    public List<Map<String, Object>> query(String sql, List<Object> paramList);

}
