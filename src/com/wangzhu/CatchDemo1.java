package com.wangzhu;

import java.util.HashMap;
import java.util.Map;

/**
 * 在try、catch、finally块中，若try中有return语句，则返回try中变量的值，<br/>
 * 不管try块外是否对该变量进行了修改， 都不影响try中return的返回值。<br/>
 * 若finally中有return语句，则忽略try、catch块中的return语句。<br/>
 * 若finally中抛出异常，则整个try-catch-finally块中抛出异常。<br/>
 * 
 * 注意：<br/>
 * 尽量在try或者catch中使用return语句，通过finally快中达到对try或者catch返回值的修改是不可行的。<br/>
 * finally块中避免使用return语句，因为finally快中如果使用return语句，<br/>
 * 会显示的消化掉try、catch块中的异常信息， 屏蔽了错误的发生。<br/>
 * finally块中皮面再次抛出异常，否则整个try-catch-finally块的方法抛出异常，并会消化掉try、catch块中的异常。<br/>
 * 
 * @author wangzhu2014-7-20下午8:48:43
 * 
 */
public class CatchDemo1 {
	/**
	 * 返回：try
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
	 * 返回：finally
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
	 * 返回：catch
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
	 * 返回：finally
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
	 * 返回:finally
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
			// 若去掉return语句则最后会报异常
			return str;
		}
	}

	/**
	 * 返回：finally
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
			// 若去掉return语句则最后会报异常
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
			// 若去掉return语句则最后会报异常
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
