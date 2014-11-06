package com.wangzhu.database;

public class DbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(DbUtils.INSTANCE.query(
				"SELECT TABLE_NAME FROM USER_TABLES WHERE NUM_ROWS > ?",
				new Object[] { "10000" }));
	}

}
