package com.wangzhu.database;

import java.util.List;
import java.util.Map;

public interface IDbHelpers {

    /**
     * ʹ��Ԥ�������ݿ��ѯ
     * 
     * @param sql
     *            ��ѯ���
     * @param paramList
     *            �󶨱�����ֵ�б�
     * @return ��ѯ���
     */
    public List<Map<String, Object>> query(String sql, List<Object> paramList);

}
