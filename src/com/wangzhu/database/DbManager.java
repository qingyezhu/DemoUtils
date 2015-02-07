package com.wangzhu.database;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * DriverManager：驱动管理器获得数据库连接<br/>
 * Connection：数据库连接接口<br/>
 * Statement：语句接口，用来静态操作SQL语句<br/>
 * PreparedStatement：预定义语句，用来动态操作SQL语句<br/>
 * ResultSetMetaData：结果集元数据，如列名称、列类型<br/>
 * ResultSet：结果集，保存数据记录的结果集合<br/>
 * ：<br/>
 * 
 * @author wangzhu
 * @date 2015-2-7下午1:46:56
 * 
 */
class DbManager {
    private static final Logger log = Logger.getLogger(DbManager.class);

    private static String driver;
    private static String url;
    private static String user;

    private static String password;

    static {
	Properties properties = new Properties();
	Reader reader = null;
	try {
	    reader = new FileReader("src\\config.properties");
	    properties.load(reader);
	} catch (IOException e) {
	    log.error("属性文件加载异常：" + e.getMessage(), e);
	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException e) {
		    log.error("属性文件读取异常：" + e.getMessage(), e);
		}
	    }
	}
	driver = properties.getProperty("driver");
	url = properties.getProperty("url");
	user = properties.getProperty("user");
	password = properties.getProperty("password");
    }

    /**
     * 获取数据库连接
     * 
     * @return
     */
    static Connection getConnection() {
	try {
	    Class.forName(driver);
	    return DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
	    log.error("数据库连接异常：" + e.getMessage(), e);
	}
	return null;
    }

    /**
     * 关闭数据库连接
     * 
     * @param conn
     *            数据库连接
     */
    static void closeConnection(Connection conn) {
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("数据库关闭异常：" + e.getMessage(), e);
	    }
	}
    }

    /**
     * 关闭静态语句接口、关闭数据库连接
     * 
     * @param statement
     *            静态语句接口
     * @param conn
     *            数据库连接
     */
    static void closeConnection(Statement statement, Connection conn) {
	if (statement != null) {
	    try {
		statement.close();
	    } catch (SQLException e) {
		log.error("静态语句接口关闭异常：" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("数据库关闭异常：" + e.getMessage(), e);
	    }
	}
    }

    /**
     * 关闭动态语句接口、关闭数据库连接
     * 
     * @param preparedStatement
     *            动态语句接口
     * @param conn
     *            数据库连接
     */
    static void closeConnection(PreparedStatement preparedStatement,
	    Connection conn) {
	if (preparedStatement != null) {
	    try {
		preparedStatement.close();
	    } catch (SQLException e) {
		log.error("动态语句接口关闭异常：" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("数据库关闭异常：" + e.getMessage(), e);
	    }
	}
    }

    /**
     * 关闭结果集、关闭静态语句接口、关闭数据库连接
     * 
     * @param rs
     *            结果集
     * @param statement
     *            静态语句接口
     * @param conn
     *            数据库连接
     */
    static void closeConnection(ResultSet rs, Statement statement,
	    Connection conn) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (SQLException e) {
		log.error("数据库结果集关闭异常：" + e.getMessage(), e);
	    }
	}
	if (statement != null) {
	    try {
		statement.close();
	    } catch (SQLException e) {
		log.error("静态语句接口关闭异常：" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("数据库关闭异常：" + e.getMessage(), e);
	    }
	}
    }

    /**
     * 关闭结果集、关闭动态语句接口、关闭数据库连接
     * 
     * @param rs
     *            结果集
     * @param preparedStatement
     *            动态语句接口
     * @param conn
     *            数据库连接
     */
    static void closeConnection(ResultSet rs,
	    PreparedStatement preparedStatement, Connection conn) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (SQLException e) {
		log.error("数据库结果集关闭异常：" + e.getMessage(), e);
	    }
	}
	if (preparedStatement != null) {
	    try {
		preparedStatement.close();
	    } catch (SQLException e) {
		log.error("动态语句接口关闭异常：" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("数据库关闭异常：" + e.getMessage(), e);
	    }
	}
    }

    public static void main(String[] args) {
	System.out.println(DbManager.getConnection());
    }

}
