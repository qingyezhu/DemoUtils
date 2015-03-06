package com.wangzhu.database;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PooledDB {

    Connection conn;

    public PooledDB() throws Exception {
	Context ct = new InitialContext();
	String source = "java:comp/env/jdbc/test_jndi_oracle";
	DataSource ds = (DataSource) ct.lookup(source);
	conn = ds.getConnection();
    }

    public void close() throws Exception {
	if ((conn != null) && !conn.isClosed()) {
	    conn.close();
	}
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

	PooledDB pd = null;
	try {
	    pd = new PooledDB();
	    System.out.println(pd.conn);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (pd != null) {
		try {
		    pd.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }

}
