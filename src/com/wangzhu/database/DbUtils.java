package com.wangzhu.database;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleCallableStatement;

import com.wangzhu.string.StringUtils;

/**
 * 使用枚举形成单例
 * 
 * @author wangzhu
 * @date 2014-11-6下午4:39:43
 * 
 */
public enum DbUtils {
    INSTANCE;
    /**
	 * 
	 */
    private static final String JDBC_FILE = "jdbc.properties";
    /**
     * 数据库连接对象
     */
    private Connection conn;

    /**
     * 初始化
     */
    private DbUtils() {
	try {
	    PropertyResourceBundle bundle = new PropertyResourceBundle(
		    DbUtils.class.getResourceAsStream(DbUtils.JDBC_FILE));
	    String driver = bundle.getString("driver");
	    String url = bundle.getString("url");
	    String user = bundle.getString("user");
	    String password = bundle.getString("password");
	    Class.forName(driver);
	    conn = DriverManager.getConnection(url, user, password);
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 使用预处理数据库查询
     * 
     * @param sql
     *            查询语句
     * @param parameterValueArr
     *            绑定变量的值数组
     * @return 查询结果
     */
    public List<Map<String, Object>> query(String sql,
	    Object[] parameterValueArr) {
	List<Map<String, Object>> rets = new ArrayList<Map<String, Object>>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
	    ps = conn.prepareStatement(sql);
	    if ((null != parameterValueArr) && (parameterValueArr.length > 0)) {
		for (int i = 0, len = parameterValueArr.length; i < len; i++) {
		    ps.setObject(i + 1, parameterValueArr[i]);
		}
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
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return rets;
    }

    /**
     * 调用存储过程
     * 
     * @param sql
     * @return
     */
    public List<Map<String, Object>> callProcedure(String sql) {
	List<Map<String, Object>> rets = new ArrayList<Map<String, Object>>();
	CallableStatement cstmt = null;
	try {
	    cstmt = conn.prepareCall(sql);
	    cstmt.registerOutParameter(1, Types.FLOAT);
	    cstmt.registerOutParameter(2, Types.CHAR);
	    cstmt.registerOutParameter(3, OracleTypes.CURSOR);
	    cstmt.executeUpdate();
	    int retCode = cstmt.getInt(1);
	    String retMsg = cstmt.getString(2);
	    if (retCode == -1) {
		System.out.println(retMsg);
	    } else {
		OracleCallableStatement ocst = (OracleCallableStatement) cstmt;
		ResultSet rs = ocst.getCursor(3);
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
		    Map<String, Object> rowMap = new LinkedHashMap<String, Object>();
		    for (int i = 1, count = rsmd.getColumnCount() + 1; i < count; i++) {
			rowMap.put(StringUtils.underlineToCamel(rsmd
				.getColumnName(i)), rs.getObject(i));
		    }
		    rets.add(rowMap);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (cstmt != null) {
		try {
		    cstmt.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return rets;
    }
}
