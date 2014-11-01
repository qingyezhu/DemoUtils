package com.wangzhu.designpattern;

/**
 * 单例设计模式：饿汉模式<br/>
 * 当JVM启动时，该类就会被加载
 * 
 * @author wangzhu
 * @date 2014-10-31上午10:03:03
 * 
 */
public class Singleton1 {
	private static final Singleton1 INSTANCE = new Singleton1();

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		return Singleton1.INSTANCE;
	}

}
