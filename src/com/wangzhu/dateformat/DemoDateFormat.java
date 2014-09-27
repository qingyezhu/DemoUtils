package com.wangzhu.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ϵͳʱ���ʽ����ͬ��ϵͳ�п��ܲ�ͬ������Ӣ��ϵͳ������ϵͳ���Ͳ�ͬ����
 * 
 * @author wangzhu
 * @date 2014-9-26����10:06:03
 * 
 */
public class DemoDateFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date(113, 1, 10);
		// Ĭ�ϵ���DateFormat.getDateTimeInstance()������ʱ����ʾ��ʽ
		System.out.println(date.toLocaleString());// 2013-2-10
		DemoDateFormat.parse("2013-02-10");
		DemoDateFormat.parse2("2013-02-10");

	}

	/**
	 * ��ʵ�����л����о�Ȼ�����쳣���͸�Ϊ����ķ���
	 * 
	 * @param dateStr
	 */
	private static void parse(String dateStr) {
		// Ĭ�ϵ��ñ�����ʾʱ���ʽ
		DateFormat format = DateFormat.getDateInstance();
		try {
			System.out.println(format.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �βεĸ�ʽ������yyyy-MM-dd�����׳��쳣
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
