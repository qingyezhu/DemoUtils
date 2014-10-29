package com.wangzhu;

import java.util.Arrays;
import java.util.List;

public class SqlHelper {

	/**
	 * 获取Sql语句中类似In语句：如<br/>
	 * AND (ZB_ID = '00001' OR ZB_ID = '00002' OR ZB_ID = '00003')
	 * 
	 * @param arr
	 *            属性值集合
	 * @param property
	 *            属性字符串
	 * @param accum
	 *            返回的Sql字符串
	 */
	public static void getInOrStr(String[] arr, String property,
			StringBuffer accum) {
		if ((arr == null) || (arr.length == 0)) {
			return;
		}
		if ((property == null) || (property.length() == 0)) {
			return;
		}
		SqlHelper.getInOrStr(Arrays.asList(arr), property, accum);
	}

	/**
	 * 获取Sql语句中类似In语句：如<br/>
	 * AND (ZB_ID = '00001' OR ZB_ID = '00002' OR ZB_ID = '00003')
	 * 
	 * @param list
	 *            属性列表
	 * @param property
	 *            属性字符串
	 * @param accum
	 *            返回的Sql字符串
	 */
	public static void getInOrStr(List<String> list, String property,
			StringBuffer accum) {
		if ((list == null) || (list.size() == 0)) {
			return;
		}
		if ((property == null) || (property.length() == 0)) {
			return;
		}
		accum.append(" AND (");
		for (int i = 0, size = list.size(); i < size; i++) {
			if (i > 0) {
				accum.append(" OR ");
			}
			accum.append(property).append(" = '").append(list.get(i))
					.append("'");
		}
		accum.append(")");
	}

	/**
	 * 获取Sql语句中In语句，如<br/>
	 * AND ZB_ID IN ('00001','00002','00003')
	 * 
	 * @param arr
	 *            属性值集合
	 * @param property
	 *            属性字符串
	 * @param accum
	 *            返回的Sql字符串
	 */
	public static void getInStr(String[] arr, String property,
			StringBuffer accum) {
		if ((null == arr) || (arr.length == 0)) {
			return;
		}
		if ((null == property) || (property.length() == 0)) {
			return;
		}
		SqlHelper.getInStr(Arrays.asList(arr), property, accum);
	}

	/**
	 * 获取Sql语句中In语句，如<br/>
	 * AND ZB_ID IN ('00001','00002','00003')
	 * 
	 * @param list
	 *            属性列表
	 * @param property
	 *            属性字符串
	 * @param accum
	 *            返回的Sql字符串
	 */
	private static void getInStr(List<String> list, String property,
			StringBuffer accum) {
		if ((null == list) || (list.size() == 0)) {
			return;
		}
		if ((null == property) || (property.length() == 0)) {
			return;
		}
		accum.append(" AND ").append(property).append(" IN (");
		for (int i = 0, size = list.size(); i < size; i++) {
			if (i > 0) {
				accum.append(",");
			}
			accum.append("'").append(list.get(i)).append("'");
		}
		accum.append(")");
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuffer accum = new StringBuffer();
		SqlHelper.getInOrStr(new String[] { "00001", "00002", "00003" },
				"zb_id", accum);
		System.out.println(accum);
		accum.setLength(0);

		SqlHelper.getInStr(new String[] { "00001", "00002", "00003" }, "zb_id",
				accum);
		System.out.println(accum);

	}
}
