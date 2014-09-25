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
	 * ��ʵ�����л����о�Ȼ�����쳣���͸�Ϊ����ķ���
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
