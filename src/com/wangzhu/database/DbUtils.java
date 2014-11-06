package com.wangzhu.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

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
			this.conn = DriverManager.getConnection(url, user, password);
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
			ps = this.conn.prepareStatement(sql);
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
					rowMap.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				rets.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rets;
	}
}
