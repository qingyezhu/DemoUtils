package com.wangzhu.dateutil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * ��ȡ��ǰʱ�䣺��ȷ����<br/>
	 * ���磺<br/>
	 * 2014-08-20 16:00:01
	 * 
	 * @return
	 */
	public static String getYMDHMST() {
		String ret = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		ret = sdf.format(date);
		return ret;
	}

	/**
	 * ��ȡ��ǰʱ�䴮����ȷ����<br/>
	 * ���磺<br/>
	 * 201408201600002
	 * 
	 * @return
	 */
	public static String getYMDHMSTS() {
		String ret = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = new Date();
		ret = sdf.format(date);
		return ret;
	}

	/**
	 * ����ָ���ĸ�ʽ����ȡʱ���ַ���
	 * 
	 * @param format
	 * @return
	 */
	public static String getFormatData(String format) {
		String ret = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		ret = sdf.format(date);
		return ret;
	}

	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("getYMDHMST: " + DateUtil.getYMDHMST());
		System.out.println("getYMDHMSTS: " + DateUtil.getYMDHMSTS());
		System.out.println("getFormatData: "
				+ DateUtil.getFormatData("yyyy-MM-dd"));
	}
}
