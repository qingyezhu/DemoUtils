package com.wangzhu.designpattern;

/**
 * �������ģʽ������ģʽ<br/>
 * ��Ȼ�������ӳټ��أ����ڽ������߳�ͬ�����ʺ�ʱ�ܸ�
 * 
 * @author wangzhu
 * @date 2014-10-31����10:04:54
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
