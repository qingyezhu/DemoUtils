package com.wangzhu.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据系统时间格式：不同的系统中可能不同（例如英文系统与中文系统，就不同！）
 * 
 * @author wangzhu
 * @date 2014-9-26下午10:06:03
 * 
 */
public class DemoDateFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date(113, 1, 10);
		// 默认调用DateFormat.getDateTimeInstance()，本地时间显示格式
		System.out.println(date.toLocaleString());// 2013-2-10
		DemoDateFormat.parse("2013-02-10");
		DemoDateFormat.parse2("2013-02-10");

	}

	/**
	 * 在实际运行环境中竟然发生异常，就改为下面的方法
	 * 
	 * @param dateStr
	 */
	private static void parse(String dateStr) {
		// 默认调用本地显示时间格式
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
