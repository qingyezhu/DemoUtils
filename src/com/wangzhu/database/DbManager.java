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
 * DriverManager������������������ݿ�����<br/>
 * Connection�����ݿ����ӽӿ�<br/>
 * Statement�����ӿڣ�������̬����SQL���<br/>
 * PreparedStatement��Ԥ������䣬������̬����SQL���<br/>
 * ResultSetMetaData�������Ԫ���ݣ��������ơ�������<br/>
 * ResultSet����������������ݼ�¼�Ľ������<br/>
 * ��<br/>
 * 
 * @author wangzhu
 * @date 2015-2-7����1:46:56
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
	    log.error("�����ļ������쳣��" + e.getMessage(), e);
	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException e) {
		    log.error("�����ļ���ȡ�쳣��" + e.getMessage(), e);
		}
	    }
	}
	driver = properties.getProperty("driver");
	url = properties.getProperty("url");
	user = properties.getProperty("user");
	password = properties.getProperty("password");
    }

    /**
     * ��ȡ���ݿ�����
     * 
     * @return
     */
    static Connection getConnection() {
	try {
	    Class.forName(driver);
	    return DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
	    log.error("���ݿ������쳣��" + e.getMessage(), e);
	}
	return null;
    }

    /**
     * �ر����ݿ�����
     * 
     * @param conn
     *            ���ݿ�����
     */
    static void closeConnection(Connection conn) {
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("���ݿ�ر��쳣��" + e.getMessage(), e);
	    }
	}
    }

    /**
     * �رվ�̬���ӿڡ��ر����ݿ�����
     * 
     * @param statement
     *            ��̬���ӿ�
     * @param conn
     *            ���ݿ�����
     */
    static void closeConnection(Statement statement, Connection conn) {
	if (statement != null) {
	    try {
		statement.close();
	    } catch (SQLException e) {
		log.error("��̬���ӿڹر��쳣��" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("���ݿ�ر��쳣��" + e.getMessage(), e);
	    }
	}
    }

    /**
     * �رն�̬���ӿڡ��ر����ݿ�����
     * 
     * @param preparedStatement
     *            ��̬���ӿ�
     * @param conn
     *            ���ݿ�����
     */
    static void closeConnection(PreparedStatement preparedStatement,
	    Connection conn) {
	if (preparedStatement != null) {
	    try {
		preparedStatement.close();
	    } catch (SQLException e) {
		log.error("��̬���ӿڹر��쳣��" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("���ݿ�ر��쳣��" + e.getMessage(), e);
	    }
	}
    }

    /**
     * �رս�������رվ�̬���ӿڡ��ر����ݿ�����
     * 
     * @param rs
     *            �����
     * @param statement
     *            ��̬���ӿ�
     * @param conn
     *            ���ݿ�����
     */
    static void closeConnection(ResultSet rs, Statement statement,
	    Connection conn) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (SQLException e) {
		log.error("���ݿ������ر��쳣��" + e.getMessage(), e);
	    }
	}
	if (statement != null) {
	    try {
		statement.close();
	    } catch (SQLException e) {
		log.error("��̬���ӿڹر��쳣��" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("���ݿ�ر��쳣��" + e.getMessage(), e);
	    }
	}
    }

    /**
     * �رս�������رն�̬���ӿڡ��ر����ݿ�����
     * 
     * @param rs
     *            �����
     * @param preparedStatement
     *            ��̬���ӿ�
     * @param conn
     *            ���ݿ�����
     */
    static void closeConnection(ResultSet rs,
	    PreparedStatement preparedStatement, Connection conn) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (SQLException e) {
		log.error("���ݿ������ر��쳣��" + e.getMessage(), e);
	    }
	}
	if (preparedStatement != null) {
	    try {
		preparedStatement.close();
	    } catch (SQLException e) {
		log.error("��̬���ӿڹر��쳣��" + e.getMessage(), e);
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		log.error("���ݿ�ر��쳣��" + e.getMessage(), e);
	    }
	}
    }

    public static void main(String[] args) {
	System.out.println(DbManager.getConnection());
    }

}
