package com.wangzhu.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DemoDateFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoDateFormat.parse("2013-02-10");
		DemoDateFormat.parse2("2013-02-10");

	}

	/**
	 * 在实际运行环境中竟然发生异常，就改为下面的方法
	 * 
	 * @param dateStr
	 */
	private static void parse(String dateStr) {
		DateFormat format = DateFormat.getDateInstance();
		try {
			System.out.println(format.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 形参的格式必须是yyyy-MM-dd否则将抛出异常
	 * 
	 * @param dateStr
	 */
	private static void parse2(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(sdf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
