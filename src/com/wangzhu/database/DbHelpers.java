package com.wangzhu.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wangzhu.string.StringUtils;

public class DbHelpers implements IDbHelpers {
    private static final Logger log = Logger.getLogger(DbHelpers.class);

    @Override
    public List<Map<String, Object>> query(String sql, List<Object> paramList) {
	List<Map<String, Object>> rets = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
	    conn = DbManager.getConnection();
	    ps = conn.prepareStatement(sql);
	    if (paramList != null) {
		for (int i = 0, size = paramList.size(); i < size; i++) {
		    ps.setObject(i + 1, paramList.get(i));
		}
		paramList = null;
	    }
	    rs = ps.executeQuery();
	    ResultSetMetaData rsmd = rs.getMetaData();
	    while (rs.next()) {
		Map<String, Object> rowMap = new LinkedHashMap<String, Object>();
		for (int i = 1, count = rsmd.getColumnCount() + 1; i < count; i++) {
		    rowMap.put(
			    StringUtils.underlineToCamel(rsmd.getColumnName(i)),
			    rs.getObject(i));
		}
		rets.add(rowMap);
		rowMap = null;
	    }
	} catch (SQLException e) {
	    log.error(
		    "DbHelpers query(String sql, List<Object> paramList) ≤È—Ø”Ôæ‰£∫"
			    + sql + " “Ï≥££∫" + e.getMessage(), e);
	} finally {
	    DbManager.closeConnection(rs, ps, conn);
	}
	return rets;
    }
}
