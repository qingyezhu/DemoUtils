package com.wangzhu.designpattern;

/**
 * �������ģʽ������ģʽ<br/>
 * ��JVM����ʱ������ͻᱻ����
 * 
 * @author wangzhu
 * @date 2014-10-31����10:03:03
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
