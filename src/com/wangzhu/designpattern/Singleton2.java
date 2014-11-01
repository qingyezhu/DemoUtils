package com.wangzhu.designpattern;

/**
 * 单例设计模式：懒汉模式<br/>
 * 虽然进行了延迟加载，由于进行了线程同步，故耗时很高
 * 
 * @author wangzhu
 * @date 2014-10-31上午10:04:54
 * 
 */
public class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {
	}

	public static synchronized Singleton2 getInstance() {
		if (null == Singleton2.instance) {
			Singleton2.instance = new Singleton2();
		}
		return Singleton2.instance;
	}

}
