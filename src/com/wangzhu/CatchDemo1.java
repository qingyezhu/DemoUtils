package com.wangzhu;

import java.util.HashMap;
import java.util.Map;

/**
 * ��try��catch��finally���У���try����return��䣬�򷵻�try�б�����ֵ��<br/>
 * ����try�����Ƿ�Ըñ����������޸ģ� ����Ӱ��try��return�ķ���ֵ��<br/>
 * ��finally����return��䣬�����try��catch���е�return��䡣<br/>
 * ��finally���׳��쳣��������try-catch-finally�����׳��쳣��<br/>
 * 
 * ע�⣺<br/>
 * ������try����catch��ʹ��return��䣬ͨ��finally���дﵽ��try����catch����ֵ���޸��ǲ����еġ�<br/>
 * finally���б���ʹ��return��䣬��Ϊfinally�������ʹ��return��䣬<br/>
 * ����ʾ��������try��catch���е��쳣��Ϣ�� �����˴���ķ�����<br/>
 * finally����Ƥ���ٴ��׳��쳣����������try-catch-finally��ķ����׳��쳣������������try��catch���е��쳣��<br/>
 * 
 * @author wangzhu2014-7-20����8:48:43
 * 
 */
public class CatchDemo1 {
	/**
	 * ���أ�try
	 * 
	 * @return
	 */
	public static String test() {
		String str = "";
		try {
			str = "try";
			return str;
		} catch (Exception e) {
			str = "catch";
			return str;
		} finally {
			str = "finally";
		}
	}

	/**
	 * ���أ�finally
	 * 
	 * @return
	 */
	public static String test1() {
		String str = "";
		try {
			str = "try";
			return str;
		} catch (Exception e) {
			str = "catch";
			return str;
		} finally {
			str = "finally";
			return str;
		}
	}

	/**
	 * ���أ�catch
	 * 
	 * @return
	 */
	public static String test2() {
		String str = "";
		try {
			str = "try";
			Integer.parseInt(null);
			return str;
		} catch (Exception e) {
			str = "catch";
			return str;
		} finally {
			str = "finally";
		}
	}

	/**
	 * ���أ�finally
	 * 
	 * @return
	 */
	public static String test3() {
		String str = "";
		try {
			str = "try";
			Integer.parseInt(null);
		} catch (Exception e) {
			str = "catch";
			return str;
		} finally {
			str = "finally";
			return str;
		}
	}

	/**
	 * ����:finally
	 * 
	 * @return
	 */
	public static String test4() {
		String str = "";
		try {
			str = "try";
			Integer.parseInt(null);
			return str;
		} catch (Exception e) {
			str = "catch";
			Integer.parseInt(null);
			return str;
		} finally {
			str = "finally";
			// ��ȥ��return��������ᱨ�쳣
			return str;
		}
	}

	/**
	 * ���أ�finally
	 * 
	 * @return
	 */
	public static String test5() {
		String str = "";
		try {
			str = "try";
			Integer.parseInt(null);
			return str;
		} catch (NullPointerException e) {
			str = "catch";
			return str;
		} finally {
			str = "finally";
			// ��ȥ��return��������ᱨ�쳣
			return str;
		}
	}

	public static String test6(Map<String, Object> map) {
		String str = "";
		try {
			str = "try";
			map.put(str, str);
			Integer.parseInt(null);
			return str;
		} catch (NullPointerException e) {
			str = "catch";
			map.put(str, str);
			return str;
		} finally {
			str = "finally";
			map.put(str, str);
			// ��ȥ��return��������ᱨ�쳣
			return str;
		}
	}

	public static void main(String[] args) {
		System.out.println("try: " + CatchDemo1.test());
		System.out.println("finally: " + CatchDemo1.test1());
		System.out.println("catch: " + CatchDemo1.test2());
		System.out.println("finally: " + CatchDemo1.test3());
		System.out.println("finally: " + CatchDemo1.test4());
		System.out.println("finally: " + CatchDemo1.test5());
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("finally: " + CatchDemo1.test6(map));
		for (String key : map.keySet()) {
			System.out.println(key + "===" + map.get(key));
		}
	}
}
